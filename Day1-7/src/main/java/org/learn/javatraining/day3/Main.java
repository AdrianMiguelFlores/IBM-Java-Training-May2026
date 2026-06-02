package org.learn.javatraining.day3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Payment> payments = new ArrayList<>(List.of(new CreditCardPayment(10_000, "1234567891234567"),
				new PayPalPayment(5_000, "sample@email.com"), new BankTransferPayment(8_000, "0000011111")));

		List<PaymentDetails> paymentDetails = new ArrayList<>();
		PaymentType onlineType = new OnlinePaymentType();
		PaymentType offlineType = new OfflinePaymentType();

		Gateway paymentGateway = new PaymentGateway();

		int transactionIdCounter = 0;
		for (Payment payment : payments) {
			Verifiable verifiablePayment = (Verifiable) payment;
			if (verifiablePayment.verifyPaymentDetails()) {
				((PaymentGateway) paymentGateway).processPayment(payment);
				paymentDetails.add(new PaymentDetails(transactionIdCounter++, payment.getAmount(),
						payment.getClass().getSimpleName(), LocalDateTime.now().toString()));
			} else {
				System.out.println("Verification Failed.");
			}
			System.out.println();
		}

		System.out.println("\nPayment Details\n");
		paymentDetails.forEach(pd -> {
			System.out.printf("%-10s %-15.2f %-25s %s%n", pd.transactionId(), pd.amount(), pd.paymentMethod(),
					pd.timeStamp());
		});
	}
}
