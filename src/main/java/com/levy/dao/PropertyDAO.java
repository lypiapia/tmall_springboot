package com.levy.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Category;
import com.levy.pojo.Property;

public interface PropertyDAO extends JpaRepository<Property, Integer>{	
	Page<Property> findByCategory(Category category,Pageable pageable);
}
