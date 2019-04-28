package com.levy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.levy.dao.ProductDAO;
import com.levy.pojo.Category;
import com.levy.pojo.Product;
import com.levy.utils.Page4Navigator;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryService categoryService;
	
	public void add(Product product) {
		productDAO.save(product);
	}
	
	public void delete(int id) {
		productDAO.delete(id);
	}
	
	public Product get(int id) {
		return productDAO.findOne(id);
	}
	
	public void update(Product product) {
		productDAO.save(product);
	}
	
	public Page4Navigator<Product> list(int cid,int start,int size,int navigatePages){
		Category category = categoryService.get(cid);
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Product> pageFromJPA =  productDAO.findByCategory(category, pageable);
		return new Page4Navigator<>(pageFromJPA, navigatePages);
	}
}
