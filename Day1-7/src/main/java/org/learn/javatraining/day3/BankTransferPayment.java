package org.learn.javatraining.day3;

public class BankTransferPayment extends Payment implements Verifiable {

	private String accountNumber;

	public BankTransferPayment(double amount, String accountNumber) {
		super(amount);
		this.accountNumber = accountNumber;
	}

	@Override
	public boolean verifyPaymentDetails() {
		return accountNumber.length() == 10;
	}

	@Override
	public void executePayment() {
		System.out.println("Processing bank transfer...");
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
