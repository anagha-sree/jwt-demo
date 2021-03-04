package com.stackroute.newsapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/v1/auth")
public class UserAuthController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody User user){
		String jwtToken ="";
		Map<String,String> map = new HashMap<>();
		try {
			jwtToken = getToken(user.getUsername(), user.getPassword());
			map.put("token", jwtToken);
			map.put("message", "user logged in successfully");
		}catch(Exception e) {
			map.put("token", "");
			map.put("message", "user NOT logged in ");
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	public String getToken(String username,String password) throws ServletException {
		String jwtToken ="";
		
		if(username == null || password ==null) {
			throw new ServletException(" Username - Password cannot be blank");
		}
		
		boolean status = userService.validate(username,password);
		if(!status) {
			throw new ServletException(" Invalid credentials");
		}
		
		jwtToken = Jwts.builder()
					   .setSubject("Demo")
					   .setIssuer("sam")
					   .setExpiration(new Date(System.currentTimeMillis()+300000))
					   .signWith(SignatureAlgorithm.HS256, "ibmwave8")
					   .compact();
		return jwtToken;
		
	}
	
	
	
	
	
	
}
