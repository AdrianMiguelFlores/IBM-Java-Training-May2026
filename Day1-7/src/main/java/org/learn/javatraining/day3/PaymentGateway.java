package org.learn.javatraining.day3;

public non-sealed class PaymentGateway extends Gateway {
	public void processPayment(Payment payment) {
		payment.executePayment();
		System.out.println("Payment has been processed");
	}
}
