package com.diligents.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.diligents.model.barclaysUsers.Account;
import com.diligents.model.barclaysUsers.Login;
import com.diligents.model.barclaysUsers.LoginDetails;
import com.diligents.model.barclaysUsers.Transaction;
import com.diligents.model.barclaysUsers.UserAccount;
import com.diligents.model.barclaysUsers.UserInfo;
import com.diligents.repository.util.AccountRowMapper;
import com.diligents.repository.util.LoginDetailsRowMapper;
import com.diligents.repository.util.TransactionRowMapper;
import com.diligents.repository.util.UserAccountRowMapper;
import com.diligents.repository.util.UserInfoRowMapper;

@Repository("usersRepository")
public class UsersRepositoryImpl implements UsersRepository {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");

	private static JdbcTemplate jdbcTemplateForUsers = ctx.getBean("jdbcTemplateForUsers", JdbcTemplate.class);

	@Override
	public Login addUser(@Valid Login login) {
		
		UserInfo userInfo = login.getUserInfo();
		LoginDetails loginDetails = login.getLoginDetails();
		
		loginDetails.setuName(userInfo.getEmailId());
		
		System.out.println("name = "+userInfo.getName());
		System.out.println(userInfo.getEmailId());
		System.out.println(userInfo.getPhoneNo());
		System.out.println(userInfo.getPanNo());
		System.out.println(userInfo.getDob());
		System.out.println(userInfo.getGender());
		
		SimpleJdbcInsert sJdbcIns = new SimpleJdbcInsert(jdbcTemplateForUsers);

		List<String> columns = new ArrayList<String>();	//for userinfo
		columns.add("emailId");
		columns.add("uName");
		columns.add("phoneNo");
		columns.add("panNo");
		columns.add("dob");
		columns.add("gender");

		sJdbcIns.setTableName("userInfo");
		sJdbcIns.setColumnNames(columns);

		Map<String, Object> data = new HashMap<>();

		data.put("emailId", userInfo.getEmailId());
		data.put("uName", userInfo.getName());
		data.put("phoneNo", userInfo.getPhoneNo());
		data.put("panNo", userInfo.getPanNo());
		data.put("dob", userInfo.getDob());
		data.put("gender", userInfo.getGender());

		 sJdbcIns.execute(data);

//		sJdbcIns.setGeneratedKeyName("uId");

		int id = getUserIdByEmailId(loginDetails.getuName());
		
		System.out.println("From addUser in repo = its inserted");
		
		System.out.println();

		userInfo = getUser(id);
		
		System.out.println("\nuserinfo gotten\n\n");
		
//		------------------------------------------
		
		columns = new ArrayList<String>();	//for loginDetails
		columns.add("uId");
		columns.add("uName");
		columns.add("uPassword");
//
		sJdbcIns = new SimpleJdbcInsert(jdbcTemplateForUsers);
		sJdbcIns.setTableName("loginDetails");
		sJdbcIns.setColumnNames(columns);
//
		data = new HashMap<>();
		
		System.out.println("\n\nloginDetails\n\n");
		System.out.println(id);
		System.out.println(loginDetails.getuName());
		System.out.println(loginDetails.getuPassword());
		
		loginDetails.setuId(id);
//
		data.put("uId", loginDetails.getuId());
		data.put("uName", userInfo.getEmailId());
		data.put("uPassword", loginDetails.getuPassword());

		 sJdbcIns.execute(data);

//		sJdbcIns.setGeneratedKeyName("uId");

		id = getUserIdByEmailIdFromLoginDetails(loginDetails.getuName());
		
		System.out.println("From addUser in repo = its inserted");

		loginDetails = getUserDetails(id);
		
		login.setLoginDetails(loginDetails);
		login.setUserInfo(userInfo);
	
//		-----------------------------------------------------------
		
		Account account = getFirstAvailableAccountNumber();
		
		columns = new ArrayList<String>();	//for loginDetails
		columns.add("uId");
		columns.add("accNo");
//
		sJdbcIns = new SimpleJdbcInsert(jdbcTemplateForUsers);
		sJdbcIns.setTableName("userAcc");
		sJdbcIns.setColumnNames(columns);
//
		data = new HashMap<>();
		
//		System.out.println("\n\nloginDetails\n\n");
//		System.out.println(id);
//		System.out.println(loginDetails.getuName());
//		System.out.println(loginDetails.getuPassword());
		
//		loginDetails.setuId(id);
//
		data.put("uId", loginDetails.getuId());
		data.put("accNo", account.getAccNo());

		 sJdbcIns.execute(data);

//		sJdbcIns.setGeneratedKeyName("uId");

//		id = getUserIdByEmailIdFromLoginDetails(loginDetails.getuName());
		 
		 jdbcTemplateForUsers.update("update account set inUse=1 where accNo = ? ",account.getAccNo());
		 

		System.out.println("\nloginDetails gotten\n\n");
		return login;
	}
	
