package org.example.sportflow.Model;

import java.time.LocalDateTime;

public class Seance {
    private int id;
    private int idmembre;
    private int identraineur;
    private LocalDateTime dateetheure;


    public Seance() {
    }

    public Seance(int idmembre, int identraineur, LocalDateTime dateetheure) {
        this.idmembre = idmembre;
        this.identraineur = identraineur;
        this.dateetheure = dateetheure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(int idmembre) {
        this.idmembre = idmembre;
    }

    public int getIdentraineur() {
        return identraineur;
    }

    public void setIdentraineur(int identraineur) {
        this.identraineur = identraineur;
    }

    public LocalDateTime getDateetheure() {
        return dateetheure;
    }

    public void setDateetheure(LocalDateTime dateetheure) {
        this.dateetheure = dateetheure;
    }

    @Override
    public String toString() {
        return "Seance{id=" + id + ", idmembre=" + idmembre + ", identraineur=" + identraineur + ", dateetheure=" + dateetheure + "}";
    }
}
