package EmployeManagement;
import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nEmployee Management System");
                System.out.println("1. Add Employee");
                System.out.println("2. Update Employee");
                System.out.println("3. Delete Employee");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        updateEmployee();
                        break;
                    case 3:
                        deleteEmployee();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addEmployee() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?)")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            System.out.print("Enter employee designation: ");
            String designation = scanner.nextLine();
            System.out.print("Enter employee salary: ");
            double salary = scanner.nextDouble();

            statement.setString(1, name);
            statement.setString(2, designation);
            statement.setDouble(3, salary);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Failed to add employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployee() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE employees SET name=?, designation=?, salary=? WHERE id=?")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employee ID: ");
            int id = scanner.nextInt();

            System.out.print("Enter new employee name: ");
            String name = scanner.next();
            System.out.print("Enter new employee designation: ");
            String designation = scanner.next();
            System.out.print("Enter new employee salary: ");
            double salary = scanner.nextDouble();

            statement.setString(1, name);
            statement.setString(2, designation);
            statement.setDouble(3, salary);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id=?")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employee ID: ");
            int id = scanner.nextInt();

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
