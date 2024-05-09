package DTO;

import java.util.regex.Pattern;

public class Validators {

    public static boolean isValidName(String name) {
        String regex = "([A-Z][a-z]*(\\s[A-Z][a-z]*)*)";
        return Pattern.matches(regex, name);
    }

    public static boolean isValidDate(String date) {
        // Date format: YYYY-MM-DD
        return Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date);
    }

    public static boolean isValidEmail(String email) {
        // Simple email validation regex (only lowercase letters)
        return Pattern.matches("\\b[a-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b", email);
    }

    public static boolean isValidStreet(String street) {
        // Allow uppercase letter at the beginning, followed by alphanumeric characters, spaces, and common punctuation
        return Pattern.matches("^[A-Z][\\w\\d\\s.,'()-]*$", street);
    }
    public static boolean isValidInt(String number) {
        return number.matches("\\d+");
    }

    public static boolean isValidGender(String gender) {
        // Accept "Male" (or "M"), "Female" (or "F"), or "Not to say" (or "N") (case-insensitive)
        return (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("M")) ||
                (gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("F")) ||
                (gender.equalsIgnoreCase("Not to say") || gender.equalsIgnoreCase("N"));
    }


    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Philippine phone number format: +639 followed by 9 digits
        return Pattern.matches("^\\+639\\d{9}$", phoneNumber);
    }

    public static boolean isValidLocation(String location) {
        // Location format: [Alphanumeric with spaces and special characters]
        return Character.isUpperCase(location.charAt(0)) && Pattern.matches("[\\w\\d\\s.,'()-]+", location);
    }

    public static boolean isValidZIPCode(String zipCode) {
        // ZIP code format: Four digits
        return zipCode.matches("^\\d{4}$");
    }

    public static boolean isValidId(String input) {
        // Add your validation logic for the admin user ID number
        // Return true if the input is valid, false otherwise
        return input.matches("[A-Za-z0-9]+");
    }

    public static boolean isValidAdminID(String adminID) {
        // Admin ID format: Six digits
        return adminID.matches("^\\d{6}$");
    }

    public boolean isValidInput(String fieldName, String userInput) {
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
}

