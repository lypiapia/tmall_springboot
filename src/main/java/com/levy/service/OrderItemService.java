package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levy.dao.OrderItemDAO;
import com.levy.pojo.Order;
import com.levy.pojo.OrderItem;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemDAO orderItemDAO;
	
	@Autowired
	private ProductImageService productImageService;
	
	public void fill(List<Order> orders) {
		for (Order order : orders) {
			fill(order);
		}
	}
	
	private void fill(Order order) {
		List<OrderItem> orderItems = listByOrder(order);
		float total = 0;
		int totalNumber = 0;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
			totalNumber += orderItem.getNumber();
			productImageService.setFirstProdutImage(orderItem.getProduct());
		}
		order.setTotal(total);
		order.setTotalNumber(totalNumber);
		order.setOrderItems(orderItems);
	}
	
	public List<OrderItem> listByOrder(Order order){
		return orderItemDAO.findByOrderOrderByIdDesc(order);
	}
	
}
