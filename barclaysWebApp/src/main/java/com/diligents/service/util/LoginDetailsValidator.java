package com.diligents.service.util;

import javax.validation.Valid;

import com.diligents.model.barclaysUsers.LoginDetails;

public class LoginDetailsValidator {

	public static boolean isValid(@Valid LoginDetails loginDetails) {
		System.out.println("from LoginDetailsValidator");
		System.out.println(loginDetails.getuId());
		System.out.println(loginDetails.getuName());
		System.out.println(loginDetails.getuPassword());

		if(loginDetails.getuPassword()==null)	return false;
		System.out.println("in logindetails Validator");
		
		return true;
	}
	
}
