package com.ctu.room.reservationportal.dbservices;

import com.ctu.room.reservationportal.model.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ctu.room.reservationportal.dbservices.CreateTables.printSQLException;
import static java.sql.Date.valueOf;

public class SelectRecords {
    private static final String SELECT_USERS_SQL = "SELECT * FROM userinfo WHERE username = ?";

    public SelectRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public UserInfo selectUserRecord(String username) throws SQLException {
        UserInfo userInfo = null;
        System.out.println("Selecting user data from DB");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "admin123$");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)) {

            preparedStatement.setString(1, userInfo.getUserName());
            preparedStatement.setInt(2, userInfo.getIdNumber());
            preparedStatement.setString(3, userInfo.getFirstName());
            preparedStatement.setString(4, userInfo.getMiddleName());
            preparedStatement.setString(5, userInfo.getLastName());
            preparedStatement.setDate(6, valueOf(userInfo.getBirthdate()));
            preparedStatement.setString(7, userInfo.getEmail());
            preparedStatement.setString(8, userInfo.getPhoneNumber());
            preparedStatement.setString(9, userInfo.getStreet());
            preparedStatement.setString(10, userInfo.getBarangay());
            preparedStatement.setString(11, userInfo.getMunicipality());
            preparedStatement.setString(12, userInfo.getCity());
            preparedStatement.setInt(13, userInfo.getZIPcode());
            preparedStatement.setString(14, userInfo.getNationality());
            preparedStatement.setString(15, userInfo.getGender());
            preparedStatement.setString(16, userInfo.getRoleAtschool());


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userInfo = new UserInfo();

                userInfo.setUserName(resultSet.getString("userName"));
                userInfo.setIdNumber(resultSet.getInt("idNumber"));
                userInfo.setFirstName(resultSet.getString("firstName"));
                userInfo.setMiddleName(resultSet.getString("middleName"));
                userInfo.setLastName(resultSet.getString("lastName"));
                userInfo.setBirthDate(String.valueOf(resultSet.getDate("birthDate").toLocalDate()));
                userInfo.setEmail(resultSet.getString("email"));
                userInfo.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                userInfo.setStreet(resultSet.getString("street"));
                userInfo.setBarangay(resultSet.getString("barangay"));
                userInfo.setMunicipality(resultSet.getString("municipality"));
                userInfo.setCity(resultSet.getString("city"));
                userInfo.setZIPcode(resultSet.getInt("zipCode"));
                userInfo.setNationality(resultSet.getString("nationality"));
                userInfo.setGender(resultSet.getString("gender"));
                userInfo.setRoleAtschool(resultSet.getString("roleAtSchool"));

            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return userInfo;
    }

    public static void main(String[] args) {
        SelectRecords selectRecords = new SelectRecords();
        try {
            // Example usage: Select user record with username "johndoe123"
            UserInfo userInfo = selectRecords.selectUserRecord("johndoe123");
            if (userInfo != null) {
                System.out.println("User found: " + userInfo.getFirstName() + " " + userInfo.getLastName());
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

