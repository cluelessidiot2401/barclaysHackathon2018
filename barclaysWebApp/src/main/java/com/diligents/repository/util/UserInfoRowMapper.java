package com.diligents.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.diligents.model.barclaysUsers.UserInfo;

public class UserInfoRowMapper implements RowMapper<UserInfo> {

	@Autowired
	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		// create table userInfo(uid int not null auto_increment primary key, uName
		// varchar(20) not null,
		// passwd varchar(60) not null
		// , enabled enum('0','1'), dob date , district varchar(30) not null, emailId
		// varchar(30) not null,
		// mobile varchar(10) not null)
		// ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and global
		// privileges';

		try {
			
			UserInfo userInfo = new UserInfo();

			userInfo.setAddress(rs.getString("address"));
			userInfo.setDob(rs.getString("dob"));
			userInfo.setEmailId(rs.getString("emailId"));
			userInfo.setEnabled(rs.getInt("enabled"));
			userInfo.setGender(rs.getString("gender"));
			userInfo.setName(rs.getString("uName"));
			userInfo.setPanNo(rs.getString("panNo"));
			userInfo.setPhoneNo(rs.getString("phoneNo"));
			userInfo.setuId(rs.getInt("uId"));
			
			return userInfo;
		}
		catch(Exception e) {
			System.out.println("Exception Thrown");
			return null;
		}

	}
}
