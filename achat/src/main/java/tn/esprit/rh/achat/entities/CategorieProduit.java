package tn.esprit.rh.achat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class CategorieProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorieProduit;
    
    private String codeCategorie;
    private String libelleCategorie;
    
    @JsonIgnore
    @OneToMany(mappedBy = "categorieProduit")
    private Set<Produit> produits;

    // Getters
    public Long getIdCategorieProduit() { return idCategorieProduit; }
    public String getCodeCategorie() { return codeCategorie; }
    public String getLibelleCategorie() { return libelleCategorie; }
    public Set<Produit> getProduits() { return produits; }

    // Setters
    public void setIdCategorieProduit(Long idCategorieProduit) { this.idCategorieProduit = idCategorieProduit; }
    public void setCodeCategorie(String codeCategorie) { this.codeCategorie = codeCategorie; }
    public void setLibelleCategorie(String libelleCategorie) { this.libelleCategorie = libelleCategorie; }
    public void setProduits(Set<Produit> produits) { this.produits = produits; }
}
