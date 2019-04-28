package com.levy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.Product;
import com.levy.pojo.ProductImage;

public interface ProductImageDAO  extends JpaRepository<ProductImage, Integer>{
	public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product,String type);
}
