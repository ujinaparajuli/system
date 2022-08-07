package com.order.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.order.system.dao.CartDao;
import com.order.system.dao.ItemDao;
import com.order.system.dao.MenuDao;
import com.order.system.dao.OrderDao;
import com.order.system.dao.UserDao;
import com.order.system.entity.Cart;
import com.order.system.entity.Item;
import com.order.system.entity.Menu;
import com.order.system.entity.Order;
import com.order.system.entity.User;
import com.order.system.model.AdminViewDTO;
import com.order.system.model.Checkout;
import com.order.system.model.ItemCart;
import com.order.system.model.OrderDTO;
import com.order.system.model.OutputMessage;
import com.order.system.model.cartDTO;
import com.order.system.service.WelcomeService;

@Service
public class WelcomeServiceImpl implements WelcomeService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ItemDao itemDao;
	
	@Autowired
	MenuDao menuDao; 
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
    JavaMailSender javaMailSender;

	@Override
	public List<Item> getFoodItems() {
		return itemDao.findAll();
	}

	public List<User> getUsers() {
		return userDao.findAll();
	}
	

	@Override
	public List<String> getAllMenuCategory() {
		List<String> menuCategories = new ArrayList<String>();
		List<Menu> menuList = menuDao.findAll();
		
		for(Menu menu : menuList) {
			menuCategories.add(menu.getCategory());
		
		}
		return menuCategories;
		
	}

	@Override
	public List<Item> getFoodItemFromCategory(String category) {
		if(category.equalsIgnoreCase("All")) {
			return itemDao.findAll();
		}else {
			List<Menu> menuList = menuDao.findByCategory(category);
			Long menuId = menuList.get(0).getId();
			
			return itemDao.findByMenuId(menuId);
			
		}

	}

	@Override
	public Item getItem(Long id) {
		return  itemDao.findById(id).get();
		
	}

	@Override
	public void addToCart(Long itemId, String sessionId) {
		List<Cart> carts = cartDao.findBySessionId(sessionId);
		int count = 0;
		for(Cart cart : cartDao.findBySessionId(sessionId)) {
			if(itemId.equals(cart.getItemId())) {
				cart.setCount(cart.getCount() + 1);
				cartDao.save(cart);
				count++;
				break;
			}
		}
		
		if(count == carts.size()) {
			Cart cart = new Cart();
			cart.setItemId(itemId);
			cart.setCount(1);
			cart.setSessionId(sessionId);
			cartDao.save(cart);
		}
//		List<Cart> carts = cartDao.findByItemId(itemId);
//		if(carts != null && ! carts.isEmpty()) {
//			Cart cart = carts.get(0);
//			cart.setCount(cart.getCount() + 1);
//			cart.setSessionId(sessionId);
//			cartDao.save(cart);
//		}else {
//			Cart cart = new Cart();
//			cart.setItemId(itemId);
//			cart.setCount(1);
//			cart.setSessionId(sessionId);
//			cartDao.save(cart);
//		}
		
	}

	@Override
	public List<cartDTO> viewCart(List<ItemCart> ItemsInSession) {
		List<cartDTO> allcart = new ArrayList<cartDTO>();
		for(ItemCart itemCart: ItemsInSession) {
			cartDTO cartDto = new cartDTO();
			cartDto.setCount(ItemsInSession.size());
			Item item = itemDao.findById(itemCart.getItemId()).get();
			cartDto.setItemId(item.getId());
			cartDto.setImg(item.getImg());
			cartDto.setPrice(item.getPrice());
			cartDto.setTitle(item.getTitle());
			cartDto.setItemTotal(item.getPrice() * itemCart.getItemCount());
			
			allcart.add(cartDto);
		}
		return allcart;
	}

	@Override
	public void checkout(Double grandTotal) {
		
		
	}

	@Override
	public Boolean postCheckout(Checkout checkoutDto, List<ItemCart> itemsInSession) {
		User user = new User();
		user.setEmail(checkoutDto.getEmail());
		user.setFirstName(checkoutDto.getFirstName());
		user.setLastName(checkoutDto.getLastName());
		user.setMobile(checkoutDto.getMobile());
		user = userDao.save(user);
		
		Order order = new Order();
		order.setGrandTotal(checkoutDto.getTotal());
		order.setOrderDate(new Date());
		order.setUserId(user.getId());
		if(checkoutDto.getPaymentMethod().equalsIgnoreCase("cash")) {
			order.setStatus("NEW");
		}else {
			order.setStatus("COMPLETE");
		}
		order = orderDao.save(order);
		
		List<Cart> carts = new ArrayList<Cart>();
		for(ItemCart itemCart : itemsInSession) {
			Cart cart = new Cart();
			cart.setItemId(itemCart.getItemId());
			cart.setCount(itemCart.getItemCount());
			cart.setOrderId(order.getId());
			carts.add(cart);
		}
		
		if(!carts.isEmpty()){
			cartDao.saveAll(carts);
		}
		
		sendNotificationToChef(order.getId());
		return true;
	}

	private void sendNotificationToChef(Long orderId) {
		messagingTemplate.convertAndSend("/topic/messages", new OutputMessage(orderId));
		
	}

	@Override
	public void cashCheckout(Long orderId) {
		
		Optional<Order> orderOptional = orderDao.findById(orderId);
		if(orderOptional.isPresent()) {
			Order order = orderOptional.get();
			order.setStatus("COMPLETE");
			
			orderDao.save(order);
		}
		
	}
	
	//chef

	@Override
	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> allOrder = new ArrayList<OrderDTO>();
		List<Order> completeOrders = orderDao.findByStatus("COMPLETE");
		for(Order order : completeOrders) {
			OrderDTO orderdto = new OrderDTO();
			orderdto.setOrderId(String.valueOf(order.getId()));
			orderdto.setUserId(String.valueOf(order.getUserId()));
			orderdto.setGrandTotal(String.valueOf(order.getGrandTotal()));
			
			Optional<User> optionalUser = userDao.findById(order.getUserId());
			if(optionalUser.isPresent()) {
				User user = optionalUser.get();
				orderdto.setEmail(user.getEmail());
				orderdto.setFirstName(user.getFirstName());
				orderdto.setLastName(user.getLastName());
				orderdto.setMobile(user.getMobile());
			}
			List<Cart> carts = cartDao.findByOrderId(order.getId());
			List<cartDTO> items = new ArrayList<cartDTO>();
			for(Cart cart : carts) {
				Optional<Item> optionalItem = itemDao.findById(cart.getItemId());
				cartDTO cartdto = new cartDTO();
				if(optionalItem.isPresent()) {
					
					cartdto.setCount(cart.getCount());
					cartdto.setItemId(cart.getItemId());
					cartdto.setTitle(optionalItem.get().getTitle());
				}
				items.add(cartdto);
				
			}
			
			orderdto.setItems(items);
			
			allOrder.add(orderdto);
		}
		
		return allOrder;
		
	}

	@Override
	public void orderReady(String orderId) {
		Long id = Long.parseLong(orderId);
		
		Optional<Order> optionalOrder = orderDao.findById(id);
		if(optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			order.setStatus("READY");
			
			orderDao.save(order);
			Optional<User> optionalUser = userDao.findById(order.getUserId());
			if(optionalUser.isPresent()) {
				sendEmailToUser(optionalUser.get());
			}
			
		}
		
	}

	private void sendEmailToUser(User user) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(user.getEmail());

	        msg.setSubject("Order Ready");
	        msg.setText("Hello " + user.getFirstName() + " " + "\n Your order is ready to pick");

	        javaMailSender.send(msg);
		} catch (Exception e) {
			System.out.println("Not able to send email " + e.toString());
		}	
	}

	@Override
	public List<AdminViewDTO> getMenuAndItemForAdmin() {
		List<Item> items = itemDao.findAll();
		List<AdminViewDTO> adminViews = new ArrayList<AdminViewDTO>();
		for(Item item : items) {
			AdminViewDTO adminview = new AdminViewDTO();
			adminview.setItemId(item.getId());
			adminview.setImg(item.getImg());
			adminview.setMenuId(item.getMenuId());
			adminview.setMenuName(getMenuNameFromId(item.getMenuId()));
			adminview.setPrice(item.getPrice());
			adminview.setSummary(item.getSummary());
			adminview.setTitle(item.getTitle());
			adminview.setType(item.getType());
			
			adminViews.add(adminview);
			
		}
		
		return adminViews;
		
	}

	private String getMenuNameFromId(Long menuId) {
		Optional<Menu> optionalMenu = menuDao.findById(menuId);
		
		return optionalMenu.isPresent() ? optionalMenu.get().getCategory() : null;
	}

	@Override
	public List<Item> searchItem(String text) {
		List<Item> searchItems = itemDao.searchByText(text);
		return itemDao.searchByText(text);
	}
	
	@Override
	public List<String> getCategoryNameFromMenus(List<Item> searchItems){
		List<String> list = new ArrayList();
		Set<String> menuNames = new HashSet<String>();
		for(Item item:searchItems) {
			String menuName	= getMenuNameFromId(item.getMenuId());
			menuNames.add(menuName);
		}
		
		
		list.addAll(menuNames);
		return list;
	}
}
