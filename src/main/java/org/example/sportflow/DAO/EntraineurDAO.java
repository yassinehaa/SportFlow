package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;
import org.example.sportflow.Model.Entraineur;
import org.example.sportflow.Model.Seance;
import org.example.sportflow.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntraineurDAO {
    private Connection con;

    public EntraineurDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }

    private void checkEntraineur(User currentUser) throws SQLException {
        if (currentUser == null || currentUser.getRole() != User.Role.entraineur) {
            throw new SQLException("Only coaches can perform this operation.");
        }
    }

    // View own profile
    public Entraineur getEntraineurById(int id, User currentUser) throws SQLException {
        if (currentUser.getId() != id) {
            throw new SQLException("You can only view your own profile.");
        }
        String sql = "SELECT u.id, u.username, u.password, u.role, c.name, c.specialite FROM users u JOIN coaches c ON u.id = c.user_id WHERE u.id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Entraineur entraineur = new Entraineur(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("specialite")
                    );
                    entraineur.setId(rs.getInt("id"));
                    return entraineur;
                }
            }
        }
        return null;
    }

    // View own sessions
    public List<Seance> afficherSeances(User currentUser) throws SQLException {
        checkEntraineur(currentUser);
        List<Seance> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE identraineur = ?";
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