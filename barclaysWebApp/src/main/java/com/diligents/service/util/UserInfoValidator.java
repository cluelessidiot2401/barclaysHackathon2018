package com.diligents.service.util;

import javax.validation.Valid;

import com.diligents.model.barclaysUsers.UserInfo;

public class UserInfoValidator {

	public static boolean isValid(@Valid UserInfo userInfo) {
		
		System.out.println("from userinfoValidator");
//		if(userInfo.getuId()==0)	return false;
		System.out.println(userInfo.getEmailId());
		if(userInfo.getEmailId()==null)	return false;
//		if(userInfo.getPasswd()==null)	return false;
//		if(userInfo.getDistrict()==null)	return false;
//		if(userInfo.getEmailId()==null)	return false;
//		System.out.println(userInfo.getEmailId());
		System.out.println(userInfo.getPhoneNo());
		if(userInfo.getPhoneNo()==null )	return false;
		System.out.println(userInfo.getName()+" "+userInfo.getName().length());
		if(userInfo.getName()==null || userInfo.getName().length()<3)	return false;
		System.out.println(userInfo.getPanNo());
		if(userInfo.getPanNo()==null)	return false;
		
//		int year = Integer.parseInt(userInfo.getDob().substring(0, 4));
//		if(year>2004)	return false;
//		System.out.println("year = "+year);
		
		return true;
	}

	
	
}