package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;
    
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;
    
    @OneToMany(mappedBy = "stock")
    private Set<Produit> produits;

    // Getters
    public Long getIdStock() { return idStock; }
    public String getLibelleStock() { return libelleStock; }
    public Integer getQte() { return qte; }
    public Integer getQteMin() { return qteMin; }
    public Set<Produit> getProduits() { return produits; }

    // Setters
    public void setIdStock(Long idStock) { this.idStock = idStock; }
    public void setLibelleStock(String libelleStock) { this.libelleStock = libelleStock; }
    public void setQte(Integer qte) { this.qte = qte; }
    public void setQteMin(Integer qteMin) { this.qteMin = qteMin; }
    public void setProduits(Set<Produit> produits) { this.produits = produits; }
}
