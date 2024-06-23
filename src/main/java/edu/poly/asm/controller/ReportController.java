package edu.poly.asm.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asm.model.Orders;
import edu.poly.asm.model.Products;
import edu.poly.asm.repository.OrderRepository;
import edu.poly.asm.repository.OrderdetailsRepository;
import edu.poly.asm.repository.ProductRepository;
import edu.poly.asm.service.OrderService;
import edu.poly.asm.service.ProductService;

@Controller
@RequestMapping("admin")
public class ReportController {
	@Autowired
	OrderRepository orderrepository;
	@Autowired
	OrderdetailsRepository ordetailrepository;
	@Autowired
	ProductRepository productrepository;
	@Autowired
	ProductService productservice;
	@Autowired
	OrderService orderservice;
	
	@RequestMapping("report/index")
	public String index(Model model, @RequestParam(name = "year", required = false) Integer year) {
		if(year != null) {
			List<Object[]> revenue = ordetailrepository.findRevenueByMonthAndYear(year);
			model.addAttribute("revenueData", revenue);
		}
		this.sellingProduct(model);
		this.productOutOfStock(model);
		this.totalOrderToday(model);
		this.totalProduct(model);
		this.totalOrder(model);
		this.totalProductOutOfStock(model);
		this.totalrevenue(model);
		return "admin/report";
	}
	
	public void sellingProduct(Model model) {
		List<Object[]> sellingproduct = ordetailrepository.findTopSellingProducts();
		model.addAttribute("itemselling", sellingproduct);
	}
	
	//danh sách sản phẩm hết hàng
	public void productOutOfStock(Model model) {
		List<Products> listoutotStock = productrepository.findByStatus(false);
		model.addAttribute("itemsoutofstock", listoutotStock);
	}
	//Tổng đơn hàng hôm nay
	public void totalOrderToday(Model model) {
		List<Object[]> totalorder = ordetailrepository.findTodayOrders();
		double totalAmout = totalorder.stream().mapToDouble(order -> (Double) order[3]).sum();
		model.addAttribute("totalAmout", formatToUSD(totalAmout));
		model.addAttribute("totaltoday", totalorder);
	}
	
	//Tổng sản phẩm
	public void totalProduct(Model model) {
		Integer totalproduct = productservice.findAll().size();
		model.addAttribute("totalproduct", totalproduct);
	}
	
	//Tổng đơn hàng
	public void totalOrder(Model model) {
		Integer totalorder = orderservice.findAll().size();
		model.addAttribute("totalorder", totalorder);
	}
	
	//Tổng sản phẩm hết hàng
		public void totalProductOutOfStock(Model model) {
			Integer totalproductoutofstock = productrepository.findByStatus(false).size();
			model.addAttribute("pOFS", totalproductoutofstock);
		}
		
		
		
	//Tổng doanh thu
	public void totalrevenue(Model model) {
		List<Orders> list = orderservice.findAll();
		double totalRevenue = list.stream().mapToDouble(item -> item.getTotalamount()).sum();
		model.addAttribute("totalrevenue", formatToUSD(totalRevenue));
	}
	
	public static String formatToUSD(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }
}
