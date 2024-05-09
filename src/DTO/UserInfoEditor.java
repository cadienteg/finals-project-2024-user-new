package DTO;
import java.util.Scanner;

public class UserInfoEditor {

    private final Scanner scanner;
    private final UserInfo userInfo;
    private boolean exitEditing;

    public UserInfoEditor(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.scanner = new Scanner(System.in);
    }

    public void editUserInfo() {
        boolean exitEditing = false;

        do {
            System.out.println("\nEDIT USER INFORMATION\n");

            // Print options for editing
            printEditOptions();

            System.out.print("Enter your choice (0 to exit editing): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exiting editing.");
                    RegistrationCode.displayRegistrationCode();
                    exitEditing = true; // Set flag to exit editing
                    break;
                case 1:
                    editField("First Name", this::editFirstName);
                    break;
                case 2:
                    editField("Middle Name", this::editMiddleName);
                    break;
                // Add cases for other fields
                case 3:
                    editField("Last Name", this::editLastName);
                    break;
                case 4:
                    editField("Birthdate", this::editBirthdate);
                    break;
                case 5:
                    editField("Email", this::editEmail);
                    break;
                case 6:
                    editField("Phone Number", this::editPhoneNumber);
                    break;
                case 7:
                    editField("Street", this::editStreet);
                    break;
                case 8:
                    editField("Barangay", this::editBarangay);
                    break;
                case 9:
                    editField("City", this::editCity);
                    break;
                case 10:
                    editField("Municipality", this::editMunicipality);
                    break;
                case 11:
                    editField("ZIP code", this::editZIPCode);
                    break;
                case 12:
                    editField("Gender", this::editgender);
                    break;
                case 13:
                    editField("Nationality", this::editnationality);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!exitEditing); // Continue editing until flag is set
    }

    private void editFirstName() {
        System.out.print("Enter new First Name: ");
        String firstName = scanner.nextLine();
        userInfo.setFirstName(firstName);
    }

    private void editMiddleName() {
        System.out.print("Enter new Middle Name: ");
        String middleName = scanner.nextLine();
        userInfo.setMiddleName(middleName);
    }

    private void editLastName() {
        System.out.print("Enter new Last Name: ");
        String lastName = scanner.nextLine();
        userInfo.setLastName(lastName);
    }

    private void editBirthdate() {
        System.out.print("Enter new Birthdate (YYYY-MM-DD): ");
        String birthdate = scanner.nextLine();
        userInfo.setBirthDate(birthdate);
    }

    private void editEmail() {
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        userInfo.setEmail(email);
    }

    private void editPhoneNumber() {
        System.out.print("Enter new Phone Number: ");
        String phoneNumber = scanner.nextLine();
        userInfo.setPhoneNumber(phoneNumber);
    }

    private void editStreet() {
        System.out.print("Enter new Street: ");
        String street = scanner.nextLine();
        userInfo.setStreet(street);
    }

    private void editBarangay() {
        System.out.print("Enter new Barangay: ");
        String barangay = scanner.nextLine();
        userInfo.setBarangay(barangay);
    }

    private void editCity() {
        System.out.print("Enter new City: ");
        String city = scanner.nextLine();
        userInfo.setCity(city);
    }

    private void editMunicipality() {
        System.out.print("Enter new Municipality: ");
        String municipality = scanner.nextLine();
        userInfo.setMunicipality(municipality);
    }

    private void editZIPCode() {
        System.out.print("Enter new ZIP code: ");
        int zipcode = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        userInfo.setZIPcode(String.valueOf(zipcode));
    }

    private void editgender() {
        System.out.print("Enter new Gender: ");
        String gender = scanner.nextLine();
        userInfo.setGender(gender);
    }

    private void editnationality() {
        System.out.print("Enter new Nationality: ");
        String nationality = scanner.nextLine();
        userInfo.setNationality(nationality);
    }

    // Method to print edit options
    private void printEditOptions() {
        System.out.println("What information do you want to edit?");
        System.out.println("1. First Name");
        System.out.println("2. Middle Name");
        System.out.println("3. Last Name");
        System.out.println("4. Birthdate");
        System.out.println("5. Email");
        System.out.println("6. Phone Number");
        System.out.println("7. Street");
        System.out.println("8. Barangay");
        System.out.println("9. City");
        System.out.println("10. Municipality");
        System.out.println("11. ZIP code");
        System.out.println("12. Gender");
        System.out.println("13. Nationality");
        System.out.println("0. Exit Editing\n");
    }

    // Method to edit a field
    private void editField(String fieldName, Runnable editMethod) {
        System.out.println("\nEditing " + fieldName);
        editMethod.run();
        System.out.println(fieldName + " updated successfully!\n");

        // Display updated details
        userInfo.displayUserInfo();

        // Ask if user wants to continue editing
        System.out.print("Do you want to continue editing? (1 = Yes, 0 = No): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice == 0) {
            System.out.println("Exit editing.");
            RegistrationCode generatedCode = new RegistrationCode();
            RegistrationCode.displayRegistrationCode();
            exitEditing = true;

            // Call CreateAccount with userInfo (if applicable)
            CreateAccount createAccount = new CreateAccount(userInfo);
        }
    }
}
