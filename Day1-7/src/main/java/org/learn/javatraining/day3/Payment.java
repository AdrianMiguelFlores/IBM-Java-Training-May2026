package org.learn.javatraining.day3;

public abstract class Payment {
	private double amount;

	public Payment(double amount) {
		this.amount = amount;
	}

	public abstract void executePayment();

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
