package com.example.loanapproval.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @ToString
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable=true, updatable=true, nullable=false)
    private int id;
    @Column(name = "nom", insertable=true, updatable=true, nullable=false)
    private String nom;
    @Column(name = "account", insertable=true, updatable=true, nullable=false)
    private int account;
    @Column(name = "prenom", insertable=true, updatable=true, nullable=false)
    private String prenom;
    @Column(name = "risk", insertable=true, updatable=true, nullable=false)
    private String risk;

    public Compte(int id, String nom, int account, String prenom, String risk) {
        this.id = id;
        this.nom = nom;
        this.account = account;
        this.prenom = prenom;
        this.risk = risk;
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

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
