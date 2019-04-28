package com.levy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.levy.pojo.Property;
import com.levy.service.PropertyService;
import com.levy.utils.Page4Navigator;

@RestController
public class PropertyController {
	@Autowired
	PropertyService propertyService;
	
	@GetMapping("/categories/{cid}/properties")
	public	Page4Navigator<Property> list(@PathVariable("cid") int id,
			@RequestParam(value="start",defaultValue="0") int start,
			@RequestParam(value="size", defaultValue="5") int size){
		start = start < 0 ? 0 : start;
		Page4Navigator<Property> page =  propertyService.list(id, start, size, 5);
		return page;
	}
	
	@GetMapping("/properties/{id}")
	public Property get(@PathVariable("id") int id) throws Exception {
		return propertyService.get(id);
	}
	
	@PostMapping("/properties")
	public void add(@RequestBody Property property) throws Exception {
		propertyService.add(property);
	}
	
	@DeleteMapping("/properties/{id}")
	public String delete(@PathVariable("id") int id) throws Exception{
		propertyService.delete(id);
		return null;
	}
	
	@PutMapping("/properties")
	public Object update(@RequestBody Property property) {
		propertyService.update(property);
		return property;
	}
	
}
