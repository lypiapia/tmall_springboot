package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.levy.dao.PropertyDAO;
import com.levy.pojo.Category;
import com.levy.pojo.Property;
import com.levy.utils.Page4Navigator;

@Service
public class PropertyService {
	
	@Autowired
	private PropertyDAO propertyDAO;
	
	@Autowired
	private CategoryService categoryService;
	
	//增加属性
	public void add(Property property) {
		propertyDAO.save(property);
	}
	
	//删除属性
	public void delete(int id) {
		propertyDAO.delete(id);
	}
	
	//更新属性
	public void update(Property property) {
		propertyDAO.save(property);
	}
	
	//根据id查询属性
	public Property get(int id) {
		Property property = propertyDAO.findOne(id);
		return property;
	}
	
	//根据category查询对应分类信息
	public Page4Navigator<Property> list(int cid, int start,int size,int navigatePages){
		Category category = categoryService.get(cid);
		
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Property> pageFromJPA = propertyDAO.findByCategory(category, pageable);
		return new Page4Navigator<>(pageFromJPA, navigatePages);
	}
	
	public List<Property> listByCategory(Category category){
		return propertyDAO.findByCategory(category);
	}
	
}
