package org.learn.javatraining.day1;

import java.util.Scanner;

public class DayOne {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		formatProblemName("Black Jack");
		System.out.println("blackjack(1,2) = " + blackjack(1, 2));
		System.out.println("blackjack(21,22) = " + blackjack(21, 22));
		System.out.println("blackjack(22,22) = " + blackjack(22, 22));
		System.out.println("blackjack(2,10) = " + blackjack(2, 10));

		System.out.println();

		formatProblemName("Day Of The Week");
		System.out.print("Enter day of the week (1 - 7): ");
		int day = Integer.parseInt(input.nextLine());

		dayOfTheWeek(day);

		System.out.println();

		formatProblemName("Number Pyramid");
		System.out.print("Enter a number between 1 and 20: ");
		int N = Integer.parseInt(input.nextLine());
		while (N < 1 || N > 20) {
			System.out.println("Number must be between 1 and 20");
			System.out.print("Enter again: ");
			N = Integer.parseInt(input.nextLine());
		}
		numberPyramid(N);
	}

	private static int blackjack(int a, int b) {
		if (a > 21)
			a = 0;
		if (b > 21)
			b = 0;

		return a > b ? a : b;
	}

	private static void dayOfTheWeek(int day) {
		System.out.println("\nSwitch");
		switch (day) {
		case 1:
			System.out.println(day + " -> Monday");
			break;
		case 2:
			System.out.println(day + " -> Tuesday");
			break;
		case 3:
			System.out.println(day + " -> Wednesday");
			break;
		case 4:
			System.out.println(day + " -> Thursday");
			break;
		case 5:
			System.out.println(day + " -> Friday");
			break;
		case 6:
			System.out.println(day + " -> Saturday");
			break;
		case 7:
			System.out.println(day + " -> Sunday");
			break;
		default:
			System.out.println("Invalid day number");
			break;
		}

		System.out.println("\nAlternative Solution (Pattern Matching)");
		switch (day) {
		case 1 -> System.out.println(day + " -> Monday");
		case 2 -> System.out.println(day + " -> Tuesday");
		case 3 -> System.out.println(day + " -> Wednesday");
		case 4 -> System.out.println(day + " -> Thursday");
		case 5 -> System.out.println(day + " -> Friday");
		case 6 -> System.out.println(day + " -> Saturday");
		case 7 -> System.out.println(day + " -> Sunday");
		default -> System.out.println("Invalid day number");
		}
	}

	private static void numberPyramid(int N) {
		System.out.println("For Loop");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print((j + 1) + " ");
			}
			System.out.println();
		}
		System.out.println("\nWhile Loop");
		int i = 0;
		while (i++ < N) {
			int j = 0;
			while (j < i) {
				System.out.print(++j + " ");
			}
			System.out.println();
		}

		System.out.println("\nDo-while Loop");
		i = 1;
		do {
			int j = 0;
			do {
				System.out.print(++j + " ");
			} while (j < i);
			System.out.println();
		} while (i++ < N);
	}

	public static void formatProblemName(String name) {
		System.out.println("=".repeat(name.length() + 5));
		System.out.println(" ".repeat(name.length() / 4) + name);
		System.out.println("=".repeat(name.length() + 5));
	}
}
