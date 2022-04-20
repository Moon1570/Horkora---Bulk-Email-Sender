package com.moon.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.moon.spring.model.Client;
import com.moon.spring.web.dto.ClientRegistrationDto;

public interface ClientService extends UserDetailsService{

	Client save(ClientRegistrationDto clientRegistrationDto);
}
