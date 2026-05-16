package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    
    private String code;
    private String libelle;
    private Float prixUnitaire;
    private Integer quantite;
    
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    
    @ManyToOne
    @JoinColumn(name = "categorie_produit_id")
    private CategorieProduit categorieProduit;

    // Getters
    public Long getIdProduit() { return idProduit; }
    public String getCode() { return code; }
    public String getLibelle() { return libelle; }
    public Float getPrixUnitaire() { return prixUnitaire; }
    public Integer getQuantite() { return quantite; }
    public Stock getStock() { return stock; }
    public CategorieProduit getCategorieProduit() { return categorieProduit; }

    // Setters
    public void setIdProduit(Long idProduit) { this.idProduit = idProduit; }
    public void setCode(String code) { this.code = code; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setPrixUnitaire(Float prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
    public void setStock(Stock stock) { this.stock = stock; }
    public void setCategorieProduit(CategorieProduit categorieProduit) { this.categorieProduit = categorieProduit; }
}
