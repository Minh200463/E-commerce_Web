package edu.poly.asm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.asm.model.Categories;
import edu.poly.asm.service.CategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryAdminController {
	@Autowired
	CategoryService categoryservice;

	@RequestMapping("index")
	public String index(Model model) {
		// List<Categories> list = categoryservice.findAll();
		// model.addAttribute("items", list);
		model.addAttribute("category", new Categories());
		return "admin/category";
	}
}
