package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;
import com.ctu.room.reservationportal.model.UserInfo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for checking the provided details of an admin
 */
public class CheckDetails {

    /**
     * Method responsible for checking provided details
     *
     * @paramuserinfo which is the userinfo object containing the admin details.
     */
    public static void checkDetails(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;


        System.out.println("\nCHECK USER DETAILS\n");
        // Display user details for confirmation
        displayUserDetails(userInfo);

        // Prompt to choose action
        while (!validInput) {
            try {
                // Prompt to choose action
                System.out.println("Are the details correct?");
                System.out.println("1: Edit Details");
                System.out.println("2: Proceed to Registration Code");
                System.out.print("Please enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Edit user information
                        UserInfoEditor edit = new UserInfoEditor(userInfo);
                        edit.editUserInfo();
                        validInput = true;
                        break;
                    case 2:
                        // Proceed to generate registration code
                        System.out.println("Details confirmed. Proceeding to Registration Code.");
                        RegistrationCode generatedCode = new RegistrationCode();
                        generatedCode.displayRegistrationCode();
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    // Helper method to display user details
    private static void displayUserDetails(UserInfo userInfo) {
        System.out.println("------------------------------------------------------------");
        System.out.println("|                User Information                           |");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Username:          | " + userInfo.getUserName() );
        System.out.println(" Name:              | " + userInfo.getFirstName() + " " + userInfo.getMiddleName() + " " + userInfo.getLastName() );
        System.out.println(" Birthdate:         | " + userInfo.getBirthdate() );
        System.out.println(" Email:             | " + userInfo.getEmail() );
        System.out.println(" Phone Number:      | " + userInfo.getPhoneNumber());
        System.out.println(" Home Address:      | " + userInfo.getStreet() + ", " + userInfo.getBarangay() + ", " + userInfo.getMunicipality() + ", " + userInfo.getCity());
        System.out.println(" ZIP code:          | " + userInfo.getZIPcode() );
        System.out.println(" Nationality:       | " + userInfo.getNationality());
        System.out.println(" Gender:            | " + userInfo.getGender() );
        System.out.println(" Role At School:    | " + userInfo.getRoleAtschool() );
        System.out.println("------------------------------------------------------------");

    }
}