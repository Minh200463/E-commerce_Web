package edu.poly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.asm.service.CookieService;
import edu.poly.asm.service.SessionService;
import jakarta.servlet.RequestDispatcher;
@Controller
public class LogoutController {
	@Autowired
	CookieService cookie;
	
	@Autowired
	SessionService session;
	@RequestMapping("logout")
	public String logout() {
		cookie.remove("username");
		session.remove("user");
		session.remove("countcart");
		return "redirect:/login/index";
	}
	
}
