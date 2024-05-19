package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Random;
import java.util.Scanner;

/**
 * The IDGenerator class provides methods to generate and display user IDs.
 */
public class IDGenerator {

    /**
     * Scanner object for reading user input.
     */
    private Scanner scanner;

    /**
     * UserInfo object for storing user registration information.
     */
    private UserInfo userRegistration;

    /**
     * Constructs an IDGenerator object and initializes the scanner and userRegistration fields.
     */
    public IDGenerator() {
        this.scanner = new Scanner(System.in);
        this.userRegistration = new UserInfo();
    }

    /**
     * Generates a random 6-digit user ID.
     *
     * @return The generated user ID as an int.
     */
    public static int generateUserID() {
        Random random = new Random();
        int userID;
        do {
            userID = random.nextInt(900000) + 100000; // Generate a random integer between 100000 and 999999 (inclusive)
        } while (!isValidIDNumber(userID));
        return userID;
    }

    /**
     * Checks if the provided ID number is a valid 6-digit number.
     *
     * @param idNumber The ID number to validate.
     * @return true if the ID number is valid, false otherwise.
     */
    public static boolean isValidIDNumber(int idNumber) {
        return String.valueOf(idNumber).matches("\\d{6}"); // Check if the ID number is exactly 6 digits
    }

    /**
     * Displays the generated ID and sets it in the UserInfo object.
     *
     * @param idNumber The generated ID number.
     */
    public static void displayID(int idNumber) {
        UserInfo userInfo = new UserInfo(); // Creating a new UserInfo object
        System.out.println("User ID Number: " + idNumber);
        userInfo.setIdNumber(idNumber); // Setting the ID number in the UserInfo object
    }
}
