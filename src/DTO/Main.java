package DTO;

import java.util.Scanner;

public class Main {

    public static String promptForInput(Scanner scanner, String promptMessage, Validator validator) {
        String input;
        boolean isValid;
        do {
            System.out.print(promptMessage);
            input = scanner.nextLine();
            isValid = validator.isValid(input);
            if (!isValid) {
                System.out.println("Please input correctly.");
            }
        } while (!isValid);
        return input;
    }

    interface Validator {
        boolean isValid(String input);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserInfo userInfo = new UserInfo();
        System.out.println("\n     WELCOME TO USER REGISTRATION!");
        System.out.println("PLEASE ENTER THE RIGHT DETAILS CORRECTLY\n");

        // Prompt for user details
        String firstName = promptForInput(scanner, "Enter First name: ", Validators::isValidName);
        userInfo.setFirstName(firstName);

        String middleName = promptForInput(scanner, "Enter Middle name: ", Validators::isValidName);
        userInfo.setMiddleName(middleName);

        String lastName = promptForInput(scanner, "Enter Last name: ", Validators::isValidName);
        userInfo.setLastName(lastName);

        String birthdate = promptForInput(scanner, "Enter Birthdate (YYYY-MM-DD): ", Validators::isValidDate);
        userInfo.setBirthDate(birthdate);

        String email = promptForInput(scanner, "Enter Email: ", Validators::isValidEmail);
        userInfo.setEmail(email);

        String phoneNumber = promptForInput(scanner, "Enter Phone Number: ", Validators::isValidPhoneNumber);
        userInfo.setPhoneNumber(phoneNumber);

        String street = promptForInput(scanner, "Enter Street: ", Validators::isValidStreet);
        userInfo.setStreet(street);

        String barangay = promptForInput(scanner, "Enter Barangay: ", Validators::isValidLocation);
        userInfo.setBarangay(barangay);

        String municipality = promptForInput(scanner, "Enter Municipality: ", Validators::isValidLocation);
        userInfo.setMunicipality(municipality);

        String city = promptForInput(scanner, "Enter City: ", Validators::isValidLocation);
        userInfo.setCity(city);

        int zipcode = Integer.parseInt(promptForInput(scanner, "Enter ZIP Code: ", Validators::isValidZIPCode));
        userInfo.setZIPcode(String.valueOf(zipcode));

        String nationality = promptForInput(scanner, "Enter Nationality: ", Validators::isValidName);
        userInfo.setNationality(nationality);

        String gender = promptForInput(scanner, "Enter Gender (M-Male/F-Female/N-Not to say): ", Validators::isValidGender);
        userInfo.setGender(gender);

        // Validation of role at school
        System.out.print("\nEnter role at school (Teacher/Student/Staff): ");
        String roleAtSchool = scanner.nextLine();
        userInfo.setroleAtSchool(roleAtSchool);

        if (roleAtSchool.equalsIgnoreCase("Teacher")) {
            // Redirect to teaching details class
            TeachingDetails teachingDetails = new TeachingDetails();
            teachingDetails.inputSubjectNames();
            // Generate registration code and verify
        } else if (roleAtSchool.equalsIgnoreCase("Student") || roleAtSchool.equalsIgnoreCase("Staff")) {
            System.out.println("\nSorry, only teachers are allowed in this portal.");
            System.out.println("\nProceeding to checking details...");
            // Redirect to check details class
            CheckDetails.checkDetails(userInfo);
        }

        scanner.close();
    }

}
