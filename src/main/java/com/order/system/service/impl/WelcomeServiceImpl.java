package com.order.system.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.order.system.dao.CartDao;
import com.order.system.dao.ImageDao;
import com.order.system.dao.ItemDao;
import com.order.system.dao.MenuDao;
import com.order.system.dao.OrderDao;
import com.order.system.dao.ReviewDao;
import com.order.system.dao.RoleDao;
import com.order.system.dao.UserDao;
import com.order.system.entity.Cart;
import com.order.system.entity.Image;
import com.order.system.entity.Item;
import com.order.system.entity.Menu;
import com.order.system.entity.Order;
import com.order.system.entity.Review;
import com.order.system.entity.Role;
import com.order.system.entity.User;
import com.order.system.model.AdminViewDTO;
import com.order.system.model.Checkout;
import com.order.system.model.ItemCart;
import com.order.system.model.OrderDTO;
import com.order.system.model.OutputMessage;
import com.order.system.model.ReviewDTO;
import com.order.system.model.SignUp;
import com.order.system.model.cartDTO;
import com.order.system.service.WelcomeService;
import com.order.system.validator.ImageUtility;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
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
	ReviewDao reviewDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	ImageDao imageDao;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
    JavaMailSender javaMailSender;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<Item> getFoodItems() {
		return itemDao.findAll();
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
		}else if("today-special".equalsIgnoreCase(category)) {
			return null;
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

//	@Override
//	public void addToCart(Long itemId, String sessionId) {
//		List<Cart> carts = cartDao.findBySessionId(sessionId);
//		int count = 0;
//		for(Cart cart : cartDao.findBySessionId(sessionId)) {
//			if(itemId.equals(cart.getItemId())) {
//				cart.setCount(cart.getCount() + 1);
//				cartDao.save(cart);
//				count++;
//				break;
//			}
//		}
//		
//		if(count == carts.size()) {
//			Cart cart = new Cart();
//			cart.setItemId(itemId);
//			cart.setCount(1);
//			cart.setSessionId(sessionId);
//			cartDao.save(cart);
//		}
////		List<Cart> carts = cartDao.findByItemId(itemId);
////		if(carts != null && ! carts.isEmpty()) {
////			Cart cart = carts.get(0);
////			cart.setCount(cart.getCount() + 1);
////			cart.setSessionId(sessionId);
////			cartDao.save(cart);
////		}else {
////			Cart cart = new Cart();
////			cart.setItemId(itemId);
////			cart.setCount(1);
////			cart.setSessionId(sessionId);
////			cartDao.save(cart);
////		}
//		
//	}

	@Override
	public List<cartDTO> viewCart(List<ItemCart> ItemsInSession) {
		List<cartDTO> allcart = new ArrayList<cartDTO>();
		for(ItemCart itemCart: ItemsInSession) {
			cartDTO cartDto = new cartDTO();
			cartDto.setCount(itemCart.getItemCount());
			Item item = itemDao.findById(itemCart.getItemId()).get();
			cartDto.setItemId(item.getId());
			cartDto.setImage(item.getImage());
			cartDto.setImageName(item.getImageName());
			cartDto.setImageType(item.getImageType());
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
	public Long postCheckout(Checkout checkoutDto, List<ItemCart> itemsInSession) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = null;
		
		if(auth.getPrincipal() instanceof String) {
			user = new User();
			user.setEmail(checkoutDto.getEmail());
			user.setFirstName(checkoutDto.getFirstName());
			user.setLastName(checkoutDto.getLastName());
			user.setMobile(checkoutDto.getMobile());
			user.setPassword("password");
			user = userDao.save(user);
		} else if(auth.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
			org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			try {
				user = userDao.findByEmail(springUser.getUsername());
			} catch (Exception e) {
				
				System.out.println(e);
				
			}
		}
		
		Order order = new Order();
		order.setGrandTotal(checkoutDto.getTotal());
		order.setOrderDate(new Date());
		order.setUserId(user.getId());
//		if(checkoutDto.getPaymentMethod().equalsIgnoreCase("cash")) {
//			order.setStatus("NEW");
//		}else {
//			order.setStatus("COMPLETE");
//		}
		order.setStatus("COMPLETE");
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
		return order.getId();
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
//			adminview.setImg(item.getImg());
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
		List<Item> searchItems = itemDao.findByTitleContaining(text);
		return searchItems;
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

	@Override
	public void deleteItemAdmin(Long itemId) {
		Optional<Item> optionalItem = itemDao.findById(itemId);
		if(optionalItem.isPresent()) {
			long menuId = optionalItem.get().getMenuId();
			itemDao.deleteById(itemId);
			List<Item> items = itemDao.findByMenuId(menuId);
			if(items == null || items.isEmpty()) {
				menuDao.deleteById(menuId);
			}
		}
	}
	
	
	@Transactional
	@Override
	public void addItemAdmin(AdminViewDTO adminView) {
		
		Item item = new Item();
		item.setPrice(adminView.getPrice());
		item.setSummary(adminView.getSummary());
		item.setTitle(adminView.getTitle());
		item.setMenuId(getMenuIdFromName(adminView.getMenuName()));
		try {
			item.setImage(org.apache.tomcat.util.codec.binary.Base64.encodeBase64(adminView.getImg().getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imageName = "";
		try {
			imageName = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(org.apache.tomcat.util.codec.binary.Base64.encodeBase64(adminView.getImg().getBytes(), false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		item.setImageName(imageName);
		item.setImageType(adminView.getImg().getContentType());
		
		itemDao.save(item);
	}

	private Long getMenuIdFromName(String menuName) {
		List<Menu> menus = menuDao.findByCategory(menuName);
		if(menus != null && ! menus.isEmpty()) {
			Menu menu = menus.get(0);
			return menu.getId();
		}else {
			Menu menu = new Menu();
			menu.setCategory(menuName);
			Menu m = menuDao.save(menu);
			return m.getId();
		}
	}

	@Override
	public void editItemAdmin(AdminViewDTO adminView, Long itemId) {
		Optional<Item> opItem = itemDao.findById(itemId);
		if(opItem.isPresent()) {
			Item item = opItem.get();
//			item.setImg(adminView.getImg());
			item.setPrice(adminView.getPrice());
			item.setSummary(adminView.getSummary());
			item.setTitle(adminView.getTitle());
			
			itemDao.save(item);
			
			Optional<Menu> menuOp = menuDao.findById(item.getMenuId());
			
			if(menuOp.isPresent()) {
				Menu menu = menuOp.get();
				menu.setCategory(adminView.getMenuName());
				
				menuDao.save(menu);
			}
		}		
	}

	@Override
	public List<Item> getMostPopularItems() {
		List<Item> allItem = itemDao.findAll();
		allItem.sort(Comparator.comparing(Item::getAvgReview).reversed());
		List<Item> first5ItemsList = allItem.stream().limit(5).collect(Collectors.toList());
		
		return first5ItemsList;
	}
	
	
	// function to sort hashmap by values
    private HashMap<Long, Integer> sortByValue(Map<Long, Integer> hm)
    {
    	List<Map.Entry<Long, Integer> > list =
                new LinkedList<Map.Entry<Long, Integer> >(hm.entrySet());
    	
    	Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {

			@Override
			public int compare(Map.Entry<Long, Integer> o1,
                    Map.Entry<Long, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
         
        // put data from sorted list to hashmap
        HashMap<Long, Integer> temp = new LinkedHashMap<Long, Integer>();
        for (Map.Entry<Long, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

	@Override
	public boolean addReview(Long itemId, Long userId, ReviewDTO reviewDto) {
		
		Review review = reviewDao.findByItemIdAndUserId(itemId, userId).get(0);
		
		if(review == null) {
			Review newReview = new Review();
			newReview.setItemId(itemId);
			newReview.setUserId(userId);
			newReview.setReviewText(reviewDto.getText());
			newReview.setReviewCount(reviewDto.getCount());
			
			reviewDao.save(newReview);
		}else {
			review.setReviewCount(reviewDto.getCount());
			review.setReviewText(reviewDto.getText());
			reviewDao.save(review);
		}
		return true;
	}

	@Override
	public boolean signUpService(SignUp signUp) {
		User existingUser = userDao.findByEmail(signUp.getEmail());
		
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            return false;
        }
        
        User user = new User();
        user.setFirstName(signUp.getfName());
        user.setLastName(signUp.getlName());
        user.setEmail(signUp.getEmail());
        
        user.setPassword(passwordEncoder.encode(signUp.getConfirm()));
        
        user.setMobile(signUp.getMobile());
        
        Role role = roleDao.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        
        user.setRoles(Arrays.asList(role));
        
        userDao.save(user);
        
		return true;
	}
	
	private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleDao.save(role);
    }

	@Override
	public boolean saveCartSession(List<ItemCart> itemcarts) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		User user = null;
		try {
			user = userDao.findByEmail(springUser.getUsername());
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
		if(itemcarts != null && !itemcarts.isEmpty()) {
			for(ItemCart itemCart : itemcarts) {
				Cart cart = new Cart();
				cart.setItemId(itemCart.getItemId());
				cart.setCount(itemCart.getItemCount());
				cart.setUserId(1L);
				
				cartDao.save(cart);
			}
		}
		
		return true;
	}

	@Override
	public List<ItemCart> getItemCarts(String userName) {
		User user = userDao.findByEmail(userName);
		List<ItemCart> items = new ArrayList<ItemCart>();
		List<Cart> carts = cartDao.findByOrderIdAndUserId(null, user.getId());
		if(carts != null && !carts.isEmpty()) {
			for(Cart cart : carts) {
				ItemCart itemcart = new ItemCart();
				itemcart.setItemId(cart.getItemId());
				itemcart.setItemCount(cart.getCount());
				
				items.add(itemcart);
			}
			return items;
		}else {
			return items;
		}
	}
}
