package edu.poly.asm.controller;

import java.security.Principal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.asm.model.ItemCart;
import edu.poly.asm.model.Products;
import edu.poly.asm.repository.ProductRepository;
import edu.poly.asm.service.CartService;
import edu.poly.asm.service.ProductService;
import edu.poly.asm.service.SessionService;

@Controller
@RequestMapping("productinfo")
public class ProductInfoController {
	@Autowired
	ProductService productservice;
	@Autowired
	ProductRepository productrepository;
	@Autowired
	CartService cartservice;
	@Autowired
	SessionService session;

	@RequestMapping("add-item/{id}")
	public String add_item(@PathVariable("id") Integer id, @RequestParam("quantity") Integer quantity, Model model,
			RedirectAttributes rdrattribute) {

		Products product = productservice.findById(id).orElse(null);
		if (product != null) {
			ItemCart item = new ItemCart();
			BeanUtils.copyProperties(product, item);
			cartservice.add(item, quantity);
			rdrattribute.addFlashAttribute("success", item.getProductname());
			session.set("countcart", cartservice.getCount());
		}
		return "redirect:/cart/index";

	}
}
