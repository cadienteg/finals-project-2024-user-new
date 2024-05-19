package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;
import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Scanner;

/**
 * Class for checking the provided details of an admin
 */
public class CheckDetails {

    /**
     * Method responsible for checking provided details
     * @paramuserinfo which is the AdminInfo object containing the admin details.
     */
    public static void checkDetails(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);
        boolean detailsConfirmed = false;


        System.out.println("\nCHECK ADMIN DETAILS\n");
        // Display admin details for confirmation
        displayUserDetails(userInfo);

        // Prompt to choose action
        System.out.println("Are the details correct?");
        System.out.println("1: Edit Details");
        System.out.println("2: Proceed to Registration Code");
        System.out.print("Please enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                // Edit admin information
                UserInfoEditor edit = new UserInfoEditor(userInfo);
                edit.editUserInfo();
                break;
            case 2:
                // Proceed to generate registration code
                System.out.println("Details confirmed. Proceeding to Registration Code.");
                RegistrationCode generatedCode = new RegistrationCode();
                generatedCode.displayRegistrationCode();
                break;
            default:
                System.out.println("Invalid choice.");
        }

    }

    // Helper method to display admin details
    private static void displayUserDetails(UserInfo userInfo) {
        System.out.println("Name: " + userInfo.getFirstName() + " " + userInfo.getMiddleName() + " " + userInfo.getLastName());
        System.out.println("Birthdate: " + userInfo.getBirthdate());
        System.out.println("Email: " + userInfo.getEmail());
        System.out.println("Home Address: " + userInfo.getStreet() + ", " + userInfo.getBarangay() + ", "
                + userInfo.getMunicipality() + ", " + userInfo.getCity());
        System.out.println("ZIP code: " + userInfo.getZIPcode());
        System.out.println("Nationality: " + userInfo.getNationality());
        System.out.println("Gender: " + userInfo.getGender());
    }
}
