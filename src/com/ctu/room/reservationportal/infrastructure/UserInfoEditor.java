package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;
import java.util.InputMismatchException;
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
            try {
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
                        editField("Password", this::editPassword);
                        break;
                    case 3:
                        editField("First Name", this::editFirstName);
                        break;
                    case 4:
                        editField("Middle Name", this::editMiddleName);
                        break;
                    case 5:
                        editField("Last Name", this::editLastName);
                        break;
                    case 6:
                        editField("Birthdate", this::editBirthdate);
                        break;
                    case 7:
                        editField("Email", this::editEmail);
                        break;
                    case 8:
                        editField("Phone Number", this::editPhoneNumber);
                        break;
                    case 9:
                        editField("Street", this::editStreet);
                        break;
                    case 10:
                        editField("Barangay", this::editBarangay);
                        break;
                    case 11:
                        editField("City", this::editCity);
                        break;
                    case 12:
                        editField("Municipality", this::editMunicipality);
                        break;
                    case 13:
                        editField("ZIP code", this::editZIPCode);
                        break;
                    case 14:
                        editField("Gender", this::editGender);
                        break;
                    case 15:
                        editField("Nationality", this::editNationality);
                        break;
                    case 16:
                        editField("Role at School", this::editRoleAtSchool);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        } while (!exitEditing); // Continue editing until flag is set
    }

    // Method to print edit options
    private void printEditOptions() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("|          Information to Edit         |         Option      |");
        System.out.println("-------------------------------------------------------------");
        System.out.println("| Username                             |           1         |");
        System.out.println("| Password                             |           2         |");
        System.out.println("| First Name                           |           3         |");
        System.out.println("| Middle Name                          |           4         |");
        System.out.println("| Last Name                            |           5         |");
        System.out.println("| Birthdate                            |           6         |");
        System.out.println("| Email                                |           7         |");
        System.out.println("| Phone Number                         |           8         |");
        System.out.println("| Street                               |           9         |");
        System.out.println("| Barangay                             |          10         |");
        System.out.println("| City                                 |          11         |");
        System.out.println("| Municipality                         |          12         |");
        System.out.println("| ZIP code                             |          13         |");
        System.out.println("| Gender                               |          14         |");
        System.out.println("| Nationality                          |          15         |");
        System.out.println("| Role at School                       |          16         |");
        System.out.println("| Exit Editing                         |           0         |");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Enter the corresponding option number to edit the information.");
    }

    // Method to edit a field
    private void editField(String fieldName, Runnable editMethod) {
        System.out.println("\nEditing " + fieldName);
        editMethod.run();
        System.out.println(fieldName + " updated successfully!\n");

        // Display updated details
        userInfo.displayUserInfo();

        // Ask if user wants to continue editing
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Do you want to continue editing? (1 = Yes, 0 = No): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice == 1 || choice == 0) {
                    if (choice == 0) {
                        System.out.println("Exit editing.");
                        RegistrationCode.displayRegistrationCode(); // Display registration code
                        exitEditing = true;
                    }
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 0.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    // Methods to edit individual fields
    private void editUserName() {
        userInfo.setUserName(validateInput("Username"));
    }
    private void editPassword() {
        userInfo.setPassword(validateInput("Password"));
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
        while (true) {
            try {
                System.out.print("Enter new ZIP code: ");
                int zipCode = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                userInfo.setZIPcode(zipCode);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid ZIP code.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
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
