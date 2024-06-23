package edu.poly.asm.impl;

import java.util.Date;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import edu.poly.asm.model.ItemCart;
import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.model.Orders;
import edu.poly.asm.model.Products;
import edu.poly.asm.model.Users;
import edu.poly.asm.service.CartService;
import edu.poly.asm.service.OrderService;
import edu.poly.asm.service.OrderdetailsService;
import edu.poly.asm.service.ProductService;
import jakarta.mail.FetchProfile.Item;

@SessionScope
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	OrderService orderservice;
	@Autowired
	OrderdetailsService orderdetailservice;
	@Autowired
	ProductService productservice;

	static Map<Integer, ItemCart> map = new HashMap<>();

	@Override
	public void add(ItemCart itemcart, Integer quantity) {
		ItemCart item = map.get(itemcart.getProductid());
		System.out.println("ID Cartitem: " + item);
		if (item != null) {
			item.setQuantity(item.getQuantity() + quantity);
		} else {
			map.put(itemcart.getProductid(), itemcart);
			itemcart.setQuantity(quantity);
		}
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public ItemCart update(Integer id, int quantity) {
		if (map.containsKey(id)) {
			ItemCart item = map.get(id);
			item.setQuantity(quantity);
			return item;
		}
		return null;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<ItemCart> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		return map.values().stream().mapToInt(item -> item.getQuantity()).sum();
	}

	@Override
	public double getAmout() {
		double total = map.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
		DecimalFormat df = new DecimalFormat("#.##"); // Định dạng số lẻ hai chữ số
		return Double.parseDouble(df.format(total)); // Chuyển đổi số thành dạng đã được làm tròn
	}

	
	@Override
	public Orders checkout(Users users) {
		Orders orders = new Orders();
		orders.setOrderdate(new Date());
		orders.setTotalamount((float) getAmout());
		orders.setStatus(1);
		orders.setUserid(users);
		Orders order = orderservice.save(orders);
		System.out.println("Add Order successfully!");

		for (ItemCart itemcart : getItems()) {
			Products product = productservice.findById(itemcart.getProductid()).get();
			if (product != null) {
				Orderdetails orderdetails = new Orderdetails();
				orderdetails.setOrderid(order);
				orderdetails.setProductid(product);
				orderdetails.setQuantity(itemcart.getQuantity());
				orderdetails.setUnitprice(itemcart.getPrice() * itemcart.getQuantity());
				
				orderdetailservice.save(orderdetails);
				System.out.println("Add orderdetails successfully!");
			}else {
				System.out.println("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:");
			}
		}
		clear();
		return order;

	}
}
