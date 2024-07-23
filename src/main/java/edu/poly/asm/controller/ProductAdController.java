package edu.poly.asm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.asm.model.Categories;
import edu.poly.asm.model.Products;
import edu.poly.asm.repository.ProductRepository;
import edu.poly.asm.service.CategoryService;
import edu.poly.asm.service.ProductService;
import edu.poly.asm.service.SessionService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("product")
public class ProductAdController {
	@Autowired
	ProductService productservice;
	@Autowired
	ProductRepository productrepository;

	@Autowired
	CategoryService categoryservice;

	@Autowired
	SessionService session;

	@RequestMapping({ "index", "search" })
	public String index(Model model) {
		// String kword = kw.orElse("");
		// Pageable pageable = PageRequest.of(p.orElse(0), 10); // 3 items per page
		// Page<Products> page;
		// if (kword.isEmpty()) {
		// page = productrepository.findAll(pageable);
		// } else {
		// page = productrepository.findByProductnameLike("%" + kword + "%", pageable);
		// }
		// model.addAttribute("items", page.getContent());
		// model.addAttribute("currentPage", page.getNumber());
		// model.addAttribute("totalPages", page.getTotalPages());
		// model.addAttribute("keyword", kword);

		// // Tổng số sản phẩm
		// long countProduct = productrepository.count();
		// model.addAttribute("countProduct", countProduct);
		return "admin/products";
	}

	@RequestMapping("addproduct")
	public String add_product(Model model) {
		model.addAttribute("categories", categoryservice.findAll());
		model.addAttribute("product", new Products());
		return "admin/add_product";
	}

	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("product") Products item, BindingResult result, Model model,
			RedirectAttributes redirectatribute, @RequestParam("imageFile") MultipartFile imagefile,
			@RequestParam("imageName") String imageName) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryservice.findAll());
			return "admin/add_product";
		}

		if (!imagefile.isEmpty()) {
			String filename = imagefile.getOriginalFilename();
			// Lấy đường dẫn tuyệt đối đến tshư mục images trong resources
			File directory = ResourceUtils.getFile("classpath:static/images");
			// Tạo đường dẫn tuyệt đối đến file ảnh mới
			File newFile = new File(directory.getAbsolutePath() + File.separator + filename);
			// Lưu file ảnh vào images
			imagefile.transferTo(newFile);
			item.setImage(filename);
			System.out.println("Img:" + newFile);
			System.out.println("Directory: " + directory);
		} else {
			item.setImage(imageName);
		}

		try {
			redirectatribute.addFlashAttribute("successMessage", "Product saved successfully");
			productservice.save(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("product", item);
		return "redirect:/product/addproduct";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Products product = productservice.getById(id);
		model.addAttribute("item", product);
		return "forward:/product/addproduct";
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectatribute) {
		try {
			redirectatribute.addFlashAttribute("successMessage", "Delete success!");
			productservice.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return "redirect:/product/index";
	}
}
