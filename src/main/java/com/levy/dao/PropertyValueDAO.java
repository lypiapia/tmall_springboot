package com.levy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Product;
import com.levy.pojo.Property;
import com.levy.pojo.PropertyValue;

public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer>{
	//根据产品查询propertyValue
	List<PropertyValue> findByProductOrderByIdDesc(Product product);
	
	//根据产品和属性获取PropertyValue
	PropertyValue getByPropertyAndProduct(Property property,Product product);
}
