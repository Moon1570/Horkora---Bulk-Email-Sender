package com.moon.spring.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moon.spring.model.Client;
import com.moon.spring.model.Role;
import com.moon.spring.repository.ClientRepository;
import com.moon.spring.web.dto.ClientRegistrationDto;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
		
	public ClientServiceImpl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}


	@Override
	public Client save(ClientRegistrationDto clientRegistrationDto) {
		// TODO Auto-generated method stub
		Client client = new Client(
				clientRegistrationDto.getName(),
				clientRegistrationDto.getEmail(),
				passwordEncoder.encode(clientRegistrationDto.getPassword()),
				Arrays.asList(new Role("ROLE_USER")));
		
		return clientRepository.save(client);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Client client = clientRepository.findByEmail(username);
		
		if(client == null) {
			throw new UsernameNotFoundException("This email is not registered");
		}
		
		return new User(client.getEmail(), client.getPassword(), mapRolesToAuthorities(client.getRoles()));
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());		
	}

}
