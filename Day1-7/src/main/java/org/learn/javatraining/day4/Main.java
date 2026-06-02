package org.learn.javatraining.day4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Alice", "IT", 55000));
		employees.add(new Employee("Bob", "Finance", 60000));
		employees.add(new Employee("Alice", "HR", 52000)); // duplicate name
		employees.add(new Employee("Ken", "IT", 60000));
		employees.add(new Employee("Maria", "HR", 50000));
		employees.add(new Employee("John", "Finance", 70000));
		employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
		employees.add(new Employee("Lara", "IT", 62000));
		employees.add(new Employee("Sam", "HR", 48000));
		employees.add(new Employee("Bob", "IT", 59000)); // duplicate name

		Set<String> uniqueNames = new HashSet<>();

		List<Employee> uniqueEmployees = new ArrayList<>();
		for (Employee emp : employees) {
			if (uniqueNames.add(emp.getName())) {
				uniqueEmployees.add(emp);
			}
		}

		System.out.println("=== Unique Employees ===");

		uniqueEmployees.forEach(emp -> {
			System.out.println(emp);
		});

		System.out.println("\n=== Employees by Department ===");

		Map<String, List<Employee>> deptToEmp = new HashMap<>();

		for (Employee emp : employees) {
			deptToEmp.computeIfAbsent(emp.getDepartment(), k -> new ArrayList<>()).add(emp);
		}

		for (Map.Entry<String, List<Employee>> entry : deptToEmp.entrySet()) {
			System.out.println(entry.getKey() + ":");
			for (Employee emp : entry.getValue()) {
				System.out.println("  - " + emp);
			}
		}

		System.out.println("\n=== Highest Paid per Department ===");

		Comparator<Employee> bySalary = Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName)
				.reversed();
		for (Map.Entry<String, List<Employee>> entry : deptToEmp.entrySet()) {
			System.out.print(entry.getKey() + ": ");
			entry.getValue().sort(bySalary);
			System.out.println(entry.getValue().getFirst());
		}

		System.out.println("\n=== Employees Sorted by Salary (Desc) ===");

		employees.sort(bySalary);
		employees.forEach(System.out::println);

		System.out.println("\n=== Unique Salaries (Sorted) ===");

		Set<Double> salaries = new TreeSet<>();
		for (Employee emp : employees) {
			salaries.add(emp.getSalary());
		}
		salaries.forEach(System.out::println);
	}
}
