package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.exceptions.ResourceNotFoundException;
import com.scm.payload.AppConstaints;
import com.scm.repository.UserRepository;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoleList(List.of(AppConstaints.ROLE_USER));
		logger.info(user.getProvider().toString());
		return this.userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(String id) {
		return this.userRepository.findById(id);
	}

	@Override
	public Optional<User> updateUser(User user) {
		User user2 = this.userRepository.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found !!"));
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		user2.setPhoneNumber(user.getPhoneNumber());
		user2.setAbout(user.getAbout());
		user2.setProfilePic(user.getProfilePic());
		user2.setEnabled(user.isEnabled());
		user2.setEmailVerified(user.isEmailVerified());
		user2.setPhoneVerified(user.isPhoneVerified());
		user2.setProvider(user.getProvider());
		user2.setProviderUserId(user.getProviderUserId());
		User user3 = this.userRepository.save(user2);
		return Optional.ofNullable(user3);
	}

	@Override
	public void deleteUser(String id) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found !!"));
		this.userRepository.delete(user);
	}

	@Override
	public boolean isUserExist(String userId) {
		User user = this.userRepository.findById(userId).orElse(null);
		return user != null ? true : false;
	}

	@Override
	public boolean isUserExistByEmail(String email) {
		User user = this.userRepository.findByEmail(email).orElse(null);
		return user != null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

}
