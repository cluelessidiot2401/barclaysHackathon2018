package com.diligents.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.diligents.model.barclaysUsers.LoginDetails;

public class LoginDetailsRowMapper implements RowMapper<LoginDetails>  {

	@Autowired
	public LoginDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

		try {
			LoginDetails loginDetails = new LoginDetails();
			
			loginDetails.setuId(rs.getInt("uId"));
			loginDetails.setuName(rs.getString("uName"));
			loginDetails.setuPassword(rs.getString("uPassword"));
			
			return loginDetails;
			
		}
		catch(Exception e) {
			return null;
		}

	}
	
}
