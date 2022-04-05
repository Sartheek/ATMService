package com.project.exam.model;

public class AmountTransfer 
{
	private String payerCardNo;
	private String payeeCardNo;
	private String amount;
	
	public String getPayerCardNo() {
		return payerCardNo;
	}
	public void setPayerCardNo(String payerCardNo) {
		this.payerCardNo = payerCardNo;
	}
	public String getPayeeCardNo() {
		return payeeCardNo;
	}
	public void setPayeeCardNo(String payeeCardNo) {
		this.payeeCardNo = payeeCardNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
