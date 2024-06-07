package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.dbservices.InsertRecords;
import com.ctu.room.reservationportal.model.UserInfo;
import com.ctu.room.reservationportal.infrastructure.UpdateUserInfo;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃     Welcome to the Room Reservation    ┃");
            System.out.println("┃                 Portal                 ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃        Options          |   Selection  ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃  1. Register            |       1      ┃");
            System.out.println("┃  2. Search and Retrieve |       2      ┃");
            System.out.println("┃  3. Update Details      |       3      ┃");
            System.out.println("┃  4. Exit                |       4      ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("┃        What would you like to do?      ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = -1;
            while (choice < 1 || choice > 4) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (choice) {
                case 1:
                    //Creating an instance of insertrecord class
                  //  InsertRecords insertDetails = new InsertRecords();
                    //Creating an instance of createuserinfo class
                    CreateUserInfo registerUserService = new CreateUserInfo();
                    //Creating an instance of userinfo class
                    UserInfo userInfo = registerUserService.registerUser();

                    break;
                case 2:
                    SearchAndRetrieve.searchAndRetrieve();
                    break;
                case 3:
                    UserInfo user = new UserInfo();

                    // Create an instance of UpdateAdminInfo class
                    UpdateUserInfo updateUserService = new UpdateUserInfo();

                    // Call the promptAndUpdate method with the admin object
                    updateUserService.promptAndUpdate();
                    break;
                        case 4:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 to 4 only.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
        }

        // Close the scanner
        scanner.close();
        System.out.println("Thank you! Have a good day");
    }
}
