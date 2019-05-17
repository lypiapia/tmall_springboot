package com.levy.controller;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.levy.dto.Result;
import com.levy.pojo.Order;
import com.levy.service.OrderItemService;
import com.levy.service.OrderService;
import com.levy.utils.Page4Navigator;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("/orders")
	public Page4Navigator<Order> list(@RequestParam(value = "start",defaultValue ="0") int start,
			@RequestParam(value = "size",defaultValue = "5") int size)throws Exception{
		start = start < 0 ? 0 : start;
		Page4Navigator<Order> page4Navigator = orderService.list(start, size, 5);
		orderItemService.fill(page4Navigator.getContent());
		orderService.removeOrderFromOrderItem(page4Navigator.getContent());
		return page4Navigator;
	}
	
	@PutMapping("/deliveryOrder/{oid}")
	public Object deliveryOrder(@PathVariable int oid)throws Exception{
		Order order = orderService.get(oid);
		order.setDeliveryDate(new Date());
		order.setStatus(OrderService.WAIT_CONFIRM);
		orderService.update(order);
		return Result.success();
	}
}
