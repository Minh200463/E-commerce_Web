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
		List<Categories> list = categoryservice.findAll();
		model.addAttribute("items", list);
		model.addAttribute("category", new Categories());
		return "admin/category";
	}

	@RequestMapping("save")
	public String add(@Valid @ModelAttribute("category") Categories item, BindingResult result, Model model, RedirectAttributes redirectatribute) {
		if (result.hasErrors()) {
			model.addAttribute("items", categoryservice.findAll());
			return "redirect:/category/index";
		}
		redirectatribute.addFlashAttribute("successMessage", "Add new category successfully!");
		categoryservice.save(item);
		return "redirect:/category/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Categories item = categoryservice.getById(id);
		model.addAttribute("item", item);
		return "forward:/category/index";
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectatribute) {
		redirectatribute.addFlashAttribute("successMessage", "Delete success!");
		categoryservice.deleteById(id);
		return "redirect:/category/index";
	}

}
