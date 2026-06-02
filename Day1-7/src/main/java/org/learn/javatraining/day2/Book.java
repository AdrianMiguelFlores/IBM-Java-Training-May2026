package org.learn.javatraining.day2;

public class Book {
	private String title;
	private String author;
	private boolean available;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void borrowBook() {
		if (available) {
			available = false;
			System.out.println(title + " successfully borrowed.");
		} else {
			System.out.println(title + " already borrowed.");
		}
	}

	public void returnBook() {
		if (available) {
			System.out.println(title + " already returned.");
			return;
		}
		System.out.println(title + " successfully returned.");
		available = true;
	}

	public void getInfo() {
		System.out.printf("%-25s %-25s %s%n", title, author, (available ? "Available" : "Not Available"));
	}

}
