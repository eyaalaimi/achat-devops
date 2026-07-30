package tn.esprit.rh.achat.dto;

public class ProduitDTO {
    private Long idProduit;
    private String code;
    private String libelle;
    private Float prixUnitaire;
    private Integer quantite;

    public ProduitDTO() {}

    public ProduitDTO(Long idProduit, String code, String libelle, Float prixUnitaire, Integer quantite) {
        this.idProduit = idProduit;
        this.code = code;
        this.libelle = libelle;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    // Getters
    public Long getIdProduit() { return idProduit; }
    public String getCode() { return code; }
    public String getLibelle() { return libelle; }
    public Float getPrixUnitaire() { return prixUnitaire; }
    public Integer getQuantite() { return quantite; }

    // Setters
    public void setIdProduit(Long idProduit) { this.idProduit = idProduit; }
    public void setCode(String code) { this.code = code; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setPrixUnitaire(Float prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
}
