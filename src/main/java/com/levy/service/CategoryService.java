package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.levy.dao.CategoryDAO;
import com.levy.pojo.Category;
import com.levy.utils.Page4Navigator;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	public List<Category> list(){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		return categoryDAO.findAll(sort);
	}
	
	public Page4Navigator<Category> list(int start,int size,int navigatorPages){
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Category> pageFromJPA = categoryDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,navigatorPages);
	}
	
}
