package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.levy.dao.CategoryDAO;
import com.levy.pojo.Category;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	public List<Category> list(){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		return categoryDAO.findAll(sort);
	}
	
}
