package com.project.exam.service;

import com.project.exam.model.AmountTransfer;
import com.project.exam.model.CustomerSignIn;
import com.project.exam.model.CustomerSignUp;
import com.project.exam.model.CustomerWithDrawNDeposit;

public interface ATMService 
{
	CustomerSignIn addCustomer(CustomerSignUp customer);

	String validateSignIn(CustomerSignIn customer);

	String checkBalance(CustomerSignIn customer);

	String withdraw(CustomerWithDrawNDeposit customer);

	String deposit(CustomerWithDrawNDeposit customer);

	String transfer(AmountTransfer customer);
}
