package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;
import java.sql.*;
import java.util.Scanner;

import static com.ctu.room.reservationportal.infrastructure.Validators.isValidInput;

/**
 * Class that holds the execution for user to update their information
 */
public class UpdateUserInfo {
    /**
     * Prompts for username and password
     */
    public void promptAndUpdate() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter username and password
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Create an UserInfo object with the provided username and password
        UserInfo user = new UserInfo();
        user.setUserName(username);
        user.setPassword(password);

        // Check if user exists in the database
        UserInfo foundUser = getUserDetailsFromDatabase(user);

        if (foundUser != null) {

            // User found, display details
            displayUserDetails(foundUser);

            // Prompt for update
            promptUpdateFields(foundUser);
        } else {
            // user not found
            System.out.println("User not found.");
        }
    }

    /**
     * Retrieving details from the database
     * @param user
     * @return
     */
    private UserInfo getUserDetailsFromDatabase(UserInfo user) {
        // Establishing a connection to a MySQL database
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "admin123$")) {

            // Query for searching inside the database using username and password
            String selectQuery = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, user.getUserName());
                selectStatement.setString(2, user.getPassword());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // User found, construct and return UserInfo object
                        UserInfo updatedUser = new UserInfo();
                        updatedUser.setUserName(resultSet.getString("username"));
                        updatedUser.setPassword(resultSet.getString("password"));
                        updatedUser.setFirstName(resultSet.getString("firstName"));
                        updatedUser.setMiddleName(resultSet.getString("middleName"));
                        updatedUser.setLastName(resultSet.getString("lastName"));
                        updatedUser.setBirthDate(resultSet.getString("birthdate"));
                        updatedUser.setEmail(resultSet.getString("email"));
                        updatedUser.setPhoneNumber(resultSet.getString("phoneNumber"));
                        updatedUser.setStreet(resultSet.getString("street"));
                        updatedUser.setBarangay(resultSet.getString("barangay"));
                        updatedUser.setCity(resultSet.getString("city"));
                        updatedUser.setMunicipality(resultSet.getString("municipality"));
                        updatedUser.setZIPcode(resultSet.getInt("zipcode"));
                        updatedUser.setGender(resultSet.getString("gender"));
                        updatedUser.setNationality(resultSet.getString("nationality"));
                        updatedUser.setRoleAtschool(resultSet.getString("roleAtSchool"));
                        return updatedUser;
                    } else {
                        // User not found, return null
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void displayUserDetails(UserInfo user) {
        System.out.println("User Details:");
        System.out.println("Username: " + user.getUserName());
        System.out.println("Password: " + user.getPassword());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Middle Name: " + user.getMiddleName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Birthdate: " + user.getBirthdate());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Street: " + user.getStreet());
        System.out.println("Barangay: " + user.getBarangay());
        System.out.println("Municipality: " + user.getMunicipality());
        System.out.println("City: " + user.getCity());
        System.out.println("ZIP Code: " + user.getZIPcode());
        System.out.println("Nationality: " + user.getNationality());
        System.out.println("Gender: " + user.getGender());
        System.out.println("Role at School: " + user.getRoleAtschool());
    }

    private void promptUpdateFields(UserInfo user) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose a field to update:");
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("3. First Name");
            System.out.println("4. Middle Name");
            System.out.println("5. Last Name");
            System.out.println("6. Birthdate");
            System.out.println("7. Email");
            System.out.println("8. Phone Number");
            System.out.println("9. Street");
            System.out.println("10. Barangay");
            System.out.println("11. City");
            System.out.println("12. Municipality");
            System.out.println("13. ZIP Code");
            System.out.println("14. Gender");
            System.out.println("15. Nationality");
            System.out.println("16. Role at School");
            System.out.println("17. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 17) {
                exit = true;
            } else if (choice >= 1 && choice <= 16) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Enter the new value: ");
                    String newValue = scanner.nextLine();

                    if (isValidInput(String.valueOf(choice), newValue)) {
                        updateDetails(choice, newValue, user);
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    public void updateDetails(int choice, String newValue, UserInfo user) {
        String columnName = null;
        boolean isValidInput = false;

        // Convert newValue to lowercase
        newValue = newValue.toLowerCase();

        // Capitalize the first letter
        newValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1);


        do {
            switch (choice) {
                case 1:
                    columnName = "userName";
                    if (!Validators.isValidUsername(newValue)) {
                        System.out.println("Invalid username format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 2:
                    columnName = "password";
                    if (!Validators.isValidPassword(newValue)) {
                        System.out.println("Invalid password format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 3:
                    columnName = "firstName";
                    if (!Validators.isValidName(newValue)) {
                        System.out.println("Invalid first name format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 4:
                    columnName = "middleName";
                    if (!Validators.isValidName(newValue)) {
                        System.out.println("Invalid middle name format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 5:
                    columnName = "lastName";
                    if (!Validators.isValidName(newValue)) {
                        System.out.println("Invalid last name format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 6:
                    columnName = "birthdate";
                    if (!Validators.isValidDate(newValue)) {
                        System.out.println("Invalid birthdate format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 7:
                    columnName = "email";
                    if (!Validators.isValidEmail(newValue)) {
                        System.out.println("Invalid email format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 8:
                    columnName = "phoneNumber";
                    if (!Validators.isValidPhoneNumber(newValue)) {
                        System.out.println("Invalid phone number format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 9:
                    columnName = "street";
                    if (!Validators.isValidStreet(newValue)) {
                        System.out.println("Invalid street format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 10:
                    columnName = "barangay";
                    if (!Validators.isValidLocation(newValue)) {
                        System.out.println("Invalid barangay format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 11:
                    columnName = "city";
                    if (!Validators.isValidLocation(newValue)) {
                        System.out.println("Invalid city format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 12:
                    columnName = "municipality";
                    if (!Validators.isValidLocation(newValue)) {
                        System.out.println("Invalid municipality format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 13:
                    columnName = "ZIPcode";
                    if (!Validators.isValidZIPCode(newValue)) {
                        System.out.println("Invalid ZIP code format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 14:
                    columnName = "gender";
                    String lowerCaseValue = newValue.toLowerCase(); // Convert to lowercase for easier comparison
                    String gender;
                    switch (lowerCaseValue) {
                        case "f":
                        case "female":
                            gender = "Female";
                            isValidInput = true;
                            break;
                        case "m":
                        case "male":
                            gender = "Male";
                            isValidInput = true;
                            break;
                        case "n":
                        case "not to say":
                            gender = "Not to say";
                            isValidInput = true;
                            break;
                        default:
                            System.out.println("Invalid gender format.");
                            return; // Exit the switch statement if input is invalid
                    }
                    newValue = gender;
                    break;

                case 15:
                    columnName = "nationality";
                    if (!Validators.isValidNationality(newValue)) {
                        System.out.println("Invalid nationality format.");
                        break;
                    }
                    isValidInput = true;
                    break;
                case 16:
                    columnName = "roleAtschool";
                    if (!newValue.equalsIgnoreCase("Teacher") && !newValue.equalsIgnoreCase("Student") && !newValue.equalsIgnoreCase("Staff")) {
                        System.out.println("Invalid role at school. Please enter 'teacher', 'student', or 'staff'.");
                        break;
                    } else {
                        // Convert the first character to uppercase and the rest to lowercase
                        newValue = newValue.substring(0, 1).toUpperCase() + newValue.substring(1).toLowerCase();
                        isValidInput = true;
                    }
                    break;

                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            if (!isValidInput) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the new value again: ");
                newValue = scanner.nextLine().toLowerCase(); // Convert to lowercase
                newValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1); // Capitalize first letter
            }

        } while (!isValidInput);

        // Update logic
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "admin123$")) {

            // First, retrieve the user details from the database
            String selectQuery = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, user.getUserName());
                selectStatement.setString(2, user.getPassword());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Display existing user details...
                    } else {
                        System.out.println("User not found.");
                        return;
                    }
                }
            }

            // Then, update the specified field in the userinfo table for the matching rows
            String updateQuery = "UPDATE userinfo SET " + columnName + " = ? WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newValue);
                preparedStatement.setString(2, user.getUserName());
                preparedStatement.setString(3, user.getPassword());

                // Execute the update query...
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Detail updated successfully.");
                    System.out.println(" ");

                    // Retrieve and display all updated details from the database
                    UserInfo updatedUser = getUserDetailsFromDatabase(user);
                    if (updatedUser != null) {
                        displayUserDetails(updatedUser);
                    }
                } else {
                    System.out.println("Failed to update the detail.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
