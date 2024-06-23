package edu.poly.asm.service;

import java.util.Collection;

import edu.poly.asm.model.ItemCart;
import edu.poly.asm.model.Orders;
import edu.poly.asm.model.Users;

public interface CartService {

	void add(ItemCart itemcart, Integer quantity);

	double getAmout();

	int getCount();

	Collection<ItemCart> getItems();

	void clear();

	void remove(Integer id);

	ItemCart update(Integer id, int quantity);

	Orders checkout(Users users);


}
