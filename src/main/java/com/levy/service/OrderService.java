package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.levy.dao.OrderDAO;
import com.levy.pojo.Order;
import com.levy.pojo.OrderItem;
import com.levy.utils.Page4Navigator;

@Service
public class OrderService {
	public static final String WAIT_PAY = "waitpay";
	public static final String WAIT_DELIVERY = "waitDelivery";
	public static final String WAIT_CONFIRM = "waitConfirm";
	public static final String WAIT_REVIEW = "waitReview";
	public static final String FINISH = "finish";
	public static final String DELETE = "delete";
	
	@Autowired
	private OrderDAO orderDAO;
	
	public Page4Navigator<Order> list(int start,int size,int navigatePages){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Order> pageFromJPA =  orderDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA, navigatePages);
	}
	
	public void removeOrderFromOrderItem(List<Order> orders) {
		for (Order order : orders) {
			removeOrderFromOrderItem(order);
		}
	}
	
	//此方法用于把订单里的订单项的订单属性设置为空，防止转换为json时循环引用无限递归
	private void removeOrderFromOrderItem(Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrder(null);
		}
	}
	
	public Order get(int oid) {
		return orderDAO.findOne(oid);
	}
	
	public void update(Order order) {
		orderDAO.save(order);
	}
	
}
