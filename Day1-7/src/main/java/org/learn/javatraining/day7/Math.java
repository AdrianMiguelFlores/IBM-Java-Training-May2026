package org.learn.javatraining.day7;

public class Math {
	public static float add(float a, float b) {
		return a + b;
	}
	
	public static float subtract(float a, float b) {
		return a - b;
	}
	
	public static float multiply(float a, float b) {
		return a * b;
	}
	
	public static float divide(float a, float b) {
		if(b == 0) {
			throw new ArithmeticException("Divisor must not be zero");
		}
		return a / b;
	}
}
