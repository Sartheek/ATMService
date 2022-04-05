package com.project.exam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.exam.model.AmountTransfer;
import com.project.exam.model.CustomerSignIn;
import com.project.exam.model.CustomerSignUp;
import com.project.exam.model.CustomerWithDrawNDeposit;
import com.project.exam.service.ATMService;

@RestController
public class ATMController 
{	
	private static final Logger logger = LoggerFactory.getLogger(ATMController.class);
	
	@Autowired
	private ATMService atmServiceImpl;
	
	@RequestMapping(method= RequestMethod.GET, value="/customer/hello")
	public String hello()
	{
		logger.info("I've entered in ATMController");
		return "Hello Sartheek";
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/customer/signUp")
	public CustomerSignIn addCustomer(@RequestBody CustomerSignUp customer)
	{
		return atmServiceImpl.addCustomer(customer);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/customer/signIn")
	public String signIn(@RequestBody CustomerSignIn customer) throws Exception
	{
		return atmServiceImpl.validateSignIn(customer);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/customer/checkBalance")
	public String checkBalance(@RequestBody CustomerSignIn customer)
	{
		return atmServiceImpl.checkBalance(customer);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/customer/withdrawMoney")
	public String withdraw(@RequestBody CustomerWithDrawNDeposit customer)
	{
		return atmServiceImpl.withdraw(customer);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/customer/depositMoney")
	public String deposit(@RequestBody CustomerWithDrawNDeposit customer)
	{
		return atmServiceImpl.deposit(customer);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/customer/transferMoney")
	public String transfer(@RequestBody AmountTransfer customer)
	{
		return atmServiceImpl.transfer(customer);
	}
}
