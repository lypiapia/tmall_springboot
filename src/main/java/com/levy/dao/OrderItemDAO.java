package com.levy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Order;
import com.levy.pojo.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer>{
	//根据订单查询订单项列表
	List<OrderItem> findByOrderOrderByIdDesc(Order order);
}
