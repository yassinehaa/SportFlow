package org.example.sportflow.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {
    public static Connection getconnectiondb() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportflow", "root", "");
        Statement stm = connection.createStatement();

        try {
            // Users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) NOT NULL UNIQUE, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "role ENUM('admin', 'member', 'coach') NOT NULL" +
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
                    "name VARCHAR(100) NOT NULL, " +
                    "date DATETIME NOT NULL, " +
                    "coach_id INT NOT NULL, " +
                    "description TEXT, " +
                    "FOREIGN KEY (coach_id) REFERENCES coaches(id)" +
                    ")";
            stm.executeUpdate(createSessionsTable);

            // Members table
            String createMembersTable = "CREATE TABLE IF NOT EXISTS members (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id)" +
                    ")";
            stm.executeUpdate(createMembersTable);

            // Members-Sessions junction table
            String createMembersSessionsTable = "CREATE TABLE IF NOT EXISTS members_sessions (" +
                    "member_id INT NOT NULL, " +
                    "session_id INT NOT NULL, " +
                    "PRIMARY KEY (member_id, session_id), " +
                    "FOREIGN KEY (member_id) REFERENCES members(id), " +
                    "FOREIGN KEY (session_id) REFERENCES sessions(id)" +
                    ")";
            stm.executeUpdate(createMembersSessionsTable);
        } finally {
            stm.close(); // Close the Statement to prevent resource leaks
        }

        return connection;
    }
}