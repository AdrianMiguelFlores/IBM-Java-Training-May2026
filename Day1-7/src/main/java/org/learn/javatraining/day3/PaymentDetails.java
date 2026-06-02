package org.learn.javatraining.day3;

public record PaymentDetails(int transactionId, double amount, String paymentMethod, String timeStamp) {
}
