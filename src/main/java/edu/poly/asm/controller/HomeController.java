package edu.poly.asm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.model.Categories;
import edu.poly.asm.model.Products;
import edu.poly.asm.repository.CategoryRepository;
import edu.poly.asm.repository.ProductRepository;
import edu.poly.asm.service.CategoryService;
import edu.poly.asm.service.CookieService;
import edu.poly.asm.service.ProductService;
import edu.poly.asm.service.SessionService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	CategoryService categoryservice;
	@Autowired
	ProductService productservice;
	@Autowired
	ProductRepository productrepository;
	@Autowired
	SessionService session;
	@Autowired
	CookieService cookie;

	@RequestMapping("index")
	public String index(Model model, @RequestParam(name = "category", required = false) String category) {
		System.out.println("Check: " + session.get("checkadmin", null));
		if (category != null) {
			Page<Products> items = null;
			if (category.equalsIgnoreCase("laptop")) {
				items = productrepository.findByCategories_Categoryid(1, null);
			} else if (category.equalsIgnoreCase("macbook")) {
				items = productrepository.findByCategories_Categoryid(2, null);
			}
			if (items != null) {
				model.addAttribute("items", items);
				return "auth/all_item";
			}
		}
		Pageable pageable = PageRequest.of(0, 8);
		List<Products> items = productrepository.findTop8DiscountedProducts(pageable);
		this.laptop_product(model);
		this.list_macbook(model);
		this.list_category(model);
		model.addAttribute("items", items);
		return "auth/main";
	}

	// List Macbook
	public List<Products> list_macbook(Model model) {
		Pageable pageable = PageRequest.of(0, 4);
		Page<Products> list_macbook = productrepository.findByCategories_Categoryid(2, pageable);
		model.addAttribute("item_macbook", list_macbook);
		return list_macbook.getContent();
	}

	// List Laptop
	public List<Products> laptop_product(Model model) {
		Pageable pageable = PageRequest.of(0, 4);
		Page<Products> product_laptop = productrepository.findByCategories_Categoryid(1, pageable);
		model.addAttribute("item_laptop", product_laptop);
		return product_laptop.getContent();
	}

	// List category
	public List<Categories> list_category(Model model) {
		List<Categories> list = categoryservice.findAll();
		model.addAttribute("list_category", list);
		return list;
	}

	@RequestMapping("product_infomation/{id}")
	public String product_infomation(Model model, @PathVariable("id") Integer id) {
		Products item = productservice.findById(id).get();
		model.addAttribute("item", item);
		return "auth/product_infomation.html";
	}

	// @RequestMapping("all-item")
	// public String all_item(Model model) {
	// Page<Products> items = productrepository.findByCategories_Categoryid(1,
	// null);
	// model.addAttribute("items", items);
	// return "auth/all_item";
	// }
}
