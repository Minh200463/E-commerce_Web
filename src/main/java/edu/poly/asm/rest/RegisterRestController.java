package edu.poly.asm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.asm.model.Users;
import edu.poly.asm.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("register")
public class RegisterRestController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("success")
    public Users post(@RequestBody Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userService.save(users);
    }

}
