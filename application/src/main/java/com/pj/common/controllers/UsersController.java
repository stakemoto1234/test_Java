package com.pj.common.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.pj.common.models.Users;
import com.pj.common.services.UsersService;

@RestController
//@RequestMapping( "/users" )
public class UsersController
{
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
	private UsersService usersService;

	@RequestMapping(method=RequestMethod.POST, value = "/users/add")
	public boolean addEmployee(@RequestBody Users users) {
		
		logger.info("Creation/Updating Users - "+users.toString());
		return usersService.insertUsers(users);
	}

}
