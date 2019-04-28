package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.levy.dao.CategoryDAO;
import com.levy.pojo.Category;
import com.levy.utils.Page4Navigator;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	//不带分页的查询分类
	public List<Category> list(){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		return categoryDAO.findAll(sort);
	}
	
	//带分页的查询分类
	public Page4Navigator<Category> list(int start,int size,int navigatorPages){
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Category> pageFromJPA = categoryDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,navigatorPages);
	}
	
	//新增分类
	public void add(Category bean) {
		categoryDAO.save(bean);
	}
	
	//删除分类
	public void delete(int id) {
		categoryDAO.delete(id);
	}
	
	
	//根据id获得Category
	public Category get(int id) {
		return categoryDAO.findOne(id);
	}
	
	//修改category
	public void update(Category category) {
		categoryDAO.save(category);
	}
	
}
