package com.order.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.system.service.WelcomeService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired WelcomeService welcomeService;
	
	@GetMapping(value = {"","/","/home"})
	public String chef(Model model) throws JsonProcessingException{
		
		model.addAttribute("foodItems",welcomeService.getFoodItems());
		model.addAttribute("categories", welcomeService.getAllMenuCategory());
		model.addAttribute("isFromLoggedInUser", true);
		model.addAttribute("popularItems", welcomeService.getMostPopularItems());
		return "main-dashboard";
		
	}

}
