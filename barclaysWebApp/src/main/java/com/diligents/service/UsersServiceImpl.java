package com.diligents.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diligents.model.barclaysUsers.Login;
import com.diligents.model.barclaysUsers.LoginDetails;
import com.diligents.model.barclaysUsers.Transaction;
import com.diligents.model.barclaysUsers.UserInfo;
import com.diligents.repository.UsersRepository;
import com.diligents.service.util.LoginValidator;
import com.diligents.service.util.UserInfoValidator;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	// @Autowired
	private UserInfoValidator userInfoValidator = new UserInfoValidator();

	@Override
	public Login addUser(@Valid Login login) {

		if (!LoginValidator.isValid(login))
			return null;
		System.out.println("From addUser = its valid");

		return usersRepository.addUser(login);
	}

	@Override
	public @Valid Login register(@Valid Login login) {
		System.out.println("\nRegistering Now...\n\n");

		if (!LoginValidator.isValid(login))
		{
			System.out.println("loginValidator says not valid");
			return null;
		}

		System.out.println("From register = its valid");
		
		UserInfo userInfo = login.getUserInfo();

		List<UserInfo> usersInfo = usersRepository.getUsersByEmailId(userInfo.getEmailId());
		System.out.println(usersInfo.size());
		
//		for(int i=0;i<usersInfo.size();++i) {
//			System.out.println(usersInfo.get(i).getName());
//		}
		
		if (usersInfo.size()>0)
			return null;
		System.out.println("From register = no user by that email");

		int userId = usersRepository.getUserIdByEmailId(userInfo.getEmailId());
		if(userId!=-1)	return null;
		
		return addUser(login);
	}

	@Override
	public UserInfo addUser(@Valid UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Login validateLogin(@Valid Login login) {
		
		System.out.println("validating\n");
		
		UserInfo userInfo = login.getUserInfo();
		LoginDetails loginDetails = login.getLoginDetails();
		
		List<UserInfo> usersInfo = usersRepository.getUsersByEmailId(userInfo.getEmailId());
		System.out.println(usersInfo.size());
		if(usersInfo==null || usersInfo.size()==0) {
			System.out.println("Error while validating");
			return null;
		}
		System.out.println("1 user by Email");
		System.out.println("\n"+userInfo.getEmailId()+" "+loginDetails.getuPassword());
		List<LoginDetails> loginDetailsMul = usersRepository.getUserByPasswordAndEmail(userInfo.getEmailId(),loginDetails.getuPassword());
		System.out.println(loginDetailsMul.size());
		if(loginDetailsMul==null || loginDetailsMul.size()==0) {
			return null;
		}
		System.out.println("Exiting");
		
		login.setUserInfo(usersInfo.get(0));
		login.setLoginDetails(loginDetailsMul.get(0));
		
		return login;
	}

	@Override
	public int changePass(String uname, String cpass, String npass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeMob(String uname, String mcpass, String nmob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getBalance(String userNameFromFE) {
		return usersRepository.getBalanceForUId(usersRepository.getUserIdByEmailId(userNameFromFE));
	}

	@Override
	public Integer transferFunds(String toAccountNo, int amount, String userName, String myPass) {
		List<LoginDetails> loginDetailsMul = usersRepository.getUserByPasswordAndEmail(userName,myPass);
		System.out.println(loginDetailsMul.size());
		if(loginDetailsMul==null || loginDetailsMul.size()==0) {
			return 0;
		}
		int balance= getBalance(userName);
		if(balance<amount)	return 0;
		
		Integer uIdForReceiver = usersRepository.getUIdFromAccNo(toAccountNo);
		
		if(uIdForReceiver==null)	return 0;
		
		int newBalanceForReceiver = getBalance(usersRepository.getEmailIdbyUId(uIdForReceiver));
		newBalanceForReceiver += amount;
		balance -= amount;
		
		usersRepository.updateBalanceForAccNo(toAccountNo,newBalanceForReceiver);
		
		int userIdOfSender = usersRepository.getUserIdByEmailId(userName);
		String curAccNo = usersRepository.getAccNoFromUId(userIdOfSender);
		
		usersRepository.updateBalanceForAccNo(curAccNo, balance);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime localDate = LocalDateTime.now();
		
		String dateTemp = dtf.format(localDate);
		String date = dateTemp.substring(0, 4)+"/"+dateTemp.substring(5,7)+"/"+dateTemp.substring(8,10);
		
		String timeTemp = dateTemp.substring(11, dateTemp.length());
		System.out.println(date+" "+timeTemp);
		
		Transaction transaction = new Transaction();
		transaction.setAccNo(curAccNo);
		transaction.setDate(date);
		transaction.setTime(timeTemp);
		transaction.setToAccNo(toAccountNo);
		transaction.setuId(userIdOfSender);
		
		usersRepository.addTransaction(transaction);
		
		return 1;
		
	}

	@Override
	public String getAccountNumberbyUserId(String userName) {
		
		return usersRepository.getAccNoFromUId(usersRepository.getUserIdByEmailId(userName));
	}

	@Override
	public List<Transaction> getTransactionHistoryByUName(String userName) {
		
		return usersRepository.getTransactionHistory(userName);
		
	}

//	@Override
//	public UserInfo validateLogin(@Valid UserInfo userInfo) {
//		return usersRepository.getValidLogin(userInfo.getUserName(), userInfo.getPasswd());
//	}
//
//	@Override
//	public int changePass(String uname, String cpass, String npass) {
//		return usersRepository.changePass(uname, cpass, npass);
//	}
//
//	@Override
//	public int changeMob(String uname, String mcpass, String nmob) {
//		return usersRepository.changeMob(uname, mcpass, nmob);
//	}

}