	private Account getFirstAvailableAccountNumber() {
		try {
			Account account = (Account) jdbcTemplateForUsers.queryForObject("select * from account where inUse = 0 limit 1",
					new AccountRowMapper());
			System.out.println("From getFirstAvailableAccountNumber in repo = its valid");
			return account;
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getFirstAvailableAccountNumber in repo = its NULL");
			return null;
		}
		
	}

	private LoginDetails getUserDetails(Integer id)
	{
		try {
			LoginDetails loginDetails = (LoginDetails) jdbcTemplateForUsers.queryForObject("select * from loginDetails where uId = ?",
					new LoginDetailsRowMapper(), new Object[] {id});
			System.out.println("From getUserDetails in repo = its valid");
			return loginDetails;
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUserDetails in repo = its NULL");
			return null;
		}
	}

	@Override
	public UserInfo getUser(Integer id) {
		
		try {
			UserInfo userInfo = jdbcTemplateForUsers.queryForObject("select * from userInfo where uId = ?",
					new UserInfoRowMapper(), id);
			System.out.println("From getUser in repo = its valid");
			return userInfo;
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUser in repo = its NULL");
			return null;
		}
	}


	@Override
	public List<UserInfo> getUsersByEmailId(String emailId) {
		try {
			System.out.println("email = "+emailId);
			List<UserInfo> userInfo = jdbcTemplateForUsers.query("select * from userInfo where emailId = ?",
					new UserInfoRowMapper(), emailId);
			System.out.println("Not null from getUsersByEmailId");
			return userInfo;
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUsersByEmailId in repo = its NULL");
			return null;
		}
	}

	@Override
	public int getUserIdByEmailId(String emailId) {
		try {
			UserInfo userInfo = jdbcTemplateForUsers.queryForObject("select * from userInfo where emailId = ?",
					new UserInfoRowMapper(), emailId);
			System.out.println("From getUser in repo = its valid");
			return userInfo.getuId();
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUser in repo = its NULL");
			return -1;
		}
	}

	public int getUserIdByEmailIdFromLoginDetails(String emailId) {
		try {
			LoginDetails loginDetails = jdbcTemplateForUsers.queryForObject("select * from loginDetails where uName = ?",
					new LoginDetailsRowMapper(), emailId);
			System.out.println("From getUserIdByEmailIdFromLoginDetails in repo = its valid");
			return loginDetails.getuId();
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUserIdByEmailIdFromLoginDetails in repo = its NULL");
			return -1;
		}
	}

