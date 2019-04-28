package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levy.dao.ProductImageDAO;
import com.levy.pojo.Product;
import com.levy.pojo.ProductImage;

@Service
public class ProductImageService {
	
	public static final String TYPE_SINGLE = "single";	//产品图片类型
	public static final String TYPE_DETAIL = "detail";	//详情图片类型
	
	@Autowired
	private ProductImageDAO productImageDAO ;
	
	public void add(ProductImage image) {
		productImageDAO.save(image);
	}
	
	public void delete(int id) {
		productImageDAO.delete(id);
	}
	
	public ProductImage get(int id) {
		return productImageDAO.getOne(id);
	}
	
	public List<ProductImage> listSingleProductImages(Product product){
		return productImageDAO.findByProductAndTypeOrderByIdDesc(product, TYPE_SINGLE);
	}
	
	public List<ProductImage> listDetailProductImages(Product product){
		return productImageDAO.findByProductAndTypeOrderByIdDesc(product, TYPE_DETAIL);
	}
	
	
	 public void setFirstProdutImage(Product product) {
	        List<ProductImage> singleImages = listSingleProductImages(product);
	        if(!singleImages.isEmpty())
	            product.setFirstProductImage(singleImages.get(0));
	        else
	            product.setFirstProductImage(new ProductImage()); 
	      //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。
	    }
	    public void setFirstProdutImages(List<Product> products) {
	        for (Product product : products)
	            setFirstProdutImage(product);
	    }
}

