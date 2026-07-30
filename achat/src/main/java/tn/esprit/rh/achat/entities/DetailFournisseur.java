package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class DetailFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailFournisseur;
    
    private Date dateDebutCollaboration;
    private String adresse;
    private String email;
    
    @OneToOne(mappedBy = "detailFournisseur")
    private Fournisseur fournisseur;

    // Getters
    public Long getIdDetailFournisseur() {
        return idDetailFournisseur;
    }

    public Date getDateDebutCollaboration() {
        return dateDebutCollaboration;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    // Setters
    public void setIdDetailFournisseur(Long idDetailFournisseur) {
        this.idDetailFournisseur = idDetailFournisseur;
    }

    public void setDateDebutCollaboration(Date dateDebutCollaboration) {
        this.dateDebutCollaboration = dateDebutCollaboration;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
