package edu.poly.asm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.model.Users;
import edu.poly.asm.repository.UserReponsitory;
import edu.poly.asm.service.CookieService;
import edu.poly.asm.service.SessionService;
import edu.poly.asm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	UserReponsitory userrepository;

	@Autowired
	UserService userservice;

	@Autowired
	SessionService session;

	@Autowired
	CookieService cookie;

	@GetMapping("index")
	public String index() {
		return "login/login";
	}

	// @PostMapping("index")
	// public String index(Model model, @RequestParam("username") String username,
	// @RequestParam("password") String password,
	// @RequestParam(value = "remember", required = false, defaultValue = "false")
	// Boolean remember) {
	// try {
	// Optional<Users> user = userrepository.findByUsername(username);
	// if (user != null && user.getPassword().equals(password)) {
	// session.set("user", user);
	// System.out.println("Admin: " + user.isAdmin());

	// session.set("checkadmin", user.isAdmin());
	// if (remember) {
	// cookie.add("username", user.getUsername(), 24);
	// } else {
	// cookie.add("username", user.getUsername(), 0);
	// }
	// if (user.isAdmin() == true) {
	// return "redirect:/admin/report/index";
	// } else {
	// return "redirect:/home/index";
	// }
	// } else {
	// model.addAttribute("message", "Invalid username or password!");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// model.addAttribute("message", "Error occurred!");
	// }
	// return "login/login";
	// }

}
