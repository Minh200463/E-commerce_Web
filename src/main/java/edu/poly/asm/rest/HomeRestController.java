package edu.poly.asm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.asm.model.Products;
import edu.poly.asm.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("ok")
public class HomeRestController {
    @Autowired
    ProductService productService;

    @GetMapping("index")
    public Page<Products> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.findAll(pageRequest);
    }

    @GetMapping("index/detail/{id}")
    public ResponseEntity<Products> getOne(@PathVariable("id") Integer id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.findById(id).get());
    }
}
