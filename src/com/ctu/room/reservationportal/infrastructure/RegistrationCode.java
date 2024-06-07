package com.ctu.room.reservationportal.infrastructure;

import java.util.Random;
import java.util.Scanner;

public class RegistrationCode {
    private static final int CODE_LENGTH = 6;

    /**
     * Generates a random 6-digit registration code
     *
     * @return a random 6-digit registration code
     */
    public static String generateCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        // Generates each digit of the registration code by appending a random digit (0-9) to the codeBuilder StringBuilder
        // The loop runs for CODE_LENGTH times.
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }

        // Returns the generated registration code as a string
        return codeBuilder.toString();
    }

    /**
     * The main method generates a registration code and prints it to the console.
     */
    public static void displayRegistrationCode() {
        Scanner scanner = new Scanner(System.in);

        boolean codeMatched = false;

        while (!codeMatched) {
            String code = generateCode();
            printRegistrationCode(code);

            System.out.print("Enter Registration Code: ");
            String inputCode = scanner.nextLine();

            if (inputCode.equals(code)) {
                codeMatched = true;
                System.out.println("\n***************************************");
                System.out.println("*                                     *");
                System.out.println("*       Registration code matched!    *");
                System.out.println("*    User Registration Successful!    *");
                System.out.println("*                                     *");
                System.out.println("***************************************\n");
            } else {
                System.out.println("\n---------------------------------------");
                System.out.println("|  Registration code does not match.  |");
                System.out.println("|  Please try again with the new code.|");
                System.out.println("---------------------------------------\n");
            }
        }
    }

    /**
     * Prints the registration code with a stylish design
     *
     * @param code The registration code to be printed
     */
    private static void printRegistrationCode(String code) {
        String formattedCode = String.format("%-10s", code); // Ensures the code is centered
        System.out.println("\n***************************************");
        System.out.println("*                                     *");
        System.out.println("*         REGISTRATION CODE           *");
        System.out.println("*                                     *");
        System.out.println("***************************************");
        System.out.println("*                                     *");
        System.out.println("*                " + formattedCode + "           *");
        System.out.println("*                                     *");
        System.out.println("***************************************\n");
    }

    public static void main(String[] args) {
        displayRegistrationCode();
    }
}
