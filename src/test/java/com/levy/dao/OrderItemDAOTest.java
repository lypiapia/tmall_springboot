package com.levy.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.levy.Application;
import com.levy.pojo.Order;
import com.levy.pojo.OrderItem;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class OrderItemDAOTest {

	@Autowired
	OrderItemDAO orderitemdao;
	@Test
	public void test() {
		Order order = new Order();
		order.setId(1);
		List<OrderItem> list = orderitemdao.findByOrderOrderByIdDesc(order);
		System.out.println(list);
	}

}
