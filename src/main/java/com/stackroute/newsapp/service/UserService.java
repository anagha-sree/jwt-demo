package com.stackroute.newsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public boolean validate(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if(user != null) {
			return true;
		}
		return false;
	}

	public User addUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	
	
}
