package com.order.system.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.order.system.service.WelcomeService;

@Controller
@RequestMapping("/chef")
public class ChefController {
	@Autowired WelcomeService welcomeService;
	

	@GetMapping(value = {"","/","/home"})
	public String chef(Model model) throws JsonProcessingException{
		model.addAttribute("orders", welcomeService.getAllOrders());
		String jsonStr = new Gson().toJson(welcomeService.getAllOrders());
		model.addAttribute("jsonOrders", jsonStr);
		model.addAttribute("isFromChef", true);
		return "main-dashboard";
		
	}
	
	@GetMapping(value = "/order/{orderId}")
	public String orderReady(@PathVariable("orderId") String orderId, Model model) {
		welcomeService.orderReady(orderId);
		return "redirect:/chef/";
	}
}
