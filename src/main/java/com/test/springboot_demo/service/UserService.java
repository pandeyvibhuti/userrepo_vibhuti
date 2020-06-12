package com.test.springboot_demo.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.print.attribute.standard.PageRanges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import com.test.springboot_demo.error.UserNotFoundException;
import com.test.springboot_demo.model.User;
import com.test.springboot_demo.repository.UserPagerepo;
import com.test.springboot_demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserPagerepo userPagerepo;
	@Autowired
	UserRepository repository;

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> books = new ArrayList<User>();
		userRepository.findAll().forEach(b -> books.add(b));
		return books;
	}

	public User getuserById(int bookid) throws UserNotFoundException {
		Optional<User> bookById = userRepository.findById(bookid);

		if (bookById.isPresent()) {
			return bookById.get();
		} else {
			User book = new User();
			return book;
		}
	}

	public User createorUpdateuser(User user) {
		Optional<User> userid = userRepository.findById(user.getUserid());

		if (userid.isPresent()) {
			User newbookid = userid.get();
			newbookid.setExperience(user.getExperience());
			newbookid.setUserName(user.getUserName());
			newbookid = userRepository.save(newbookid);
			return newbookid;
		} else {
			user = userRepository.save(user);
			return user;
		}

	}

	public void deleteUserId(int userid) throws UserNotFoundException {
		Optional<User> employee = userRepository.findById(userid);

		if (employee.isPresent()) {
			userRepository.deleteById(userid);
		} else {
			throw new UserNotFoundException("No employee record exist for given id");
		}
	}

	public List<User> finduserexperience() {

		Map<Integer, String> map = null;
		List<User> result = userRepository.finduserExperience();
		return result;
	}

	public List<User> findbelow5experience() {
		List<User> result = userRepository.findByExp();
		return result;
	}

	public List<User> findabove10experience() {
		List<User> result = userRepository.findByExp10();
		return result;
	}

	public Slice<User> getAllItemCategoriesByPageable(PageRequest page) {
		// TODO Auto-generated method stub
		return userPagerepo.findAll((org.springframework.data.domain.Pageable) page);
	}

	public List<User> getByExpbetween() {
		List<User> result = userRepository.findBetweenexperience();
		return result;
	}

	public List<User> getEmployeesByOrder() {
		List<User> userlist = repository.findAll(Sort.by(Sort.Direction.ASC, "userName"));
		return userlist;
	}

}
