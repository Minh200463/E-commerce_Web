package edu.poly.asm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.asm.model.Categories;
import edu.poly.asm.service.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin("*")
@RequestMapping("admin")
@RestController
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("category")
    public List<Categories> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("category/{id}")
    public ResponseEntity<Categories> getOne(@PathVariable("id") Integer id) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.findById(id).get());
    }

    @PostMapping("category")
    public ResponseEntity<Void> post(@RequestBody Categories categories) {
        if (categories.getCategoryid() == null || categoryService.existsById(categories.getCategoryid())) {
            System.out.println("Categortid_dong1: " + categories.getCategoryid());
            categoryService.save(categories);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Categories> put(@PathVariable("id") Integer id, @RequestBody Categories categories) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.save(categories);
        return ResponseEntity.ok(categories);
    }

}
