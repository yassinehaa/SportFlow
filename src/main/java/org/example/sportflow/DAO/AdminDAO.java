package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;
import org.example.sportflow.Model.Entraineur;
import org.example.sportflow.Model.Membre;
import org.example.sportflow.Model.Seance;
import org.example.sportflow.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Connection con;

    public AdminDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }

    private void checkAdmin(User currentUser) throws SQLException {
        if (currentUser == null || currentUser.getRole() != User.Role.admin) {
            throw new SQLException("Only admins can perform this operation.");
        }
    }

    // User CRUD
    public void ajouterUser(User user, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }

    public void modifierUser(User user, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        }
    }

    public void supprimerUser(int id, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<User> afficherUsers(User currentUser) throws SQLException {
        checkAdmin(currentUser);
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role"))
                );
                user.setId(rs.getInt("id"));
                user.setDateCreated(rs.getTimestamp("date_created").toLocalDateTime());
                users.add(user);
            }
        }
        return users;
    }

    // Membre CRUD
    public void ajouterMembre(Membre membre, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        ajouterUser(membre, currentUser); // Add to users table first
        String sql = "INSERT INTO members (user_id, name) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, membre.getId());
            ps.setString(2, membre.getNom());
            ps.executeUpdate();
        }
    }

    public void modifierMembre(Membre membre, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        modifierUser(membre, currentUser); // Update users table
        String sql = "UPDATE members SET name = ? WHERE user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, membre.getNom());
            ps.setInt(2, membre.getId());
            ps.executeUpdate();
        }
    }

    public void supprimerMembre(int id, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sqlMember = "DELETE FROM members WHERE user_id = ?";
        try (PreparedStatement psMember = con.prepareStatement(sqlMember)) {
            psMember.setInt(1, id);
            psMember.executeUpdate();
        }
        supprimerUser(id, currentUser); // Delete from users table
    }

    public List<Membre> afficherMembres(User currentUser) throws SQLException {
        checkAdmin(currentUser);
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT u.id, u.username, u.password, u.role, m.name FROM users u JOIN members m ON u.id = m.user_id";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name")
                );
                membre.setId(rs.getInt("id"));
                membres.add(membre);
            }
        }
        return membres;
    }

    // Entraineur CRUD
    public void ajouterEntraineur(Entraineur en, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        ajouterUser(en, currentUser); // Add to users table first
        String sql = "INSERT INTO coaches (user_id, name, specialite) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, en.getId());
            ps.setString(2, en.getName());
            ps.setString(3, en.getSpecialite());
            ps.executeUpdate();
        }
    }

    public void modifierEntraineur(Entraineur en, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        modifierUser(en, currentUser); // Update users table
        String sql = "UPDATE coaches SET name = ?, specialite = ? WHERE user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, en.getName());
            ps.setString(2, en.getSpecialite());
            ps.setInt(3, en.getId());
            ps.executeUpdate();
        }
    }

    public void supprimerEntraineur(int id, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sqlCoach = "DELETE FROM coaches WHERE user_id = ?";
        try (PreparedStatement psCoach = con.prepareStatement(sqlCoach)) {
            psCoach.setInt(1, id);
            psCoach.executeUpdate();
        }
        supprimerUser(id, currentUser); // Delete from users table
    }

    public List<Entraineur> afficherEntraineurs(User currentUser) throws SQLException {
        checkAdmin(currentUser);
        List<Entraineur> entraineurs = new ArrayList<>();
        String sql = "SELECT u.id, u.username, u.password, u.role, c.name, c.specialite FROM users u JOIN coaches c ON u.id = c.user_id";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Entraineur entraineur = new Entraineur(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("specialite")
                );
                entraineur.setId(rs.getInt("id"));
                entraineurs.add(entraineur);
            }
        }
        return entraineurs;
    }

    // Seance CRUD
    public void ajouterSeance(Seance seance, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sql = "INSERT INTO seances (idmembre, identraineur, dateetheure) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, seance.getIdmembre());
            ps.setInt(2, seance.getIdentraineur());
            ps.setTimestamp(3, Timestamp.valueOf(seance.getDateetheure()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                seance.setId(rs.getInt(1));
            }
        }
    }

    public void modifierSeance(Seance seance, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sql = "UPDATE seances SET idmembre = ?, identraineur = ?, dateetheure = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, seance.getIdmembre());
            ps.setInt(2, seance.getIdentraineur());
            ps.setTimestamp(3, Timestamp.valueOf(seance.getDateetheure()));
            ps.setInt(4, seance.getId());
            ps.executeUpdate();
        }
    }

    public void supprimerSeance(int id, User currentUser) throws SQLException {
        checkAdmin(currentUser);
        String sql = "DELETE FROM seances WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Seance> afficherSeances(User currentUser) throws SQLException {
        checkAdmin(currentUser); // Admin sees all sessions
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seances";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
        return seances;
    }
}