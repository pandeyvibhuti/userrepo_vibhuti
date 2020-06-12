package com.test.springboot_demo.repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.print.attribute.standard.PageRanges;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.springboot_demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT " + "    new com.test.springboot_demo.model.User(u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    User u " + " ORDER BY u.experienceLevel ASC")
	List<User> finduserExperience();

	@Query("SELECT " + "    new com.test.springboot_demo.model.User( u.userid, u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    User u " + " WHERE u.experience < 5" )
	List<User> findByExp();

	@Query("SELECT " + "    new com.test.springboot_demo.model.User(u.userid, u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    User u " + " WHERE u.experience >10" )
	List<User> findByExp10();
	
	@Query("SELECT " + "    new com.test.springboot_demo.model.User(u.userid, u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    User u " + " WHERE u.experience BETWEEN 5 AND 10" )
	List<User> findBetweenexperience();

	

	

}
