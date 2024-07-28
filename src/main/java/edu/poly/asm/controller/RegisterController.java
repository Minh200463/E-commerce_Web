package edu.poly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.asm.model.Users;
import edu.poly.asm.service.UserService;
import jakarta.validation.Valid;

@RequestMapping("register")
@Controller
public class RegisterController {
	@Autowired
	UserService userservice;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("user", new Users());
		return "login/register";
	}

	@PostMapping("index")
	public String index(@Valid @ModelAttribute("user") Users item, BindingResult result, Model model,
			RedirectAttributes redirectAttributes,
			@RequestParam("password") String pw, @RequestParam("repassword") String rpw) {

		System.out.println("Repassword: " + rpw);
		if (result.hasErrors()) {
			return "login/register";
		}

		if (rpw == null || rpw.isEmpty()) {
			model.addAttribute("errorrepassword", "Please enter the repassword!");
			return "login/register";
		}

		if (!rpw.equals(pw)) {
			model.addAttribute("errorrepassword", "Passwords do not match!");
			return "login/register";
		}

		item.setPassword(passwordEncoder.encode(item.getPassword()));
		userservice.save(item);
		redirectAttributes.addFlashAttribute("successMessage", "Registration Success!");
		return "redirect:/login/index";
	}

}
