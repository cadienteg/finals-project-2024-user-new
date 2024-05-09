package DTO;

import java.util.Scanner;

public class CheckDetails {

    /*
     * @param userInfo The UserInfo object containing the user details.
     */
    public static void checkDetails(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("** Checking Details **");
        System.out.println("Name: " + userInfo.getFirstName() + " " + userInfo.getMiddleName() + " " + userInfo.getLastName());
        System.out.println("Birthdate: " + userInfo.getBirthdate());
        System.out.println("Email: " + userInfo.getEmail());
        System.out.println("HOME ADDRESS DETAILS");
        System.out.println("Home Address: " + userInfo.getStreet() + ", " + userInfo.getBarangay() + ", "
                + userInfo.getMunicipality() + ", " + userInfo.getCity());
        System.out.println("ZIP code: " + userInfo.getZIPcode());
        System.out.println("");
        System.out.println("USER OTHER DETAILS");
        System.out.println("Nationality: " + userInfo.getNationality());
        System.out.println("Gender: " + userInfo.getGender());

        // Prompt user for confirmation after checking
        System.out.println("Are the details correct?");
        System.out.println("1: Edit Details");
        System.out.println("2: Skip Checking");
        System.out.println("Please enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice == 2) {
            System.out.println("Details confirmed. Proceeding to Registration Code .");
            RegistrationCode registrationCode = new RegistrationCode();
            RegistrationCode.displayRegistrationCode();
        } else if (choice == 1) {
            // Call UserInfoEditor to edit user information (optional)
            UserInfoEditor userInfoEditor = new UserInfoEditor(userInfo);  // Pass userInfo object (optional)
            userInfoEditor.editUserInfo();
        }
    }}