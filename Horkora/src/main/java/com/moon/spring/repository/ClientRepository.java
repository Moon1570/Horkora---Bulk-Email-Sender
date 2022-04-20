package com.moon.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moon.spring.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Client findByEmail(String email);
}
