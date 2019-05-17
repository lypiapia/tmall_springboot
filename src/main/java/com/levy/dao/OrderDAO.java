package com.levy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{
	
}
