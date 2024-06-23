package edu.poly.asm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.model.Users;
import edu.poly.asm.service.CartService;
import edu.poly.asm.service.DateService;
import edu.poly.asm.service.SessionService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	CartService cartservice;
	@Autowired
	DateService dateservice;
	@Autowired
	SessionService session;
	
	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("cartitem", cartservice.getItems());
		model.addAttribute("amount", cartservice.getAmout());
		return "auth/cart";
	}
	
	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id, @RequestParam("quantity") int quantity) {
		cartservice.update(id, quantity);
		session.set("countcart", cartservice.getCount());	
		return "redirect:/cart/index";
	}
	
	@RequestMapping("remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cartservice.remove(id);
		session.set("countcart", cartservice.getCount());	
		return "redirect:/cart/index";
	}
}
