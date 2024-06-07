package com.ctu.room.reservationportal.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

/**
 * Class for searching and retrieving details from database
 * Variables are declared final because these are constants
 * Encapsulated for this is a connection to the database so must be private
 */
public class SearchAndRetrieve {
    /**
     * Database URL.
     */
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "admin123$";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Method for executing the search and retrieve
     *
     * @param
     */
    public static void searchAndRetrieve() {
        try {
            // Create a Scanner object for user input
            Scanner scanner = new Scanner(System.in);
            Connection connection = getConnection();
            int choice = 0; // Initialize choice outside the do-while loop

            do {
                // A menu for you to choose of what type of details you will use to search
                System.out.println("+--------------------------------------------+");
                System.out.println("|        Choose search criteria:             |");
                System.out.println("+--------------------------------------------+");
                System.out.println("|  Option  |           Criteria              |");
                System.out.println("+--------------------------------------------+");
                System.out.println("|    1     |     Search by username          |");
                System.out.println("|    2     |     Search by first name,       |");
                System.out.println("|          |     middle name, last name,     |");
                System.out.println("|          |     or ID number                |");
                System.out.println("|    3     |     Search by other details     |");
                System.out.println("+--------------------------------------------+");
                System.out.print("Enter your choice: ");
                try {
                    // Reads an integer input from the user and assigns it to the variable choice.
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    // Handle the InputMismatchException
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume the invalid input
                    continue; // Skip the rest of the loop iteration
                }

                //Starts switch statement, evaluates the value of the variable choice to determine which case to execute.
                switch (choice) {
                    /*
                     * connection is passed because it represents a connection to the database
                     * scanner is used to collect user input from the console
                     */
                    case 1:
                        usernameSearch(connection, scanner);
                        break;
                    case 2:
                        filteredSearch(connection, scanner);
                        break;
                    case 3:
                        editProfileSearch(connection, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * A method for searching using username only
     *
     * @param connection
     * @param scanner
     * @throws SQLException
     */
    private static void usernameSearch(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter username:");
        String username = scanner.nextLine().trim();

        // Requesting for data from a database
        // adminfo is the TABLE FROM database
        String sql = "SELECT * FROM userinfo WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                displayResult(resultSet);
            } else {
                System.out.println("No details found for the provided username.");
            }
        }
    }

    /**
     * A method for filtered searches (first name, middle name, last name, and ID Number)
     *
     * @param connection
     * @param scanner
     * @throws SQLException
     */
    private static void filteredSearch(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃           Choose field to search       ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 1. First Name                          ┃");
        System.out.println("┃ 2. Middle Name                         ┃");
        System.out.println("┃ 3. Last Name                           ┃");
        System.out.println("┃ 4. ID Number                           ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String field;
        switch (choice) {
            case 1:
                field = "firstName";
                break;
            case 2:
                field = "middleName";
                break;
            case 3:
                field = "lastName";
                break;
            case 4:
                field = "idNumber";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine().trim();

        String sql = "SELECT * FROM userinfo WHERE " + field + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, searchTerm);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                displayResult(resultSet);
            } else {
                System.out.println("No details found for the provided search term.");
            }
        }
    }

    /**
     * A method for searching those updatable profile details aside from those in filtered search
     *
     * @param connection
     * @param scanner
     * @throws SQLException
     */

    private static void editProfileSearch(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                Choose search query          ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 1. Search by birthdate                      ┃");
        System.out.println("┃ 2. Search by email                          ┃");
        System.out.println("┃ 3. Search by phone number                   ┃");
        System.out.println("┃ 4. Search by street                         ┃");
        System.out.println("┃ 5. Search by barangay                       ┃");
        System.out.println("┃ 6. Search by municipality                   ┃");
        System.out.println("┃ 7. Search by city                           ┃");
        System.out.println("┃ 8. Search by nationality                    ┃");
        System.out.println("┃ 9. Search by gender                         ┃");
        System.out.println("┃ 10. Search by role at school                ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("Enter your choice: ");


        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String column;
        switch (choice) {
            case 1:
                column = "birthdate";
                break;
            case 2:
                column = "email";
                break;
            case 3:
                column = "phoneNumber";
                break;
            case 4:
                column = "street";
                break;
            case 5:
                column = "barangay";
                break;
            case 6:
                column = "municipality";
                break;
            case 7:
                column = "city";
                break;
            case 8:
                column = "nationality";
                break;
            case 9:
                column = "gender";
                break;
            case 10:
                column = "roleAtSchool";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine().trim();

        if (column.equals("phoneNumber") && searchTerm.startsWith("09")) {
            searchTerm = "+63" + searchTerm.substring(1);
        }

        if (column.equals("gender")) {
            switch (searchTerm.toLowerCase()) {
                case "f":
                case "female":
                    searchTerm = "female";
                    break;
                case "m":
                case "male":
                    searchTerm = "male";
                    break;
                case "n":
                case "not to say":
                    searchTerm = "not to say";
                    break;
                default:
                    System.out.println("Invalid gender search term.");
                    return;
            }
        }



        String sql = "SELECT * FROM userinfo WHERE " + column + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, searchTerm);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    displayResult(resultSet);
                } else {
                    System.out.println("No details found for the provided search term.");
                }
            }
        }
    }

    /**
     * A method to retrieve display the matched result from database
     * ResultSet represents a set of rows retrieved from a database after executing a query
     *
     * @param resultSet
     * @throws SQLException
     */
    private static void displayResult(ResultSet resultSet) throws SQLException {
        // Retrieve and display data from the result set
        System.out.printf("+--------------------------+------------------+------------------------------+-----------------------+---------------------+------------------+--------------------------------------------+---------------+--------------------------------------------------------------+-----------------+-----------------+-----------------+%n");
        System.out.printf("|        Username          |     ID Number    |           First Name         |      Middle Name      |      Last Name      |    Birthdate     |                  Email                     |  Phone Number |                            Home Address                      |   Nationality   |     Gender      |  Role at School |%n");
        System.out.printf("+--------------------------+------------------+------------------------------+-----------------------+---------------------+------------------+--------------------------------------------+---------------+--------------------------------------------------------------+-----------------+-----------------+-----------------+%n");

        do {
            System.out.printf("| %-24s | %-16s | %-28s | %-21s | %-19s | %-16s | %-42s | %-13s | %-60s | %-15s | %-15s | %-15s |%n",
                    resultSet.getString("userName"),
                    resultSet.getString("idNumber"),
                    resultSet.getString("firstName"),
                    resultSet.getString("middleName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("birthDate"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("street") + ", " + resultSet.getString("barangay") + ", " + resultSet.getString("municipality") + ", " + resultSet.getString("city") + ", " + resultSet.getString("zipcode"),
                    resultSet.getString("nationality"),
                    resultSet.getString("gender"),
                    resultSet.getString("roleAtschool"));
        } while (resultSet.next());
        System.out.printf("+--------------------------+------------------+------------------------------+-----------------------+---------------------+------------------+--------------------------------------------+---------------+--------------------------------------------------------------+-----------------+-----------------+-----------------+%n");
        while (resultSet.next());
    }
}

