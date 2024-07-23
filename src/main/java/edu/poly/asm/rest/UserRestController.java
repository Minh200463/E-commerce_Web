package edu.poly.asm.rest;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import edu.poly.asm.model.Users;
import edu.poly.asm.service.UserService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("admin")
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public Page<Users> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userService.findAll(pageable);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Users> getOne(@PathVariable("id") Integer id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findById(id).get());
    }

    @PostMapping("users")
    public ResponseEntity<?> post(@Valid @RequestBody Users user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        if (user.getUserid() == null || userService.existsById(user.getUserid())) {
            userService.save(user);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
