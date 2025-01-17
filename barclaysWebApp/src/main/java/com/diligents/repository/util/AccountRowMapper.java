package com.diligents.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.diligents.model.barclaysUsers.Account;

public class AccountRowMapper implements RowMapper<Account> {
	@Autowired
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

		try {
			
			Account account = new Account();
			
			account.setAccNo(rs.getString("accNo"));
			account.setAccType(rs.getString("accType"));
			account.setBalance(rs.getInt("balance"));
			account.setBranchId(rs.getInt("branchId"));
			
			return account;
			
		}
		catch(Exception e) {
			return null;
		}
	}

}