	@Override
	public UserInfo addUser(@Valid UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginDetails validateForUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginDetails getUserById(int userId) {
		try {
			LoginDetails loginDetails = jdbcTemplateForUsers.queryForObject("select * from loginDetails where uId = ?",
					new LoginDetailsRowMapper(), userId);
			System.out.println("From getUserById in repo = its valid");
			return loginDetails;
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUserById in repo = its NULL");
			return null;
		}
	}

	@Override
	public List<LoginDetails> getUserByPasswordAndEmail(String emailId, String getuPassword) {
		try {
			System.out.println(emailId+" "+getuPassword);
			List<LoginDetails> loginDetails = jdbcTemplateForUsers.query("select * from loginDetails where uName = ? and uPassword = ?",
					new LoginDetailsRowMapper(), new Object[] {emailId,getuPassword});
			System.out.println("Not null from getUserByPasswordAndEmail");
			return loginDetails;
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUserByPasswordAndEmail in repo = its NULL");
			return null;
		}
	}

	@Override
	public Integer getBalanceForUId(int userId) {
	
		try {
			
			UserAccount userAccount = jdbcTemplateForUsers.queryForObject("select * from userAcc where uId = ? ", new UserAccountRowMapper(), userId);
			
			Account account =  jdbcTemplateForUsers.queryForObject("select * from account where accNo = ? ", new AccountRowMapper(), userAccount.getAccNo());

			System.out.println("From getUserById in repo = its valid");
			return account.getBalance();
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUserById in repo = its NULL");
			return null;
		}
	}

	@Override
	public Integer getUIdFromAccNo(String toAccountNo) {
		try {
			
			UserAccount userAccount = jdbcTemplateForUsers.queryForObject("select * from userAcc where accNo = ? ", new UserAccountRowMapper(), toAccountNo);
			System.out.println("From getUIdFromAccNo in repo = its valid");
			return userAccount.getuId();
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getUIdFromAccNo in repo = its NULL");
			return null;
		}
	}

	@Override
	public String getEmailIdbyUId(Integer uIdForReceiver) {
		try {
			UserInfo userInfo = jdbcTemplateForUsers.queryForObject("select * from userInfo where uId = ?",
					new UserInfoRowMapper(), uIdForReceiver);
			System.out.println("From getEmailIdbyUId in repo = its valid");
			return userInfo.getEmailId();
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getEmailIdbyUId in repo = its NULL");
			return null;
		}
	}

	@Override
	public String getAccNoFromUId(int userIdByEmailId) {
		try {
			
			UserAccount userAccount = jdbcTemplateForUsers.queryForObject("select * from userAcc where uId = ? ", new UserAccountRowMapper(), userIdByEmailId);
			System.out.println("From getAccNoFromUId in repo = its valid");
			return userAccount.getAccNo();
		}
		catch(EmptyResultDataAccessException erdae) {
			System.out.println("From getAccNoFromUId in repo = its NULL");
			return null;
		}
	}

	@Override
	public int updateBalanceForAccNo(String toAccountNo, int newBalanceForReceiver) {
		try {
			 jdbcTemplateForUsers.update("update account set balance= ?  where accNo = ? ",newBalanceForReceiver, toAccountNo);
			 return 1;
		}
		catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Transaction addTransaction(Transaction transaction) {
		
		try {
//			
//			System.out.println("name = "+userInfo.getName());
//			System.out.println(userInfo.getEmailId());
//			System.out.println(userInfo.getPhoneNo());
//			System.out.println(userInfo.getPanNo());
//			System.out.println(userInfo.getDob());
//			System.out.println(userInfo.getGender());
			
			SimpleJdbcInsert sJdbcIns = new SimpleJdbcInsert(jdbcTemplateForUsers);

			List<String> columns = new ArrayList<String>();	//for userinfo
			columns.add("uId");
			columns.add("accNo");
			columns.add("toAccNo");
			columns.add("date");
			columns.add("time");

			sJdbcIns.setTableName("transaction");
			sJdbcIns.setColumnNames(columns);

			Map<String, Object> data = new HashMap<>();

			data.put("uId", transaction.getuId());
			data.put("accNo", transaction.getAccNo());
			data.put("toAccNo", transaction.getToAccNo());
			data.put("date", transaction.getDate());
			data.put("time", transaction.getTime());

			 sJdbcIns.execute(data);

//			sJdbcIns.setGeneratedKeyName("uId");

			
			System.out.println("From addTransaction in repo = its inserted");
			
			System.out.println();

			System.out.println("\nTransaction gotten\n\n");
			
			return transaction;
			
		}
		catch(Exception e) {
			return null;
		}


		
	}

	@Override
	public List<Transaction> getTransactionHistory(String userName) {
		
		try {
			
			String accNo = getAccNoFromUId(getUserIdByEmailId(userName));
			
			List<Transaction> ts=jdbcTemplateForUsers.query("select *from transaction where accNo=? or toAccNo=?", new TransactionRowMapper(),accNo,accNo);
			return ts;
		}
		catch(EmptyResultDataAccessException erdae) {
			return null;
		}
		
	}

	

}
