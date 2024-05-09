package DTO;


import java.util.Scanner;

public class TeachingDetails {
    Scanner scanner = new Scanner(System.in);
    private int numberOfSubjects;
    private String[] subjectNames;

    // Constructor
    public TeachingDetails() {
        this.numberOfSubjects = numberOfSubjects;
        this.subjectNames = new String[numberOfSubjects];
    }


    // Method to input subject names
    public void inputSubjectNames() {
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.println("Enter subject name " + (i + 1) + ":");
            subjectNames[i] = scanner.nextLine();

            System.out.println("\nTeaching details:");
            System.out.println("Number of subjects: " + numberOfSubjects);
            System.out.println("Subject names:");
            for (String subject : subjectNames) {
                System.out.println("Subject Names" + subject);
            }

            RegistrationCode.displayRegistrationCode();
        }
    }}
