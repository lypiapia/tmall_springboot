package com.levy.dao;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.levy.Application;
import com.levy.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {Application.class})
public class ProductDAOTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void testProductService() {
		System.out.println(productService.get(2));
	}
	@Test
	public void testPage4Navigator() {
		System.out.println(productService.list(8, 0, 5, 2));
	}

}
