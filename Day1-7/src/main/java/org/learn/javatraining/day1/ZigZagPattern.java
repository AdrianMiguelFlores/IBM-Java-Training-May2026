package org.learn.javatraining.day1;

import java.util.Scanner;

public class ZigZagPattern {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int size = Integer.parseInt(input.nextLine());
		int sizeCounter = 0;
		for (int i = 0; i < size; i++) {
			if (i % 2 == 1) {
				sizeCounter += size;
				for (int j = 0; j < size; j++) {
					System.out.print(sizeCounter - j + " ");
				}
			} else {
				for (int j = 1; j <= size; j++) {
					System.out.print((sizeCounter + j) + " ");
				}
				sizeCounter += size;
			}
			System.out.println();
		}
	}
}
