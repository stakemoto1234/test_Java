package com.pj.common.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.common.models.Users;
import com.pj.common.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
	private UsersRepository usersRepository;

    public boolean insertUsers(Users users) {
	 try {
		usersRepository.save(users);
		return true;
	 	} 
	 catch (Exception e) {
		return false;
		}
	}
}