package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.dbservices.InsertRecords;
import com.ctu.room.reservationportal.model.UserInfo;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Room Reservation Portal!");
            System.out.println("What would you like to do?");
            System.out.println("1. Register");
            System.out.println("2. Search and Retrieve");
            System.out.println("3. Update Details");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    //Creating an instance of insertrecord class
                    InsertRecords insertDetails = new InsertRecords();
                    //Creating an instance of createuserinfo class
                    CreateUserInfo registerUserService = new CreateUserInfo();
                    //Creating an instance of userinfo class
                    UserInfo userInfo = registerUserService.registerUser();
                    //calling inseruserrecord methods
                    insertDetails.insertUserRecord(userInfo);
                    break;
                case 2:
                    SearchAndRetrieve.searchAndRetrieve();
                    break;
                case 3:
                    System.out.println("not yet working");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

