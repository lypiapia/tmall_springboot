package com.levy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.levy.pojo.Product;
import com.levy.pojo.PropertyValue;
import com.levy.service.ProductService;
import com.levy.service.PropertyValueService;

@RestController
public class PropertyValueController {
	
	@Autowired
	private PropertyValueService propertyValueService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("products/{id}/propertyValues")
	public List<PropertyValue> list(@PathVariable int id) throws Exception{
		Product product =  productService.get(id);
		propertyValueService.init(product);
		List<PropertyValue> list = propertyValueService.list(product);
		return list;
	}
	
	@PutMapping("/propertyValues")
	public Object update(@RequestBody PropertyValue propertyValue) throws Exception{
		propertyValueService.update(propertyValue);
		return propertyValue;
	}
}
