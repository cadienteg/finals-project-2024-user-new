package DTO;

import java.util.Scanner;

public class PreferRole {
    public PreferRole(UserInfo userInfo) {
    }

    public static void selectPreferRole(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);
        int preferredRole;

        do {
            System.out.println("Enter Preferred Role (1 = User, 2 = Admin):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextInt();
            }
            preferredRole = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } while (preferredRole != 1 && preferredRole != 2);

        if (preferredRole == 1) {
            // User registration
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            String newEmail;
            do {
                System.out.println("Enter your email: ");
                newEmail = scanner.nextLine();
            } while (!Validators.isValidEmail(newEmail)); // Loop until valid email is entered

            System.out.println("Enter admin user ID: ");
            String userId = scanner.nextLine();

            // ...
        } else if (preferredRole == 2) {
            // Admin registration
            String adminId;
            do {
                System.out.println("Enter admin user ID (six digits): ");
                adminId = scanner.nextLine();
            } while (!Validators.isValidAdminID(adminId)); // Loop until valid admin ID is entered
            userInfo.setAdminID(adminId);

            // ...
        } else {
            System.out.println("Invalid role selected.");
        }
    }

    public static void displayPreferRole() {
    }

    public void selectPreferRole() {
    }
}
