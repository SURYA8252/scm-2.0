package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	//User Dashboard
	@GetMapping("/dashboard")
	public String userDashboard() {
		return "user/dashboard";
	}
	//User Profile Page
	@GetMapping("/profile")
	public String userProfile() {
		return "user/profile";
	}
	//User add contact page
	//User View Contact Page
	//User Edit Contact Page
	//User Delete Contact Page
}
