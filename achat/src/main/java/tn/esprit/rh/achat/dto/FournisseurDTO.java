package tn.esprit.rh.achat.dto;

import java.util.Date;

public class FournisseurDTO {
    private Long idFournisseur;
    private String code;
    private String libelle;
    private Date dateDebutCollaboration;

    // Default constructor
    public FournisseurDTO() {}

    // Constructor with fields
    public FournisseurDTO(Long idFournisseur, String code, String libelle, Date dateDebutCollaboration) {
        this.idFournisseur = idFournisseur;
        this.code = code;
        this.libelle = libelle;
        this.dateDebutCollaboration = dateDebutCollaboration;
    }

    // Getters
    public Long getIdFournisseur() { return idFournisseur; }
    public String getCode() { return code; }
    public String getLibelle() { return libelle; }
    public Date getDateDebutCollaboration() { return dateDebutCollaboration; }

    // Setters
    public void setIdFournisseur(Long idFournisseur) { this.idFournisseur = idFournisseur; }
    public void setCode(String code) { this.code = code; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setDateDebutCollaboration(Date dateDebutCollaboration) { this.dateDebutCollaboration = dateDebutCollaboration; }
}
