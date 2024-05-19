package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Scanner;

/**
 * Class for editing user details
 */
public class UserInfoEditor {
    private final Scanner scanner;
    private final UserInfo userInfo;
    private boolean exitEditing;

    public UserInfoEditor(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.scanner = new Scanner(System.in);
        this.exitEditing = false;
    }

    public void editUserInfo() {
        do {
            System.out.println("\nEDIT USER INFORMATION\n");

            // Print options for editing
            printEditOptions();

            System.out.print("Enter your choice (0 to exit editing): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exit editing.");
                    RegistrationCode.displayRegistrationCode(); // Display registration code
                    exitEditing = true;
                    break;
                case 1:
                    editField("Username", this::editUserName);
                    break;
                case 2:
                    editField("First Name", this::editFirstName);
                    break;
                case 3:
                    editField("Middle Name", this::editMiddleName);
                    break;
                // Add cases for other fields
                case 4:
                    editField("Last Name", this::editLastName);
                    break;
                case 5:
                    editField("Birthdate", this::editBirthdate);
                    break;
                case 6:
                    editField("Email", this::editEmail);
                    break;
                case 7:
                    editField("Phone Number", this::editPhoneNumber);
                    break;
                case 8:
                    editField("Street", this::editStreet);
                    break;
                case 9:
                    editField("Barangay", this::editBarangay);
                    break;
                case 10:
                    editField("City", this::editCity);
                    break;
                case 11:
                    editField("Municipality", this::editMunicipality);
                    break;
                case 12:
                    editField("ZIP code", this::editZIPCode);
                    break;
                case 13:
                    editField("Gender", this::editGender);
                    break;
                case 14:
                    editField("Nationality", this::editNationality);
                    break;
                case 15:
                    editField("Role at School", this::editRoleAtSchool);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!exitEditing); // Continue editing until flag is set
    }

    // Method to print edit options
    private void printEditOptions() {
        System.out.println("What information do you want to edit?");
        System.out.println("1. Username");
        System.out.println("2. First Name");
        System.out.println("3. Middle Name");
        System.out.println("4. Last Name");
        System.out.println("5. Birthdate");
        System.out.println("6. Email");
        System.out.println("7. Phone Number");
        System.out.println("8. Street");
        System.out.println("9. Barangay");
        System.out.println("10. City");
        System.out.println("11. Municipality");
        System.out.println("12. ZIP code");
        System.out.println("13. Gender");
        System.out.println("14. Nationality");
        System.out.println("15. Role at School");
        System.out.println("0. Exit Editing\n");
    }

    // Method to edit a field
    private void editField(String fieldName, Runnable editMethod) {
        System.out.println("\nEditing " + fieldName);
        editMethod.run();
        System.out.println(fieldName + " updated successfully!\n");

        // Display updated details
        userInfo.displayUserInfo();

        // Ask if user wants to continue editing
        System.out.print("Do you want to continue editing? (1 = Yes, 0 = No): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice == 0) {
            System.out.println("Exit editing.");
            RegistrationCode.displayRegistrationCode(); // Display registration code
            exitEditing = true;
        }
    }

    // Methods to edit individual fields
    private void editUserName() {
        userInfo.setUserName(validateInput("Username"));
    }

    private void editFirstName() {
        userInfo.setFirstName(validateInput("First Name"));
    }

    private void editMiddleName() {
        userInfo.setMiddleName(validateInput("Middle Name"));
    }

    private void editLastName() {
        userInfo.setLastName(validateInput("Last Name"));
    }

    private void editBirthdate() {
        userInfo.setBirthDate(validateInput("Birthdate"));
    }

    private void editEmail() {
        userInfo.setEmail(validateInput("Email"));
    }

    private void editStreet() {
        userInfo.setStreet(validateInput("Street"));
    }

    private void editBarangay() {
        userInfo.setBarangay(validateInput("Barangay"));
    }

    private void editCity() {
        userInfo.setCity(validateInput("City"));
    }

    private void editMunicipality() {
        userInfo.setMunicipality(validateInput("Municipality"));
    }

    private void editZIPCode() {
        userInfo.setZIPcode(Integer.parseInt(validateInput("ZIP code")));
    }

    private void editGender() {
        userInfo.setGender(validateInput("Gender"));
    }

    private void editNationality() {
        userInfo.setNationality(validateInput("Nationality"));
    }

    private void editPhoneNumber() {
        userInfo.setPhoneNumber(validateInput("Phone Number"));
    }

    private void editRoleAtSchool() {
        userInfo.setRoleAtschool(validateInput("Role at School"));
    }

    // Method to validate user input
    private String validateInput(String fieldName) {
        String userInput;
        boolean isValid;


        do {
            System.out.print("Enter new " + fieldName + ": ");
            userInput = scanner.nextLine();
            isValid = Validators.isValidInput(fieldName, userInput);

            if (!isValid) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!isValid);

        return userInput;
    }
}
