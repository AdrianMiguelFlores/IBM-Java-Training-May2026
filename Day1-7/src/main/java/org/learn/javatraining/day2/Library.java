package org.learn.javatraining.day2;

import java.util.ArrayList;
import java.util.List;

public class Library {
	List<Book> books = new ArrayList<>();

	public void addBook(Book book) {
		for (Book b : books) {
			if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
				System.out.println(book.getTitle() + " already exist");
				return;
			}
		}
		books.add(book);
		System.out.println(book.getTitle() + " successfully added.");
	}

	public void showAllBooks() {
		if (books.isEmpty()) {
			System.out.println("Books in the library is empty.");
			return;
		}
		System.out.println("\nBooks:");
		books.forEach(book -> book.getInfo());
		System.out.println();
	}

	public void borrowBook(String title) {
		for (Book b : books) {
			if (b.getTitle().equals(title)) {
				b.borrowBook();
				return;
			}
		}
		System.out.println(title + " is not found in the library.");
	}

	public void returnBook(String title) {
		for (Book b : books) {
			if (b.getTitle().equals(title)) {
				b.returnBook();
				return;
			}
		}
		System.out.println(title + " is not found in the library.");
	}
}
