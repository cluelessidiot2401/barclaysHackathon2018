package com.diligents.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.diligents.model.barclaysUsers.BranchInfo;

public class BranchInfoRowMapper {

	@Autowired
	public BranchInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		try {
			BranchInfo branchInfo = new BranchInfo();
			
			branchInfo.setBranchAddress(rs.getString("branchAddress"));
			branchInfo.setBranchCode(rs.getString("branchCode"));
			branchInfo.setBranchId(rs.getInt("branchId"));
			branchInfo.setBranchName(rs.getString("branchName"));
			
			return branchInfo;
		}
		catch(Exception e) {
			return null;
		}

	}

}
