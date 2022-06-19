package com.order.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order.system.service.WelcomeService;

@Controller
@RequestMapping("/chef")
public class ChefController {
	@Autowired WelcomeService welcomeService;
	

	@GetMapping(value = {"","/","/home"})
	public String chef(Model model){
		model.addAttribute("orders", welcomeService.getAllOrders());

		return "chef-dashboard";
		
	}
}
