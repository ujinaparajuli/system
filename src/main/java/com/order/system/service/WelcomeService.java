package com.order.system.service;

import java.util.List;

import com.order.system.entity.Cart;
import com.order.system.entity.Item;
import com.order.system.entity.User;
import com.order.system.model.AdminViewDTO;
import com.order.system.model.Checkout;
import com.order.system.model.ItemCart;
import com.order.system.model.OrderDTO;
import com.order.system.model.cartDTO;

public interface WelcomeService {
	public List<Item> getFoodItems();
	public Item getItem(Long id);
	public void addToCart(Long itemId, String sessionId);
	public List<cartDTO> viewCart(List<ItemCart> itemsInSession);
	public void checkout(Double grandTotal);
	
	// search
	public List<Item> searchItem(String text);
	public List<String> getCategoryNameFromMenus(List<Item> searchItems);
	
	//public List<User> getUsers();
	
	public List<String> getAllMenuCategory();
	public List<Item> getFoodItemFromCategory(String category);
	
	public Boolean postCheckout(Checkout checkout, List<ItemCart> itemsInSession);
	public void cashCheckout(Long orderId);
	
	
	// Chef
	public List<OrderDTO> getAllOrders();
	public void orderReady(String orderId);
	
	//Admin
	public List<AdminViewDTO> getMenuAndItemForAdmin();
	
	

}
