package com.diligents.service.util;

import javax.validation.Valid;

import com.diligents.model.barclaysUsers.Login;
import com.diligents.model.barclaysUsers.LoginDetails;
import com.diligents.model.barclaysUsers.UserInfo;

public class LoginValidator {
	
	public static boolean isValid(@Valid Login login) {
		
		UserInfo userInfo = login.getUserInfo();
		LoginDetails loginDetails = login.getLoginDetails();
		
		if(!UserInfoValidator.isValid(userInfo))	return false;
		if(!LoginDetailsValidator.isValid(loginDetails))	return false;
		
		return true;
	}
}
