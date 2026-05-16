package tn.esprit.rh.achat.dto;

public class FournisseurDTO {
    private Long idFournisseur;
    private String code;
    private String libelle;

    public FournisseurDTO() {}

    public FournisseurDTO(Long idFournisseur, String code, String libelle) {
        this.idFournisseur = idFournisseur;
        this.code = code;
        this.libelle = libelle;
    }

    // Getters
    public Long getIdFournisseur() { return idFournisseur; }
    public String getCode() { return code; }
    public String getLibelle() { return libelle; }

    // Setters
    public void setIdFournisseur(Long idFournisseur) { this.idFournisseur = idFournisseur; }
    public void setCode(String code) { this.code = code; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}
