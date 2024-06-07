//THE PACKAGE OF THE WHOLE CLASS
package com.ctu.room.reservationportal.infrastructure;
import com.ctu.room.reservationportal.dbservices.InsertRecords;
import com.ctu.room.reservationportal.model.UserInfo;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

//import static com.ctu.room.reservationportal.infrastructure.CreateUserInfo.promptForInput;
//MAIN CLASS CREATING USER INGO
public class CreateUserInfo {
    /**
     * Prompts the user for input and validates it using a provided validator.
     *
     * @param scanner       Scanner object for input
     * @param promptMessage Message to display when prompting for input
     * @param validator     Validator for input validation
     * @return Validated user input
     */
    public static String promptForInput(Scanner scanner, String promptMessage, CreateUserInfo.Validator validator) {
        String input;
        boolean isValid;
        do {
            System.out.print(promptMessage);
            input = scanner.nextLine();
            isValid = validator.isValid(input);
            if (!isValid) {
                System.out.println("Oops! Looks like there's a typo in there! " +
                        "Please input details correctly.");
            }
        } while (!isValid);
        return input;
    }
    //Interface defines a set of methods that implementing classes must provide.
    //Interface for input validation.
    //It declares a single method isValid(String input),
    // which takes a string input and returns a boolean value indicating whether the input is valid according to the
    // specific validation rule implemented by the validator.
    interface Validator {
        boolean isValid(String input);
    }

    /**
     * Scanner object for reading user input.
     */
   private Scanner scanner;
    /**
     * UserInfo object for storing user registration information.
     */
   private UserInfo userRegistration;


    /**
     * Constructs a CreateUserInfo object and initializes the scanner for user input
     * and the userRegistration object for storing user information.
     */
    public CreateUserInfo() {
        this.scanner = new Scanner(System.in);
        userRegistration = new UserInfo();
    }

