package com.diligents.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class UsersController {
	
	@RequestMapping(value = "login")
	public String Login() {
//		if(userinfo == null) {
//			System.out.println("Its null man!");
//		}
//		System.out.println("username = " + userinfo.getUserName());
//		System.out.println("passwd = " + userinfo.getPasswd());
		System.out.println("login is where it goes");
		return "login";
	}
	
}
