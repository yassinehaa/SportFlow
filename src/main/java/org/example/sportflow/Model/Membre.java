package org.example.sportflow.Model;

import java.util.Date;

public class Membre extends User {
    private String nom;
    private Date dateNaissance;
    private String sportpratique;

    public Membre() {
        super();
        this.role = Role.membre;
    }

    public Membre(String nom, Date dateNaissance, String sportpratique) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.sportpratique = sportpratique;
    }

    public Membre(String username, String password, Role role, String nom, Date dateNaissance, String sportpratique) {
        super(username, password, role);
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.sportpratique = sportpratique;
    }

    public Membre(String username, java.sql.Date dateNaissance, String password, String name) {
    }

    public Membre(String username, String password, String name) {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSportpratique() {
        return sportpratique;
    }

    public void setSportpratique(String sportpratique) {
        this.sportpratique = sportpratique;
    }
}
