package edu.poly.asm.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.asm.model.Products;
import edu.poly.asm.service.ProductService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("admin")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("products")
    public Page<Products> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return productService.findAll(pageable);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Products> getOne(@PathVariable("id") Integer id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.findById(id).get());
    }

    @PostMapping("products")
    public ResponseEntity<?> post(@Valid @RequestPart("products") Products products,
            BindingResult result,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile)
            throws IllegalStateException, IOException {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = imageFile.getOriginalFilename();
            File directory = ResourceUtils.getFile("classpath:static/images");
            File newFile = new File(directory.getAbsolutePath() + File.separator + filename);
            imageFile.transferTo(newFile);
            products.setImage(filename);
        } else {
            System.out.println("Img is null");
            products.setImage(null);
        }

        if (products.getProductid() == null || productService.existsById(products.getProductid())) {
            productService.save(products);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Products> delete(@PathVariable("id") Integer id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
