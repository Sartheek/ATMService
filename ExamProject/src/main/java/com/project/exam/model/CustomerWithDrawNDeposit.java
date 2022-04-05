package com.project.exam.model;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Valid
public class CustomerWithDrawNDeposit 
{
	@NotNull
	@Min(12)
	@Max(12)
	private String cardNo;

	@NotNull
	@Min(4)
	@Max(4)
	private String pin;

	@NotNull
	@Min(1)
	@Max(8)
	private int amount;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