    //Main class for user registration
    /**
     * Prompts the user for input and validates it using a provided validator.
     *
     * @paramscanner       Scanner object for input
     * @parampromptMessage Message to display when prompting for input
     * @paramvalidator     Validator for input validation
     * @return Validated user input
     */
    public UserInfo registerUser() {
        int userChoice = 0;
        do {
            try {
                System.out.println("+---------------------------------+");
                System.out.println("| Are you just a user?            |");
                System.out.println("+---------------------------------+");
                System.out.println("| 1. Yes                          |");
                System.out.println("| 2. No                           |");
                System.out.println("+---------------------------------+");
                System.out.print("Enter your choice (1 or 2): ");
                userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (userChoice != 1 && userChoice != 2) {
                    System.out.println("Invalid choice. Please enter 1 for Yes or 2 for No.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        } while (userChoice != 1 && userChoice != 2);
        // Proceed only if the user chooses to register
        // If the user is just a user, proceed with the registration process
        if (userChoice == 1) {
            System.out.println("Proceeding to user registration process...");
        } else {
            // If the user is not just a user, exit the registration process
            System.out.println("This registration is only for user registration.");
            System.exit(0);
        }

        // Print welcome message and instructions for user registration
        System.out.println("**********************************************************************");
        System.out.println("*                     WELCOME TO USER REGISTRATION!                  *");
        System.out.println("**********************************************************************");
        System.out.println("* Each field has specific guidelines. To ensure a smooth process:    *");
        System.out.println("* - Review the information and enter details that match guidelines.  *");
        System.out.println("* - Names and Address (Except Street and Barangay) should contain    *");
        System.out.println("*    letters only, with both uppercase and lowercase accepted.       *");
        System.out.println("* - Phone Number, ZIP Code, Birthdate, and Gender must be entered    *");
        System.out.println("*     correctly in the specified format.                             *");
        System.out.println("* - Phone Number should start with '+639' or '09' followed by 9      *");
        System.out.println("*     digits.                                                        *");
        System.out.println("* - ZIP Code should contain four digits.                             *");
        System.out.println("* - Birthdate should be entered in the format 'YYYY-MM-DD'.          *");
        System.out.println("**********************************************************************");



        // Prompt for and validate user details
        // Each detail is prompted for using the promptForInput method with appropriate validators
        //In the promptForInput method, Validators::isValidName is passed as an argument to indicate that the input provided by the user should be validated using the isValidName method defined in the Validators class.
        // Prompt for username and validate
        String username = promptForInput(scanner, "Enter Username: ", Validators::isValidUsername);
        userRegistration.setUserName(username);

        String password = promptForInput(scanner, "Enter Password: ", Validators::isValidPassword);
        userRegistration.setPassword(password);

        int idNumber = IDGenerator.generateUserID();
        IDGenerator.displayID(idNumber);

        // Prompt for ID number and validate
        int enteredIdNumber;
        do {
            enteredIdNumber = Integer.parseInt(promptForInput(scanner, "Enter ID Number: ", Validators::isValidIDNumber));
            if (enteredIdNumber != idNumber) {
                System.out.println("Invalid ID Number. Please try again.");
            }
        } while (enteredIdNumber != idNumber);
        userRegistration.setIdNumber(idNumber);

        String firstName = promptForInput(scanner, "Enter First name: ", Validators::isValidName);
        userRegistration.setFirstName(firstName);

        String middleName = promptForInput(scanner, "Enter Middle name: ", Validators::isValidName);
        userRegistration.setMiddleName(middleName);

        String lastName = promptForInput(scanner, "Enter Last name: ", Validators::isValidName);
        userRegistration.setLastName(lastName);

        String birthdate = promptForInput(scanner, "Enter Birthdate (YYYY-MM-DD): ", Validators::isValidDate);
        userRegistration.setBirthDate(birthdate);

        String email = promptForInput(scanner, "Enter Email: ", Validators::isValidEmail);
        userRegistration.setEmail(email);

        String phoneNumber = promptForInput(scanner, "Enter Phone Number: ", Validators::isValidPhoneNumber);
        userRegistration.setPhoneNumber(phoneNumber);

        String street = promptForInput(scanner, "Enter Street: ", Validators::isValidStreet);
        userRegistration.setStreet(street);

        String barangay = promptForInput(scanner, "Enter Barangay: ", Validators::isValidLocation);
        userRegistration.setBarangay(barangay);

        String municipality = promptForInput(scanner, "Enter Municipality: ", Validators::isValidLocation);
        userRegistration.setMunicipality(municipality);

        String city = promptForInput(scanner, "Enter City: ", Validators::isValidLocation);
        userRegistration.setCity(city);

        int zipcode = Integer.parseInt(promptForInput(scanner, "Enter ZIP Code: ", Validators::isValidZIPCode));
        userRegistration.setZIPcode(zipcode);

        String nationality = promptForInput(scanner, "Enter Nationality: ", Validators::isValidNationality);
        userRegistration.setNationality(nationality);

        String gender = promptForInput(scanner, "Enter Gender (M-Male|F-Female|N-Not to say): ", Validators::isValidGender);
        userRegistration.setGender(gender);


        // Validation of role at school
        // Check the role at school and validate it
        int roleChoice;
        boolean validRoleChoice = false;
        do {
            try {
                System.out.print("\nEnter role at school (1 for Teacher, 2 for Student, 3 for Staff): ");
                roleChoice = Integer.parseInt(scanner.nextLine());
                switch (roleChoice) {
                    case 1:
                        userRegistration.setRoleAtschool("Teacher");
                        validRoleChoice = true;
                        break;
                    case 2:
                        userRegistration.setRoleAtschool("Student");
                        validRoleChoice = true;
                        break;
                    case 3:
                        userRegistration.setRoleAtschool("Staff");
                        validRoleChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 for Teacher, 2 for Student, or 3 for Staff.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validRoleChoice);



        // Call Check user detail
        CheckDetails.checkDetails(userRegistration);

        // End the program after all actions are completed
        System.out.println("You have successfully registered.");

        // Instantiate the InsertRecords class
        InsertRecords insertRecords = new InsertRecords();

        // Call the insertUserRecord method of InsertRecords and pass the adminRegistration object
        insertRecords.insertUserRecord(userRegistration);

        // Ask if the user wants to register another user
        boolean isValidChoice = false;
        while (!isValidChoice) {
            System.out.print("Do you want to register another  user account (1 for Yes, 2 for No)? ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) {
                    isValidChoice = true;
                    registerUser(); // Re-register
                } else if (choice == 2) {
                    isValidChoice = true;
                    System.out.println("Exiting the registration process.");
                    scanner.close(); // Close the scanner
                    System.exit(0); // Exit the program
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Yes or 2 for No.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer (1 for Yes, 2 for No).");
            }
        }


        return this.userRegistration;
    }
}



