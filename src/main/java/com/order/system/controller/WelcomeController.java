package com.order.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.MultipartStream.ItemInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.order.system.entity.Item;
import com.order.system.entity.User;
import com.order.system.model.Checkout;
import com.order.system.model.ItemCart;
import com.order.system.model.Message;
import com.order.system.model.OutputMessage;
import com.order.system.model.Viewcart;
import com.order.system.model.cartDTO;
import com.order.system.service.WelcomeService;

@Controller
public class WelcomeController {
	@Autowired WelcomeService welcomeService;
	
	@RequestMapping(value = {"", "/", "/home"})
	public String welcome(Model model, HttpServletRequest request) {
		model.addAttribute("foodItems",welcomeService.getFoodItems());
		model.addAttribute("categories", welcomeService.getAllMenuCategory());
		model.addAttribute("isDashboardContainer", true);
		model.addAttribute("isDashboardNavBar", true);
		model.addAttribute("isDashboardSideBar", true);
		String isFromChekout = (String) request.getAttribute("isCheckoutSuccessful");
		if( isFromChekout != null && isFromChekout.equalsIgnoreCase("true")) {
			System.out.println(isFromChekout);
			model.addAttribute("isCheckoutSuccessful", true);
		}else {
			System.out.println(isFromChekout);
			model.addAttribute("isCheckoutSuccessful", false);
		}
		return "main-dashboard";
	}
	
	@GetMapping("/item/{id}")
	public String getItem(@PathVariable("id") Long id, Model model) {
		model.addAttribute("foodItem",welcomeService.getItem(id));
		return "item";
	}
	
	@GetMapping("/category/{name}")
	public String getItemFromCategory(@PathVariable("name") String category, Model model) {
		model.addAttribute("foodItems",welcomeService.getFoodItemFromCategory(category));
		model.addAttribute("categories", welcomeService.getCategoryNameFromMenus(welcomeService.getFoodItemFromCategory(category)));
		model.addAttribute("isDashboardContainer", true);
		model.addAttribute("isDashboardNavBar", true);
		model.addAttribute("isDashboardSideBar", true);
		return "main-dashboard";
	}
	
	@PostMapping("/add/{itemId}")
	public String addToCart(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request) {		
		List<ItemCart> itemsInSession = (List<ItemCart>) request.getSession().getAttribute("CART_SESSION");
		
		if(itemsInSession == null || itemsInSession.isEmpty()) {
			itemsInSession = new ArrayList<ItemCart>();
			ItemCart itemCart = new ItemCart();
			itemCart.setItemId(itemId);
			itemCart.setItemCount(1);
			itemsInSession.add(itemCart);
		}else {
			boolean isFound = false;
			for(ItemCart itemCart : itemsInSession) {
				if(itemId.equals(itemCart.getItemId())) {
					isFound = true;
					itemCart.setItemCount(itemCart.getItemCount() + 1);
				}
			}
			if(!isFound) {
				ItemCart itemCart = new ItemCart();
				itemCart.setItemId(itemId);
				itemCart.setItemCount(1);
				itemsInSession.add(itemCart);
			}
		}
		
		request.getSession().setAttribute("CART_SESSION", itemsInSession);
		
		return "redirect:/";
	}
	
	@GetMapping("/cart/view")
	public ResponseEntity viewCart(HttpSession session) {
		Viewcart viewCart = new Viewcart();
		
		List<ItemCart> itemsInSession = (List<ItemCart>) session.getAttribute("CART_SESSION");
		if(itemsInSession == null || itemsInSession.isEmpty()) {
			viewCart.setCartEmpty(true);
			return new ResponseEntity<Viewcart>(viewCart, HttpStatus.OK);
		}else {
			List<cartDTO> cartsDtos = welcomeService.viewCart(itemsInSession);
			Double grandTotal = 0.0;
			for(cartDTO cartDto : cartsDtos) {
				grandTotal = grandTotal + cartDto.getItemTotal();
			}
			double tax = grandTotal * 7 / 100;
			viewCart.setCartDtos(cartsDtos);
			viewCart.setCartEmpty(false);
			viewCart.setGrandTotal(String.valueOf(grandTotal));
			viewCart.setGrandtotalWithTax(String.valueOf(grandTotal+tax));
			viewCart.setItemCount(String.valueOf(cartsDtos.size()));
			return new ResponseEntity<Viewcart>(viewCart, HttpStatus.OK);
		}
	}
	

	@PostMapping("/checkout/{grandTotal}")
	public String checkout(@PathVariable("grandTotal") Double grandTotal, Model model) {
		model.addAttribute("total", grandTotal);
		model.addAttribute("checkout", new Checkout());
		model.addAttribute("isCheckoutContainer", true);
		model.addAttribute("isDashboardNavBar", true);
		return "main-dashboard";
	}
	
	@GetMapping("/save/view")
	public String viewAllSaveItem(Model model, HttpSession session) {
		return "view";
	}
	
	@GetMapping("/save/view/{userId")
	public String viewOrderItem(@PathVariable("userId") Long userId, Model model) {
		return "order/:";
	}
	
	@PostMapping("/postcheckout")
	public String postcheckout(RedirectAttributes redirectAttributes, HttpServletRequest request, @ModelAttribute("checkout") Checkout checkout) {
		List<ItemCart> itemsInSession = (List<ItemCart>) request.getSession().getAttribute("CART_SESSION");
		welcomeService.postCheckout(checkout,itemsInSession);
		request.getSession().invalidate();
		
		request.setAttribute("isCheckoutSuccessful", "true");
		
        return "redirect:/";
		
	}

	@GetMapping("/manager")
	public String manager(Model model) {
		return "manager";
	}
	
	@PostMapping("/cash/checkout/{orderId}")
	public String cashCheckout(Model model,@PathVariable("orderId")Long OrderId) {
		System.out.println(OrderId);
		return "success";
	}
	
//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	public OutputMessage send(Message message) throws Exception {
//	    String time = new SimpleDateFormat("HH:mm").format(new Date());
//	    return new OutputMessage(message.getFrom(), message.getText(), time);
//	}
	
	@GetMapping("/search")
	public String search(Model model,@RequestParam("text") String text) {
		List<Item> searchItems = welcomeService.searchItem(text);
		model.addAttribute("foodItems", searchItems);
		model.addAttribute("categories", welcomeService.getCategoryNameFromMenus(searchItems));
		
		return "dashboard";
	}
}