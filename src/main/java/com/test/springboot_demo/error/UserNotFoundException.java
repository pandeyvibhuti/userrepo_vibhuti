package com.test.springboot_demo.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


//@ResponseStatus(HttpStatus.NOT_FOUND) 
public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
        super(message);
		System.out.println("bookid::::::::::message::::::::::"+message);

    }
	
}
