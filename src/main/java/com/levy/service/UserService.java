package com.levy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.levy.dao.UserDAO;
import com.levy.pojo.User;
import com.levy.utils.Page4Navigator;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public Page4Navigator<User> list(int start,int size,int navigatePages){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<User> pageFromJPA = userDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA, navigatePages);
	}
}
