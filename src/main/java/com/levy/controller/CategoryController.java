package com.levy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.levy.pojo.Category;
import com.levy.service.CategoryService;
import com.levy.utils.Page4Navigator;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
//	@GetMapping("/categories")
//	public List<Category> list() throws Exception{
//		return categoryService.list();
//	}
	
	//修改list方法，增加start和size参数,即增加分页功能
	@GetMapping("/categories")
	public Page4Navigator<Category> list(@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size){
		start = start < 0 ? 0 : start;
		Page4Navigator<Category> page = categoryService.list(start, size, 5); //默认导航栏显示5个页数
		return page;
	}
	
}
