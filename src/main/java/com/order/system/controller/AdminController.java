package com.order.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order.system.model.AdminViewDTO;
import com.order.system.service.WelcomeService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired 
	WelcomeService welcomeService ;
		
		@GetMapping(value = {"","/","/home"})
		public String admin(Model model) {
			List<AdminViewDTO> adminviews = welcomeService.getMenuAndItemForAdmin();
			model.addAttribute("adminviews", adminviews);
			model.addAttribute("isFromAdmin", true);
			model.addAttribute("addItem", new AdminViewDTO());
			return "main-dashboard";
			
		}
		
		@GetMapping(value = "/item/delete/{itemId}")
		public String deleteItem(Model model, @PathVariable("itemId") Long itemId) {
			welcomeService.deleteItemAdmin(itemId);
			return "redirect:/admin/";
			
		}
		
//		@GetMapping("/all-item")
//		public String allItems(Model model) {
//			
//			return "item";
//			
//		}
//		
		@PostMapping("/add/item")
		public String addItem(Model model, @ModelAttribute("addItem")AdminViewDTO item) {
			welcomeService.addItemAdmin(item);
			return "redirect:/admin/";
			
		}
//		
//		@DeleteMapping("/delete-item")
//		public String deleteItem() {
//			return "allItem";
//			
//		}
//		
//		@PutMapping("/updateItem")
//		public String updateItem() {
//			return "/item:";
//			
//		}
//		
//		@GetMapping("/viewItem/{itemId}")
//		public String viewItem(@PathVariable("itemId") Long itemId, Model model) {
//			return "view";
//			
//		}

}
