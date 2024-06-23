package edu.poly.asm.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.model.Orders;
import edu.poly.asm.model.Users;
import edu.poly.asm.repository.OrderRepository;
import edu.poly.asm.repository.OrderdetailsRepository;
import edu.poly.asm.service.OrderService;
import edu.poly.asm.service.OrderdetailsService;

@Controller
@RequestMapping("admin")
public class OrderController {
	@Autowired
	OrderService orderservice;
	@Autowired
	OrderdetailsService orderdetailservice;
	@Autowired
	OrderdetailsRepository orderdetailsrepository;
	@Autowired
	OrderRepository orderrepository;
	
	@GetMapping("order/index")
    public String index(Model model) {
        List<Orders> orderList = orderservice.findAll();
        model.addAttribute("orderlist", orderList);
        return "admin/order";
    }

    @GetMapping("order/details/{id}")
    public String viewOrderDetails(@PathVariable("id") Integer id, Model model) {
        System.out.println("Orderid: " + id);

        Orders order = orderservice.findById(id).get();
        model.addAttribute("order", order);
        List<Orderdetails> orderDetailsList = orderdetailsrepository.findAllByOrderid_Orderid(id);
        model.addAttribute("odetails", orderDetailsList);
        return "forward:/admin/order/index";
    }
	
    @RequestMapping("order/addorders")
	public String add_view() {
		return "admin/add_order";
	}
    
//    @GetMapping({"", "/index", "/search", "/details/{id}"})
//    public String handleOrderRequests(
//            Model model,
//            @RequestParam("p") Optional<Integer> p,
//            @RequestParam("keyword") Optional<String> kw,
//            @PathVariable(value = "id", required = false) Integer id) {
//
//        // Load all orders for the initial view or details view
//        List<Orders> orderList = orderService.findAll();
//        model.addAttribute("orderlist", orderList);
//
//        // Check if it's a details request
//        if (id != null) {
//            System.out.println("Orderid: " + id);
//            Orders order = orderService.findById(id).orElse(null);
//            model.addAttribute("order", order);
//            List<Orderdetails> orderDetailsList = orderDetailsRepository.findAllByOrderid_Orderid(id);
//            model.addAttribute("odetails", orderDetailsList);
//            return "admin/order";
//        }
//
//        // Check if it's a search request
//        String kword = kw.orElse("");
//        Pageable pageable = PageRequest.of(p.orElse(0), 10); // 10 items per page
//        Page<Orderdetails> page;
//        if (kword.isEmpty()) {
//            page = orderDetailService.findAll(pageable);
//        } else {
//            page = orderDetailsRepository.findOrderdetailsByKeyword("%" + kword + "%", pageable);
//        }
//        model.addAttribute("items", page.getContent());
//        model.addAttribute("currentPage", page.getNumber());
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("keyword", kword);
//
//        return "admin/order";
//    }
	
	
}
