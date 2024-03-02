package com.ischolar.ischolar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ischolar.ischolar.entity.User;
import com.ischolar.ischolar.repo.UserDao;

public class UserServiceDetailsImp implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found !");
		}
		return new CustomeUser(user);
	}

	
}
