package org.learn.javatraining.day2;

public class Main {

	public static void main(String[] args) {
		Library lib = new Library();

		lib.showAllBooks();

		lib.addBook(new Book("Harry Potter", "J.K. Rowling"));
		lib.addBook(new Book("Dune", "Frank Herbert"));
		lib.addBook(new Book("Noli Me Tangere", "Jose Rizal"));
		lib.addBook(new Book("Noli Me Tangere", "Jose Rizal"));

		lib.showAllBooks();

		lib.borrowBook("Sample");
		lib.borrowBook("Harry Potter");

		lib.showAllBooks();

		lib.returnBook("Sample");
		lib.returnBook("Harry Potter");

		lib.showAllBooks();
	}

}
