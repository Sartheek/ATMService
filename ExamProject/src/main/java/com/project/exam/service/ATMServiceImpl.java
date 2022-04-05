package com.project.exam.service;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.dao.ATMRepository;
import com.project.exam.model.AmountTransfer;
import com.project.exam.model.CustomerSignIn;
import com.project.exam.model.CustomerSignUp;
import com.project.exam.model.CustomerWithDrawNDeposit;

@Service("atmServiceImpl")
public class ATMServiceImpl implements ATMService
{
	@Autowired
	private ATMRepository atmRepository;

	public String validateSignIn(CustomerSignIn customer) 
	{
		String customerName = atmRepository.getCustomerName(customer.getCardNo(), customer.getPin());
		if(customerName!=null)
		{
			return customerName;
		}
		return null;
	}
	
	public CustomerSignIn addCustomer(CustomerSignUp customer)
	{
		Random rnd = new Random();
	    String pin = String.valueOf(rnd.nextInt(9999));
	    String cardNo = String.valueOf((Math.random()*Math.pow(12,12)));
	    customer.setPin(pin);
	    customer.setCardNo(cardNo);
		atmRepository.insertCustomer(customer.getName(), customer.getCardNo(), customer.getPin(), customer.getAmount(), customer.getAddress().getAddressLine1(), 
				customer.getAddress().getAddressLine2(), customer.getAddress().getCity(), customer.getAddress().getState(), customer.getAddress().getZipcode());
		CustomerSignIn signInData = new CustomerSignIn();
		signInData.setCardNo(cardNo);
		signInData.setPin(pin);
		return signInData;
	}

	public String checkBalance(CustomerSignIn customer) 
	{
		String amount = atmRepository.getAccountBalance(customer.getCardNo());
		if(amount!=null)
		{
			return amount;
		}
		return "0";
	}

	@Transactional
	public String withdraw(CustomerWithDrawNDeposit customer) 
	{
		int amountInBank = Integer.parseInt(atmRepository.getAccountBalance(customer.getCardNo()));
		try
		{
			if(amountInBank>=customer.getAmount())
			{
				atmRepository.insertTransaction(customer.getCardNo(), String.valueOf(customer.getAmount()), "Debit");
				atmRepository.updateAccountBalance(customer.getCardNo(), String.valueOf(amountInBank-customer.getAmount()));
				return "Success";
			}
			return "Withdrawal failed, check your balance.";
		}
		catch(Exception e)
		{
			return "Withdrawal failed, please contact administrator.";
		}
	}

	@Transactional
	public String deposit(CustomerWithDrawNDeposit customer) 
	{
		int amountInBank = Integer.parseInt(atmRepository.getAccountBalance(customer.getCardNo()));
		try
		{
			atmRepository.insertTransaction(customer.getCardNo(), String.valueOf(customer.getAmount()), "Credit");
			atmRepository.updateAccountBalance(customer.getCardNo(), String.valueOf(amountInBank+customer.getAmount()));
		}
		catch(Exception e)
		{
			return "Deposit failed, please contact administrator.";
		}
		return "Success";
	}

	@Transactional
	public String transfer(AmountTransfer customer) 
	{
		try
		{
			int payerBalance = Integer.parseInt(atmRepository.getAccountBalance(customer.getPayerCardNo()));
			int payeeBalance = Integer.parseInt(atmRepository.getAccountBalance(customer.getPayeeCardNo()));
			
			atmRepository.insertTransaction(customer.getPayerCardNo(), customer.getAmount(), "Debit");
			atmRepository.insertTransaction(customer.getPayeeCardNo(), customer.getAmount(), "Credit");
			atmRepository.updateAccountBalance(customer.getPayerCardNo(), String.valueOf(payerBalance-Integer.parseInt(customer.getAmount())));
			atmRepository.updateAccountBalance(customer.getPayeeCardNo(), String.valueOf(payeeBalance+Integer.parseInt(customer.getAmount())));
		}
		catch(Exception e)
		{
			return "Transfer failed, please contact administrator.";
		}
		return "Success";
	}
}
