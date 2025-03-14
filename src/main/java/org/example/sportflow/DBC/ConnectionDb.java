package org.example.sportflow.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {
    public static Connection getconnectiondb() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportflow", "root", "");

        try (Statement stm = connection.createStatement()) {
            // Users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) NOT NULL UNIQUE, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "role ENUM('admin', 'membre', 'entraineur') NOT NULL" +
                    ")";
            stm.executeUpdate(createUsersTable);

            // Coaches table
            String createCoachesTable = "CREATE TABLE IF NOT EXISTS coaches (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "specialite VARCHAR(100), " +
                    "FOREIGN KEY (user_id) REFERENCES users(id)" +
                    ")";
            stm.executeUpdate(createCoachesTable);

            // Sessions table
            String createSessionsTable = "CREATE TABLE IF NOT EXISTS sessions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "idmembre INT NOT NULL, " +
                    "identraineur INT NOT NULL, " +
                    "dateetheure DATETIME NOT NULL, " +
                    "FOREIGN KEY (idmembre) REFERENCES members(id), " +
                    "FOREIGN KEY (identraineur) REFERENCES coaches(id)" +
                    ")";
            stm.executeUpdate(createSessionsTable);
            stm.executeUpdate(createSessionsTable);

            // Members table
            String createMembersTable = "CREATE TABLE IF NOT EXISTS members (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id)" +
                    ")";
            stm.executeUpdate(createMembersTable);
        }

        return connection;
    }
}