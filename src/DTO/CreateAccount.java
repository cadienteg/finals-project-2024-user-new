package DTO;

import java.util.Scanner;

public class CreateAccount {

    private final UserInfo userInfo;

    public CreateAccount(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);

        String username, password, confirmPassword;

        do {
            System.out.println("Create an Account");
            System.out.print("Username: ");
            username = scanner.nextLine();

            do {
                System.out.print("Password: ");
                password = scanner.nextLine();

                System.out.print("Confirm Password: ");
                confirmPassword = scanner.nextLine();

                if (!password.equals(confirmPassword)) {
                    System.out.println("Passwords do not match. Please try again.");
                }
            } while (!password.equals(confirmPassword)); // Loop for password confirmation

            userInfo.setUsername(username);
            userInfo.setPassword(password); // Assuming userInfo has setUsername and setPassword methods

            System.out.println("Congrats! You can now freely reserve a preferred room.");
            break; // Exit the loop after successful registration

        } while (true); // Loop continues until passwords match

        scanner.close(); // Close the Scanner to avoid resource leaks

        // Call selectPreferredRole with userInfo
        PreferRole preferredRole = new PreferRole(userInfo);
        preferredRole.selectPreferRole();
    }
}
