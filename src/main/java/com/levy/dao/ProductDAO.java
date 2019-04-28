package com.levy.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Category;
import com.levy.pojo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	Page<Product> findByCategory(Category category,Pageable pageable);
}
