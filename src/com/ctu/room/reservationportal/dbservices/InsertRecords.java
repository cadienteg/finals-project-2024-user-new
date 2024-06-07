package com.ctu.room.reservationportal.dbservices;

import com.ctu.room.reservationportal.model.UserInfo;

import java.sql.*;

public class InsertRecords {

    private static final String INSERT_USERS_SQL = "INSERT INTO userinfo (" +
            "  userName, password, idNumber, firstName, middleName, lastName, birthDate, email, phoneNumber, street, barangay, municipality, city, zipcode, nationality, gender, roleAtschool) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;

    public InsertRecords() {
        this.jdbcURL = "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true";
        this.jdbcUsername = "root";
        this.jdbcPassword = "admin123$";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public void insertUserRecord(UserInfo userInfo) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, userInfo.getUserName());
            preparedStatement.setString(2, userInfo.getPassword());
            preparedStatement.setInt(3, userInfo.getIdNumber());
            preparedStatement.setString(4, capitalizeFirstLetter(userInfo.getFirstName()));
            preparedStatement.setString(5, capitalizeFirstLetter(userInfo.getMiddleName()));
            preparedStatement.setString(6, capitalizeFirstLetter(userInfo.getLastName()));
            preparedStatement.setString(7, userInfo.getBirthdate());
            preparedStatement.setString(8, userInfo.getEmail().toLowerCase());
            preparedStatement.setString(9, formatPhoneNumber(userInfo.getPhoneNumber()));
            preparedStatement.setString(10, capitalizeFirstLetter(userInfo.getStreet()));
            preparedStatement.setString(11, capitalizeFirstLetter(userInfo.getBarangay()));
            preparedStatement.setString(12, capitalizeFirstLetter(userInfo.getMunicipality()));
            preparedStatement.setString(13, capitalizeFirstLetter(userInfo.getCity()));
            preparedStatement.setInt(14, userInfo.getZIPcode());
            preparedStatement.setString(15, capitalizeFirstLetter(userInfo.getNationality()));
            preparedStatement.setString(16, formatGender(userInfo.getGender()));
            preparedStatement.setString(17, userInfo.getRoleAtschool());


            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            } else {
                System.out.println("Failed to insert user data.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting user data to DB: " + e.getMessage());
        }
    }

    private String capitalizeFirstLetter(String str) {
        // Check if the string is null or empty
        if (str == null || str.isEmpty()) {
            return str;
        }

        // Split the string into words
        String[] words = str.split("\\s+");
        StringBuilder capitalizedStr = new StringBuilder();

        // Capitalize the first letter of each word and convert the rest to lowercase
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedStr.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Trim the trailing space and return the result
        return capitalizedStr.toString().trim();
    }

    private String formatGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return gender;
        }

        gender = gender.toLowerCase();
        switch (gender) {
            case "f":
            case "female":
                return "Female";
            case "m":
            case "male":
                return "Male";
            case "n":
            case "not to say":
                return "Not to say";
            default:
                throw new IllegalArgumentException("Invalid gender value");
        }
    }

    private String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return  phoneNumber;
        }
        if (phoneNumber.startsWith("09")) {
            return "+63" + phoneNumber.substring(1);
        } else if (phoneNumber.startsWith("+639")) {
            return phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }



    public static void main(String[] args) {
        // Create a new instance of UserInfo with sample data
        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(userInfo.getUserName());
        userInfo.setPassword(userInfo.getPassword());
        userInfo.setIdNumber((userInfo.getIdNumber()));
        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setMiddleName(userInfo.getMiddleName());
        userInfo.setLastName(userInfo.getLastName());
        userInfo.setBirthDate(String.valueOf(userInfo.getBirthdate()));
        userInfo.setEmail(userInfo.getEmail());
        userInfo.setPhoneNumber(String.valueOf(userInfo.getPhoneNumber()));
        userInfo.setStreet(userInfo.getStreet());
        userInfo.setBarangay(userInfo.getBarangay());
        userInfo.setMunicipality(userInfo.getMunicipality());
        userInfo.setCity(userInfo.getCity());
        userInfo.setZIPcode(userInfo.getZIPcode());
        userInfo.setNationality(userInfo.getNationality());
        userInfo.setGender(userInfo.getGender());
        userInfo.setRoleAtschool(userInfo.getRoleAtschool());

        // Create an instance of InsertRecords
        InsertRecords insertRecords = new InsertRecords();

        // Call insertUserRecord method to insert the user info into the database
        insertRecords.insertUserRecord(userInfo);
    }

}
