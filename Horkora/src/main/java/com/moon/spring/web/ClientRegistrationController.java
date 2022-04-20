package com.moon.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moon.spring.service.ClientService;
import com.moon.spring.web.dto.ClientRegistrationDto;

@Controller
@RequestMapping("/registration")
public class ClientRegistrationController {

	private ClientService service;

	public ClientRegistrationController(ClientService service) {
		super();
		this.service = service;
	}
	
	@ModelAttribute("client")
	public ClientRegistrationDto clientRegistrationDto() {
		return new ClientRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerClientAccount(@ModelAttribute("client") ClientRegistrationDto registrationDto) {
		service.save(registrationDto);
		return "redirect:/?successReg";
	}
}
