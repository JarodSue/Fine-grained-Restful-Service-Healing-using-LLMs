package com.example.loanapproval.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @ToString
public class LoanApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable=true, updatable=true, nullable=false)
    private int id;
    @Column(name = "nom", insertable=true, updatable=true, nullable=false)
    private String nom;
    @Column(name = "somme", insertable=true, updatable=true, nullable=false)
    private int somme;

    public LoanApproval(int id, String nom, int somme) {
        this.id = id;
        this.nom = nom;
        this.somme = somme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
}
