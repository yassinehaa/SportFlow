package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;
import org.example.sportflow.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection con;

    public UserDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }

    // Login functionality (available to all)
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT id, username, password, role FROM users WHERE username = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String roleStr = rs.getString("role");
                    User.Role role = (roleStr != null) ? User.Role.valueOf(roleStr.replace("coach", "entraineur").replace("member", "membre")) : null;
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            role
                    );
                    return user;
                }
            }
        }
        return null;
    }

    // View own profile (available to all)
    public User getUserById(int id, User currentUser) throws SQLException {
        if (currentUser.getId() != id) {
            throw new SQLException("You can only view your own profile.");
        }
        String sql = "SELECT Damian, username, password, role FROM users WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            User.Role.valueOf(rs.getString("role"))
                    );
                    return user;
                }
            }
        }
        return null;
    }
}