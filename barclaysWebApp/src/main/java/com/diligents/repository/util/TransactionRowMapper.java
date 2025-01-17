package com.diligents.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.diligents.model.barclaysUsers.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {
	
	@Autowired
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {

		try {
			Transaction transaction = new Transaction();
			
			transaction.setuId(rs.getInt("uId"));
			transaction.setAccNo(rs.getString("accNo"));
			transaction.setToAccNo(rs.getString("toAccNo"));
			transaction.setDate(rs.getString("date"));
			transaction.setTime(rs.getString("time"));
			
			return transaction;
		}
		catch(Exception e) {
			return null;
		}

	}

}
