package org.learn.javatraining.day6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

	private static final String URL = "jdbc:postgresql://localhost:5432/day6";
	private static final String USER = "postgres";
	private static final String PASSWORD = "poranges123";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String insertQuery = "INSERT INTO student (email, pass, firstname, lastname, dateadded, dateupdated) VALUES (?, ?, ?, ?, ?, ?)";
		String selectQuery = "SELECT * FROM student WHERE id = ?";
		String updateQuery = "UPDATE student SET pass = ? WHERE id = ?";
		String deleteQuery = "DELETE FROM student WHERE id = ?";

		while (true) {
			printMenu();
			String choice = scanner.nextLine();
			System.out.println();

			if (choice.equalsIgnoreCase("Q")) {
				System.out.println("Exit the program.");
				break;
			}

			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
					PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
					PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
					PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
				switch (choice) {
				case "A" -> {
					System.out.print("Enter email: ");
					String email = scanner.nextLine();

					System.out.print("Enter password: ");
					String password = scanner.nextLine();

					System.out.print("Enter firstname: ");
					String firstname = scanner.nextLine();

					System.out.print("Enter lastname: ");
					String lastname = scanner.nextLine();

					insertStmt.setString(1, email);
					insertStmt.setString(2, password);
					insertStmt.setString(3, firstname);
					insertStmt.setString(4, lastname);
					insertStmt.setObject(5, LocalDateTime.now());
					insertStmt.setObject(6, LocalDateTime.now());

					insertStmt.executeUpdate();
					System.out.println("New row successfully added");
				}
				case "V" -> {
					System.out.print("Enter an id: ");
					int id = Integer.parseInt(scanner.nextLine());
					selectStmt.setInt(1, id);

					try (ResultSet rs = selectStmt.executeQuery()) {
						while (rs.next()) {
							System.out.printf("%-20s %-15s %-15s %-15s %-15s %s%n", rs.getString("email"),
									rs.getString("pass"), rs.getString("firstname"), rs.getString("lastname"),
									rs.getString("dateadded"), rs.getString("dateupdated"));
						}
					}
				}
				case "U" -> {
					System.out.print("Enter new password: ");
					String password = scanner.nextLine();

					System.out.print("Enter target id: ");
					int id = Integer.parseInt(scanner.nextLine());

					updateStmt.setString(1, password);
					updateStmt.setInt(2, id);

					updateStmt.executeUpdate();
					System.out.println("Row " + id + " successfully updated");
				}
				case "D" -> {
					System.out.print("Enter an id to delete: ");
					int id = Integer.parseInt(scanner.nextLine());

					deleteStmt.setInt(1, id);
					deleteStmt.executeUpdate();

					System.out.println("Row with id " + id + " successfully deleted");
				}
				default -> {
					System.out.println("Invalid Input");
				}
				}
			} catch (SQLException e) {
				System.out.println("Trouble processing this request request. Reason: " + e.getMessage());
			}

		}

	}

	private static void printMenu() {
		System.out.println("=== MENU ===");
		System.out.println("[A]dd");
		System.out.println("[V]iew");
		System.out.println("[U]pdate Password");
		System.out.println("[D]elete");
		System.out.println("[Q]uit");
		System.out.print("\nEnter choice: ");
	}

}
