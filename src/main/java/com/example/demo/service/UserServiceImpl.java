package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.modal.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public User loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if(user != null) {
		return user;
		}
		try {
			if (user != null) {
				logger.info("Logged in User is {} ", user.getEmail()); 
				/*
				 * if(UserStatus.ACTIVE.equals(user.getUserStatus())) { return user; }
				 */
				return user;
			}
			
		} catch (UsernameNotFoundException e) {
			logger.info("Logged in User is {} ", user.getEmail()); 
		} catch (InternalAuthenticationServiceException e) {
			System.err.println("****I am here");
			logger.info("Logged in User is {} ", user.getEmail()); 
			
		}

		return user;
	}

}
