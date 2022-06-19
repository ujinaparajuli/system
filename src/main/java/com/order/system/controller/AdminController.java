package com.order.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
		
		@GetMapping(value = {"","/","/home"})
		public String admin(Model model) {
			
			return "admin-dashboard";
			
		}
		
//		@GetMapping("/all-item")
//		public String allItems(Model model) {
//			
//			return "item";
//			
//		}
//		
//		@PostMapping("/add-item")
//		public String addItem(Model model) {
//			return "allItems";
//			
//		}
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
