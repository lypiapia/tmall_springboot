package com.levy.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.levy.pojo.Product;
import com.levy.service.ProductImageService;
import com.levy.service.ProductService;
import com.levy.utils.Page4Navigator;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductImageService productImageService;
	
	@GetMapping("/categories/{cid}/products")
	public Page4Navigator<Product> list(@PathVariable("cid") int cid,
			@RequestParam(value="start",defaultValue="0") int start,
			@RequestParam(value="size",defaultValue="5") int size)throws Exception{
		start = start < 0 ? 0 : start;
		Page4Navigator<Product> page4Navigator =  productService.list(cid, start, size, 5);
		//设置产品图片
		productImageService.setFirstProdutImages(page4Navigator.getContent());
		page4Navigator.getContent();
		for(Product list: page4Navigator.getContent()) {
			System.out.println(list.getFirstProductImage());
		}
		return page4Navigator;
	}
	
	@GetMapping("/products/{id}")
	public Product get(@PathVariable("id") int id) throws Exception{
		return productService.get(id);
	}
	
	@PostMapping("/products")
	public Object add(@RequestBody Product product) throws Exception{
		product.setCreateDate(new Date());
		productService.add(product);
		return product;
	}
	
	@DeleteMapping("/products/{id}")
	public String delete(@PathVariable("id") int id) throws Exception{
		productService.delete(id);
		return null;
	}
	
	@PutMapping("/products")
	public Object update(@RequestBody Product product) throws Exception{
		productService.update(product);
		return product;
	}
	
}
