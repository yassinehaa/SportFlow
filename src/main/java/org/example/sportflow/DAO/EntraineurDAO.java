package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;
import org.example.sportflow.Model.Entraineur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntraineurDAO {
    private Connection con;

    public EntraineurDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }

    public void ajouterEntraineur(Entraineur en) throws SQLException {
        String sql = "INSERT INTO entraineur (id, nom, specialite) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, en.getId());
            ps.setString(2, en.getNom());
            ps.setString(3, en.getSpecialite());
            ps.executeUpdate();
        }
    }

    public void modifierEntraineur(Entraineur en) throws SQLException {
        String sql = "UPDATE entraineur SET nom = ?, specialite = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, en.getNom());
            ps.setString(2, en.getSpecialite());
            ps.setInt(3, en.getId());
            ps.executeUpdate();
        }
    }

    public List<Entraineur> AfficherEntraineur() throws SQLException {
        List<Entraineur> entraineurs = new ArrayList<>();
        String sql = "SELECT * FROM entraineur";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Entraineur entraineur = new Entraineur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("specialite"));
                entraineurs.add(entraineur);
            }
        }
        return entraineurs;
    }

    public Entraineur getEntraineurById(int id) throws SQLException {
        String sql = "SELECT * FROM entraineur WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Entraineur(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("specialite"));
                }
            }
        }
        return null;
    }

    public void supprimerEntraineur(int id) throws SQLException {
        String sql = "DELETE FROM entraineur WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}