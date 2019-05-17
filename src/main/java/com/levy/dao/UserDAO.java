package com.levy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levy.pojo.User;

public interface UserDAO extends JpaRepository<User, Integer>{

}
