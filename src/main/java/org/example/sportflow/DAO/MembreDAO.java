package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;
import org.example.sportflow.Model.Membre;
import org.example.sportflow.Model.Seance;
import org.example.sportflow.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {
    private Connection con;

    public MembreDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }

    private void checkMember(User currentUser) throws SQLException {
        if (currentUser == null || currentUser.getRole() != User.Role.membre) {
            throw new SQLException("Only members can perform this operation.");
        }
    }

    // View own profile
    public Membre getMembreById(int id, User currentUser) throws SQLException {
        if (currentUser.getId() != id) {
            throw new SQLException("You can only view your own profile.");
        }
        String sql = "SELECT u.id, u.username, u.password, u.role, m.name FROM users u JOIN members m ON u.id = m.user_id WHERE u.id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Membre membre = new Membre(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("name")
                    );
                    membre.setId(rs.getInt("id"));
                    return membre;
                }
            }
        }
        return null;
    }

    public List<Seance> afficherSeances(User currentUser) throws SQLException {
        checkMember(currentUser);
        List<Seance> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE idmembre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, currentUser.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seance seance = new Seance(
                            rs.getInt("idmembre"),
                            rs.getInt("identraineur"),
                            rs.getTimestamp("dateetheure").toLocalDateTime()
                    );
                    seance.setId(rs.getInt("id"));
                    sessions.add(seance);
                }
            }
        }
        return sessions;
    }
}