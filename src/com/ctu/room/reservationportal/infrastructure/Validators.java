package com.ctu.room.reservationportal.infrastructure;
import java.sql.*;
import java.util.regex.Pattern;

public class Validators {
    private static final String URL = "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin123$";

    public static boolean isValidUsername(String username) {
        // Regular expression to validate username: at least 5 characters, including letters (uppercase or lowercase), and may include special characters and numbers
        String regex = "[\\w\\W]{5,}";
        return Pattern.matches(regex, username) && !existsInDatabase("SELECT COUNT(*) FROM userinfo WHERE username = ?", username, 1);
    }

    public static boolean isValidEmail(String email) {
        // Email validation regex to allow uppercase and lowercase letters before @, no digits after @, and require .com at the end
        String emailRegex = "(?i)^[a-z0-9._%+-]+@[a-z.-]+\\.com$";
        return Pattern.matches(emailRegex, email) && !existsInDatabase("SELECT COUNT(*) FROM userinfo WHERE email = ?", email, 1);
    }


    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Phone number format: Philippine style starting with +639 and followed by 9 digits
        String phoneRegex = "^(\\+639|09)\\d{9}$";
        return Pattern.matches(phoneRegex, phoneNumber) && !existsInDatabase("SELECT COUNT(*) FROM userinfo WHERE phoneNumber = ?", phoneNumber, 1);
    }


    private static boolean existsInDatabase(String query, String value, int columnIndex) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(columnIndex, value); // Set the parameter based on the given column index
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        System.out.println("It seems like you have provided an existing detail. Provide a new one");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurred or entry doesn't exist
    }

    public static boolean isValidName(String name) {
        // Regular expression to match names with only letters (uppercase or lowercase), spaces, and hyphens
        String regex = "^[a-zA-Z]+(?:-[a-zA-Z]+)*(?: [a-zA-Z]+(?:-[a-zA-Z]+)*)*$";
        return Pattern.matches(regex, name);
    }

    public static boolean isValidDate(String date) {
        // Date format: YYYY-MM-DD
        // Ensure month is between 01 and 12 and day is between 01 and 31
        return Pattern.matches("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[01])", date);
    }


    public static boolean isValidPassword(String password) {
        // Password must be 8 or more characters long
        return password.matches("^(?=.*[a-zA-Z\\d@$!%*?&\\-_/|]).{8,}$");
    }
    public static boolean isValidIDNumber(String idNumber) {
        // Validate the format of the ID number using a regular expression
        return idNumber.matches("\\d{6}"); // Assuming the ID number should be 6 digits
    }

   // public static boolean isValidEmail(String email) {
        // Email validation regex to allow uppercase and lowercase letters before @ and require .com at the end
     //   return Pattern.matches("(?i)^[a-z0-9._%+-]+@[a-z0-9.-]+\\.com$", email);
 //   }

   // public static boolean isValidPhoneNumber(String phoneNumber) {
        // Phone number format: Philippine style starting with +639 and followed by 9 digits
        //return phoneNumber.matches("^(\\+639|09)\\d{9}$");
    //}

    public static boolean isValidStreet(String street) {
        // Street format: [Alphanumeric with spaces and special characters]
        return Pattern.matches("[\\w\\d\\s.,'()-]+", street);
    }

    public static boolean isValidInt(String number) {
        return number.matches("\\d+");
    }

    public static boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("n") || gender.equalsIgnoreCase("Male")
                || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase ("Not to say") ;
    }

    public static boolean isValidLocation(String location) {
        // Location format: Alphabetic characters, spaces, and special characters
        return Pattern.matches("[a-zA-Z\\s.,'()-]+", location);
    }

    public static boolean isValidZIPCode(String zipCode) {
        // ZIP code format: Four digits
        return zipCode.matches("^\\d{4}$");
    }

    public static boolean isValidNationality(String nationality) {
        return NationalityValidator.isValidNationality(nationality);
    }
    public static boolean isValidInput(String fieldName, String userInput) {
        switch (fieldName) {
            case "First Name":
            case "Middle Name":
            case "Last Name":
            case "Nationality":
            case "Street":
            case "Barangay":
            case "City":
            case "Municipality":
                return Validators.isValidName(userInput);
            case "Birthdate":
                return Validators.isValidDate(userInput);
            case "Email":
                return Validators.isValidEmail(userInput);
            case "Phone Number":
                return Validators.isValidPhoneNumber(userInput);
            case "ZIP code":
                return Validators.isValidZIPCode(userInput);
            case "Gender":
                return Validators.isValidGender(userInput);
            default:
                return true;
        }
    }

    public boolean isValid(String input) {
        return true;
    }


}