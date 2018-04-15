package com.diligents.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.diligents.model.barclaysUsers.UserAccount;
import com.diligents.model.barclaysUsers.UserInfo;

public class UserAccountRowMapper implements RowMapper<UserAccount> {
	
	@Autowired
	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

		try {
			UserAccount userAccount = new UserAccount();
			
			userAccount.setAccNo(rs.getString("accNo"));
			userAccount.setuId(rs.getInt("uId"));
			
			return userAccount;
		}
		catch(Exception e) {
			return null;
		}

	}

}
