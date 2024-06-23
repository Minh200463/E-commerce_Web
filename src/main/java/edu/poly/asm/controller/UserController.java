package edu.poly.asm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.asm.model.Products;
import edu.poly.asm.model.Users;
import edu.poly.asm.repository.UserReponsitory;
import edu.poly.asm.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userservice;

	@Autowired
	UserReponsitory userreponsitory;
	
	

	@RequestMapping({ "index", "search" })
	public String index(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("keyword") Optional<String> kw) {
		String kword = kw.orElse("");
		Pageable pageable = PageRequest.of(p.orElse(0), 10); // 10 items per page
		Page<Users> page;
		if (kword.isEmpty()) {
			page = userservice.findAll(pageable);
		} else {
			page = userreponsitory.findByUsernameLike("%" + kword + "%", pageable);
		}
		model.addAttribute("items", page.getContent());
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("keyword", kword);
		return "admin/user";
	}

	@RequestMapping("adduser")
	public String add_user(Model model) {
		model.addAttribute("user", new Users());
		return "admin/add_user";
	}

	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("user") Users item, BindingResult result, Model model, RedirectAttributes redirectatribute) {
		if (result.hasErrors()) {
			return "admin/add_user";
		}
		model.addAttribute("successMessage", "Saved successfully!");
		userservice.save(item);
		return "admin/add_user";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Users> user = userservice.findById(id);
		model.addAttribute("user", user.get());
		return "admin/add_user";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectatribute) {
		redirectatribute.addFlashAttribute("successMessage", "Delete successfully!");
		userservice.deleteById(id);
		return "redirect:/user/index";
	}
	
	@RequestMapping("deleteall")
	public String deleteall(Model model) {
		userservice.deleteAll();
		return "redirect:/user/index";
	}
}
