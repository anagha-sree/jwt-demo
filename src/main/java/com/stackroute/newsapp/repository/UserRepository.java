package com.stackroute.newsapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.newsapp.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, String>{

	User findByUsernameAndPassword(String username,String password);
}
