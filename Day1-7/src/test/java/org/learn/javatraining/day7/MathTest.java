package org.learn.javatraining.day7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class MathTest {

	@Test
	void should_ReturnCorrectAddition_WhenTwoNumbers() {
		float a = 10;
		float b = 5;
		float expected = 15;
		
		float actual = Math.add(a, b);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ReturnCorrectSubtraction_WhenTwoNumbers() {
		float a = 10;
		float b = 5;
		float expected = 5;
		
		float actual = Math.subtract(a, b);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ReturnCorrectMultiplication_WhenTwoNumbers() {
		float a = 10;
		float b = 5;
		float expected = 50;
		
		float actual = Math.multiply(a, b);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ReturnCorrectDivision_WhenTwoNumbers() {
		float a = 10;
		float b = 5;
		float expected = 2;
		
		float actual = Math.divide(a, b);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void should_ThrowArithmeticException_WhenDivisorIsZero() {
		float a = 10;
		float b = 0;
		
		Executable executable = () -> Math.divide(a, b);
		
		assertThrows(ArithmeticException.class, executable);
	}

}
