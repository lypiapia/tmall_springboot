package com.levy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{
	
}
