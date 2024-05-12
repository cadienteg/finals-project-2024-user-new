package com.ctu.reservationportal.infrastructure;

import com.ctu.reservationportal.model.UserInfo;

import java.util.Scanner;

public class PreferRole {

    // Method to select preferred role and register user or admin
    public static void selectPreferRole(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);
        int preferredRole;

        do {
            System.out.println("Enter Preferred Role (1 = User, 2 = Admin):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid 1 for User and 2 for Admin.");
                scanner.next(); // Consume the invalid input
            }
            preferredRole = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } while (preferredRole != 1 && preferredRole != 2);
        // Loop until valid email is entered

        if (preferredRole == 1) {
            // Generate user ID number
            String idNumber = IDGenerator.generateUserID();
            System.out.println("User ID number: " + idNumber);
            //user set info
            userInfo.setIDNumber(idNumber);

        } else if (preferredRole == 2) {
            // Admin registration

            // Generate admin ID number
            String idNumber = IDGenerator.generateAdminID();
            System.out.println("Admin ID number: " + idNumber);

            // Set admin info
            userInfo.setIDNumber(idNumber);

        } else {
            System.out.println("Invalid role selected.");
        }

        // Exit the program
        System.out.println("You hav succesfully registered in this portal.");
        System.exit(0);
    }
}
