package com.example.loanapproval.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @ToString
public class Approval {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", insertable=true, updatable=true, nullable=false)
        private int id;
        @Column(name = "nom", insertable=true, updatable=true, nullable=false)
        private String nom;
        @Column(name = "reponse", insertable=true, updatable=true, nullable=false)
        private String reponse;

        public Approval(int id, String nom, String reponse) {
                this.id = id;
                this.nom = nom;
                this.reponse = reponse;
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

        public String getReponse() {
                return reponse;
        }

        public void setReponse(String reponse) {
                this.reponse = reponse;
        }
}

