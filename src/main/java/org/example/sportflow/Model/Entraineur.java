package org.example.sportflow.Model;

public class Entraineur extends User {
    private String name;
    private String specialite;


    public Entraineur() {
        super();
        this.role = Role.entraineur;
    }

    public Entraineur(String username, String password, String name, String specialite) {
        super(username, password, Role.entraineur);
        this.name = name;
        this.specialite = specialite;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Coach{id=" + id + ", username='" + username + "', name='" + name + "', specialite='" + specialite + "'}";
    }
}