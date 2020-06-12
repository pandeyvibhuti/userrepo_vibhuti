package com.test.springboot_demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.springboot_demo.model.User;

public interface UserPagerepo extends CrudRepository<User, Integer>,PagingAndSortingRepository<User, Integer>{
Page<User> findAll(Pageable pageable) ;
	// TODO Auto-generated method stub


	

}
