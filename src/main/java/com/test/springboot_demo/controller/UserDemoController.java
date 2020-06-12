package com.test.springboot_demo.controller;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot_demo.error.UserNotFoundException;
import com.test.springboot_demo.model.User;
import com.test.springboot_demo.repository.UserPagerepo;
import com.test.springboot_demo.repository.UserRepository;
import com.test.springboot_demo.model.ErrorMessage;
import com.test.springboot_demo.service.UserService;
import org.springframework.data.domain.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Sort;

@RestController
@Api(value = "UserDemoController", description = "Rest API related to user experience")
public class UserDemoController {

	@Autowired
	UserService userservice;
	

	@GetMapping("/alluser")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userservice.getAllUsers();
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Search a user with an ID ", response = User.class)
	@RequestMapping("/user/{userid}")
	public ResponseEntity<User> getuserid(@PathVariable("userid") int userid) throws UserNotFoundException {
		User book = null;
		book = userservice.getuserById(userid);
		if (book.getUserid() == 0) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setErrorCode(404);
			errorMessage.setErrorMessage("No userID record exist for given userid");
			return new ResponseEntity(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(book, new HttpHeaders(), HttpStatus.OK);
		}

	}

	@ApiOperation("For Below 5 years Experience")
	@GetMapping("/user")
	public ResponseEntity<List<User>> userBelow5() {
		List<User> list = userservice.findbelow5experience();
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation("For above 10 years Experience")
	@GetMapping("/useraboveten")
	public ResponseEntity<List<User>> getByExpten() {
		List<User> list = userservice.findabove10experience();
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation("Between 5 to 10 years Experience")
	@GetMapping("/userbetween")
	public ResponseEntity<List<User>> getByExpbetween() {
		List<User> list = userservice.getByExpbetween();
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add user")
	@PostMapping("/user/{userid}")
	public ResponseEntity<User> createorUpdateuser(@RequestBody User user) throws UserNotFoundException {
		User updated = userservice.createorUpdateuser(user);
		return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation("Get All User Order By Name")
	@GetMapping("/usersort")
	public ResponseEntity<List<User>> getEmployeesByOrder() {
		List<User> list = userservice.getEmployeesByOrder();
		//List<User> userlist = repository.findAll(Sort.by(Sort.Direction.ASC, "userName"));
		return ResponseEntity.ok(list);
	}

	@ApiOperation(value = "delete a user")
	@DeleteMapping("/user/{userid}")
	public HttpStatus deleteuser(@PathVariable("userid") int userid) throws UserNotFoundException {
		userservice.deleteUserId(userid);
		return HttpStatus.FORBIDDEN;
	}

	@ApiOperation("Get All User using PageRequest")
	@RequestMapping(value = "/userpage", method = RequestMethod.GET)
	public List<User> getAllItemCategoryByPage(@RequestParam("page") int pageIndex,
			@RequestParam("size") int pageSize) {
		return userservice.getAllItemCategoriesByPageable(PageRequest.of(pageIndex, pageSize)).getContent();
	}

}
