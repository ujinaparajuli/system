package com.order.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.MultipartStream.ItemInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
		model.addAttribute("popularItems", welcomeService.getMostPopularItems());
		return "main-dashboard";
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
			int size = 0;
			for(ItemCart itemCart : itemsInSession) {
				if(itemId.longValue() == itemCart.getItemId().longValue()) {
					itemCart.setItemCount(itemCart.getItemCount() + 1);
					break;
				}else {
					size++;
				}
			}
			if(size == itemsInSession.size()) {
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
		
		viewCart = getViewCart(itemsInSession);
		
		return new ResponseEntity<Viewcart>(viewCart, HttpStatus.OK);
	}
	
	private Viewcart getViewCart(List<ItemCart> items) {
		Viewcart viewCart = new Viewcart();
		if(items == null || items.isEmpty()) {
			viewCart.setCartEmpty(true);
		}else {
			List<cartDTO> cartsDtos = welcomeService.viewCart(items);
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
		}
		
		return viewCart;
	}
	

	@PostMapping("/checkout")
	public String checkout(HttpSession session, Model model) {

		List<ItemCart> itemsInSession = (List<ItemCart>) session.getAttribute("CART_SESSION");
		
		Viewcart viewCart = getViewCart(itemsInSession);
		
		model.addAttribute("total", viewCart.getGrandtotalWithTax());
		model.addAttribute("checkout", new Checkout());
		model.addAttribute("isCheckoutContainer", true);
		model.addAttribute("isCheckOutNavBar", true);
		return "main-dashboard";
	}
	
	@PostMapping("/postcheckout")
	public RedirectView postcheckout(RedirectAttributes redirectAttributes, HttpServletRequest request, @ModelAttribute("checkout") Checkout checkout) {
		List<ItemCart> itemsInSession = (List<ItemCart>) request.getSession().getAttribute("CART_SESSION");
		Long orderId= welcomeService.postCheckout(checkout,itemsInSession);
		request.getSession().invalidate();
		
		
		RedirectView redirectView= new RedirectView("/",true);
		redirectAttributes.addFlashAttribute("isCheckoutSuccessful",true);
		redirectAttributes.addFlashAttribute("orderId",orderId);
	    return redirectView;
		
	}
	
	@PostMapping("/search")
	public String search(Model model,@RequestParam("text") String text) {
		List<Item> searchItems = welcomeService.searchItem(text);
		model.addAttribute("foodItems", searchItems);
		model.addAttribute("categories", welcomeService.getCategoryNameFromMenus(searchItems));
		
		model.addAttribute("isDashboardContainer", true);
		model.addAttribute("isDashboardNavBar", true);
		model.addAttribute("isDashboardSideBar", true);
		
		return "main-dashboard";
	}
	
	@GetMapping(value="/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
	@GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");
        
        model.addAttribute("isFromLogin", true);

        return "main-dashboard";
    }
}