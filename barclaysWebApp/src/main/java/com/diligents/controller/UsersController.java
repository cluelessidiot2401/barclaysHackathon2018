package com.diligents.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diligents.model.barclaysUsers.Login;
import com.diligents.model.barclaysUsers.LoginDetails;
import com.diligents.model.barclaysUsers.Transaction;
import com.diligents.model.barclaysUsers.UserInfo;
import com.diligents.service.UsersService;

@Controller("usersController")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	private static String userName;
	private static int userId;
	
	@RequestMapping(value = "/user_page")
	public String UserPage(@ModelAttribute("login") Login login) {
//		if(userinfo == null) {
//			System.out.println("Its null man!");
//		}
//		System.out.println("username = " + userinfo.getUserName());
//		System.out.println("passwd = " + userinfo.getPasswd());
		System.out.println("userpage is where it goes");
		System.out.println(login.getLoginDetails().getuPassword());
		System.out.println(login.getUserInfo().getEmailId());
		return "user_page";
	}
	
	@RequestMapping(value = "/login")
	public String Login(@ModelAttribute("login") Login login) {
//		if(userinfo == null) {
//			System.out.println("Its null man!");
//		}
//		System.out.println("username = " + userinfo.getUserName());
//		System.out.println("passwd = " + userinfo.getPasswd());
		System.out.println("login is where it goes");
		return "login";
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("login") Login login, Model model,
			HttpSession session) {
//		ModelAndView reg = null;
		UserInfo userInfo = login.getUserInfo();
		LoginDetails loginDetails = login.getLoginDetails();
		
		System.out.println(userInfo.getDob());
		if (userInfo.getEmailId().equals("") && loginDetails.getuPassword().equals("")) {
			System.out.println("redirected due to binding errors");
//			reg = new ModelAndView("redirect:login.html");
			return "redirect:login.html";
//			return reg;
		}
		System.out.println("username = " + userInfo.getEmailId());
		System.out.println("passwd = " + loginDetails.getuPassword());
		login = usersService.validateLogin(login);
		if (login == null) {
			System.out.println("redirected due to login issues");
//			reg = new ModelAndView("redirect:login.html");
//			return reg;
			return "redirect:login.html";
		}
		
//		reg = new ModelAndView(new RedirectView("user_page.html"));
//		reg.addObject("userInfo", userInfo);
//		reg.addObject("idealConditions", new IdealConditions());
		System.out.println("exiting from loginProcess post");
//		return reg;
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("loginDetails", loginDetails);
		model.addAttribute("uName", loginDetails.getuName());

		session.setAttribute("emailId", userInfo.getEmailId());
		System.out.println("uname = "+loginDetails.getuName());
		session.setAttribute("uName", loginDetails.getuName());
		session.setMaxInactiveInterval(600);
		model.addAttribute("login", login);
		
		ModelMap modelMap = new ModelMap();
		modelMap.put("userInfo", userInfo);
		modelMap.put("loginDetails", loginDetails);
		modelMap.put("uName", loginDetails.getuName());
		
		userName = login.getUserInfo().getEmailId();
	
		return "forward:user_page.html";

	}
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String registerProcess(@Valid @ModelAttribute("login") Login login,
			BindingResult result, Model model) {
//		ModelAndView reg = null;
		System.out.println(login.getUserInfo().getDob());
		if (result.hasErrors()) {
			System.out.println("redirected due to binding errors");
//			reg = new ModelAndView("redirect:login.html");
			return "redirect:login.html";
//			return reg;
		}
		login = usersService.register(login);
		if (login == null) {
			System.out.println("redirected due to some other error");
//			reg = new ModelAndView("redirect:login.html");
			return "redirect:login.html";
//			return reg;
		}
		
		model.addAttribute("login", login);
		
		userName = login.getUserInfo().getEmailId();
		
		return "forward:user_page.html";
		
//		reg = new ModelAndView(new RedirectView("user_page.html"));
//		reg.addObject("userInfo", userInfo);
//		reg.addObject("idealConditions", new IdealConditions());
//		return reg;

	}
	
	@RequestMapping(value = "/getuName", method = RequestMethod.GET)
	public @ResponseBody String[] getUserName() {
		return new String[] {userName};
	}
	
	@RequestMapping(value = "/getAccountNo", method = RequestMethod.GET)
	public @ResponseBody String[] getAccountNo() {
		return new String[] {usersService.getAccountNumberbyUserId(userName)};
	}
	
	@RequestMapping(value = "/getTransactionHistory", method = RequestMethod.GET)
	public @ResponseBody List<Transaction> getTransactionHistory() {
		return usersService.getTransactionHistoryByUName(userName);
	}
	
	
	@RequestMapping(value = "/getBalance/{uName}", method = RequestMethod.GET)
	public @ResponseBody Integer[] getUserName(@PathVariable(value="uName") String userNameFromFE) {
		return new Integer[] {usersService.getBalance(userNameFromFE)};
	}
	
	@RequestMapping(value = "/transfer/{toAccount}/{amount}/{myPass}", method = RequestMethod.GET)
	public @ResponseBody Integer[] transferFunds(@PathVariable(value="toAccount") String toAccountNo, @PathVariable(value="amount") int amount,
			@PathVariable(value="myPass") String myPass) {
		return new Integer[] {usersService.transferFunds(toAccountNo,amount,userName,myPass)};
	}


	
}
