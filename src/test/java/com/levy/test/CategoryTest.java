package com.levy.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.levy.Application;
import com.levy.dao.CategoryDAO;
import com.levy.pojo.Category;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {Application.class})
public class CategoryTest {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	
	@Test
	public void test() {
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = new PageRequest(0, 5,sort);
		Page<Category> page = categoryDAO.findAll(pageable);
		System.out.println("当前页数:"+page.getNumber());
		System.out.println("当前页大小："+page.getSize());
		List<Category> list = page.getContent();
		for (Category category : list) {
			System.out.println(category);
		}
		
		
	}
}
		
	
	
	

