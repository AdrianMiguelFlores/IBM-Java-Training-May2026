package org.learn.javatraining.day3;

public class PayPalPayment extends Payment implements Verifiable {

	private String email;

	public PayPalPayment(double amount, String email) {
		super(amount);
		this.email = email;
	}

	@Override
	public boolean verifyPaymentDetails() {
		return email.contains("@");
	}

	@Override
	public void executePayment() {
		System.out.println("Processing PayPal payment...");

	}

	public String getEmail() {
		return email;
	}

}
