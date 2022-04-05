package com.project.exam.model;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Valid
public class CustomerSignIn 
{
	@NotNull
	@Min(12)
	@Max(12)
	private String cardNo;

	@NotNull
	@Min(4)
	@Max(4)
	private String pin;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
}
