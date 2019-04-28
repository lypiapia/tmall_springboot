package com.levy.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.levy.pojo.Product;
import com.levy.pojo.ProductImage;
import com.levy.service.ProductImageService;
import com.levy.service.ProductService;
import com.levy.utils.ImageUtil;

@RestController
public class ProductImageController {
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products/{pid}/productImages")
	public List<ProductImage> list(@RequestParam("type") String type,
			@PathVariable("pid") int pid) throws Exception{
		Product product = productService.get(pid);
		
		if(ProductImageService.TYPE_SINGLE.equals(type)) {
			return productImageService.listSingleProductImages(product);
		}else if(ProductImageService.TYPE_DETAIL.equals(type)) {
			return productImageService.listDetailProductImages(product);
		}else {
			return new ArrayList<ProductImage>();
		}
	}
	
	@PostMapping("/productImages")
	public Object add(@RequestParam("pid") int pid, @RequestParam("type") String type,
			MultipartFile image,HttpServletRequest request) throws Exception{
		//将图片信息插入数据库
		ProductImage productImage = new ProductImage();
		Product product = productService.get(pid);
		productImage.setType(type);
		productImage.setProduct(product);
		productImageService.add(productImage);
		
		//保存图片
		String folder = new String("img/");
		
		if(ProductImageService.TYPE_SINGLE.equals(type)) {
			folder += "productSingle";
		}else {
			folder += "productDetail";
		}
		File imageFolder = new File(request.getServletContext().getRealPath(folder));
		File file = new File(imageFolder, productImage.getId()+".jpg");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}
		String fileName = file.getName();
		//将文件保存至指定文件夹
		image.transferTo(file);
		
		//将文件转换为jpg格式
		BufferedImage img = ImageUtil.change2jpg(file);
		ImageIO.write(img, "jpg", file);
		
		//若是single类型图片，分别保存small和middle类型
		if(ProductImageService.TYPE_SINGLE.equals(type)) {
			String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_samll");
			String imageFolder_middle = request.getServletContext().getRealPath("img/productDetail_middle");
			File f_small = new File(imageFolder_small, fileName);
			File f_middle = new File(imageFolder_middle,fileName);
			if(!f_small.getParentFile().exists()) {
				f_small.getParentFile().mkdir();
			}
			if(!f_middle.getParentFile().exists()) {
				f_middle.getParentFile().mkdir();
			}
			ImageUtil.resizeImage(file, 56, 56, f_small);
			ImageUtil.resizeImage(file, 217, 190, f_small);
		}
		return productImage;
	}
	
	@DeleteMapping("/productImages/{id}")
	public String delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception{
		//获取数据库中数据，以便找到磁盘对应图片
		ProductImage productImage = productImageService.get(id);
		//删除数据库中数据
		productImageService.delete(id);
		
		//删除磁盘图片
		String folder = "img/";
		if(ProductImageService.TYPE_SINGLE.equals(productImage.getType())) {
			folder += "productSingle";
		}else if(ProductImageService.TYPE_DETAIL.equals(productImage.getType())) {
			folder += "productDetail";
		}
		File imageFolder = new File(request.getServletContext().getRealPath(folder));
		File file = new File(imageFolder, productImage.getId()+".jpg");
		String fileName = file.getName();
		file.delete();
		
		//删除小型和中型图片
		if(ProductImageService.TYPE_SINGLE.equals(productImage.getType())) {
			String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
			String imageFolder_middle = request.getServletContext().getRealPath("img/productSingle_middle");
			File f_small = new File(imageFolder_small,fileName);
			File f_middle = new File(imageFolder_middle,fileName);
			f_small.delete();
			f_middle.delete();
		}
		return null;
	}
}
