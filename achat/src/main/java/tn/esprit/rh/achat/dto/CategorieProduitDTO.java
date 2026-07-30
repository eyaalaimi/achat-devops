package tn.esprit.rh.achat.dto;

public class CategorieProduitDTO {
    private Long idCategorieProduit;
    private String codeCategorie;
    private String libelleCategorie;
    
    // Constructors
    public CategorieProduitDTO() {}
    
    public CategorieProduitDTO(Long idCategorieProduit, String codeCategorie, String libelleCategorie) {
        this.idCategorieProduit = idCategorieProduit;
        this.codeCategorie = codeCategorie;
        this.libelleCategorie = libelleCategorie;
    }
    
    // Getters
    public Long getIdCategorieProduit() {
        return idCategorieProduit;
    }
    
    public String getCodeCategorie() {
        return codeCategorie;
    }
    
    public String getLibelleCategorie() {
        return libelleCategorie;
    }
    
    // Setters
    public void setIdCategorieProduit(Long idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }
    
    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }
    
    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }
}
