package com.levy.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.levy.pojo.Category;
import com.levy.service.CategoryService;
import com.levy.utils.ImageUtil;
import com.levy.utils.Page4Navigator;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
//	@GetMapping("/categories")
//	public List<Category> list() throws Exception{
//		return categoryService.list();
//	}
	
	//修改list方法，增加start和size参数,即增加分页功能
	@GetMapping("/categories")
	public Page4Navigator<Category> list(@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size){
		start = start < 0 ? 0 : start;
		Page4Navigator<Category> page = categoryService.list(start, size, 5); //默认导航栏显示5个页数
		return page;
	}
	
	@PostMapping("/categories")
	public Object add(Category bean,MultipartFile image,HttpServletRequest request) throws IllegalStateException, IOException {
		categoryService.add(bean);	//保存category数据
		saveOrUpdateImageFile(bean,image,request);
		return bean;
	}
	
	@DeleteMapping("/categories/{id}")
	public String delete(@PathVariable("id")int id,HttpServletRequest request)  throws Exception{
		//在数据库中删除记录
		categoryService.delete(id);
		//将对应分类的图片删除
		File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,id+".jpg");
		file.delete();
		return null;
	}
	 
	@DeleteMapping("/deleteAll")
	public String deleteAll(HttpServletRequest request) {
		categoryService.delteAll();
		File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
		imageFolder.delete();
		System.out.println("删除所有成功");
		return null;
	}
	
	@GetMapping(value="/categories/{id}")
	public Category getCategoryById(@PathVariable int id) throws Exception {
		Category category = categoryService.get(id);
		return category;
	}
	
	@PutMapping(value="/categories/{id}")
	public Object update(Category category,MultipartFile image, HttpServletRequest request) throws IOException {
		String name = request.getParameter("name");
		category.setName(name);
		categoryService.update(category);
		if(image != null) {
			saveOrUpdateImageFile(category,image,request);
		}
		
		return category;
	}
	
	
	 public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
	            throws IOException {
	        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
	        File file = new File(imageFolder,bean.getId()+".jpg");
	        if(!file.getParentFile().exists())
	            file.getParentFile().mkdirs();
	        image.transferTo(file);
	        BufferedImage img = ImageUtil.change2jpg(file);
	        ImageIO.write(img, "jpg", file);
	    }
}
