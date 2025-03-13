package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;
import org.example.sportflow.Model.Seance;
import org.example.sportflow.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    private Connection con;

    public SeanceDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }

    public List<Seance> afficherSeances(User currentUser) throws SQLException {
        List<Seance> seances = new ArrayList<>();
        String sql;
        if (currentUser.getRole() == User.Role.admin) {
            sql = "SELECT * FROM seances";
        } else if (currentUser.getRole() == User.Role.membre) {
            sql = "SELECT * FROM seances WHERE idmembre = ?";
        } else if (currentUser.getRole() == User.Role.entraineur) {
            sql = "SELECT * FROM seances WHERE identraineur = ?";
        } else {
            throw new SQLException("Unauthorized access.");
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            if (currentUser.getRole() != User.Role.admin) {
                ps.setInt(1, currentUser.getId());
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seance seance = new Seance(
                            rs.getInt("idmembre"),
                            rs.getInt("identraineur"),
                            rs.getTimestamp("dateetheure").toLocalDateTime()
                    );
                    seance.setId(rs.getInt("id"));
                    seances.add(seance);
                }
            }
        }
        return seances;
    }

    public Seance getSeanceById(int id, User currentUser) throws SQLException {
        String sql;
        if (currentUser.getRole() == User.Role.admin) {
            sql = "SELECT * FROM seances WHERE id = ?";
        } else if (currentUser.getRole() == User.Role.membre) {
            sql = "SELECT * FROM seances WHERE id = ? AND idmembre = ?";
        } else if (currentUser.getRole() == User.Role.entraineur) {
            sql = "SELECT * FROM seances WHERE id = ? AND identraineur = ?";
        } else {
            throw new SQLException("Unauthorized access.");
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            if (currentUser.getRole() != User.Role.admin) {
                ps.setInt(2, currentUser.getId());
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Seance seance = new Seance(
                            rs.getInt("idmembre"),
                            rs.getInt("identraineur"),
                            rs.getTimestamp("dateetheure").toLocalDateTime()
                    );
                    seance.setId(rs.getInt("id"));
                    return seance;
                }
            }
        }
        return null;
    }
}