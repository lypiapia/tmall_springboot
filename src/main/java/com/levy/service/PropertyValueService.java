package com.levy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levy.dao.PropertyValueDAO;
import com.levy.pojo.Category;
import com.levy.pojo.Product;
import com.levy.pojo.Property;
import com.levy.pojo.PropertyValue;

@Service
public class PropertyValueService {

	@Autowired
	private PropertyValueDAO propertyValueDAO;
	
	@Autowired
	private PropertyService propertyService;
	
	/*
	 * 初始化的意思是：
	1 这个方法的作用是初始化PropertyValue。 为什么要初始化呢？ 
	因为对于PropertyValue的管理，没有增加，只有修改。 所以需要通过初始化来进行自动地增加，以便于后面的修改。
	2 首先根据产品获取分类，然后获取这个分类下的所有属性集合
	3 然后用属性id和产品id去查询，看看这个属性和这个产品，是否已经存在属性值了。
	4 如果不存在，那么就创建一个属性值，并设置其属性和产品，接着插入到数据库中。
	 */
	public void init(Product product) {
		Category category = product.getCategory();
		List<Property> properties = propertyService.listByCategory(category);
		for (Property property : properties) {
			PropertyValue propertyValue = getByPropertyAndProduct(property, product);
			if(propertyValue == null) {
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValueDAO.save(propertyValue);
			}
		}
	}
	
	public PropertyValue getByPropertyAndProduct(Property property,Product product) {
		return propertyValueDAO.getByPropertyAndProduct(property, product);
	}
	
	public void update(PropertyValue propertyValue) {
		propertyValueDAO.save(propertyValue);
	}
	
	public List<PropertyValue> list(Product product){
		return propertyValueDAO.findByProductOrderByIdDesc(product);
	}
}
