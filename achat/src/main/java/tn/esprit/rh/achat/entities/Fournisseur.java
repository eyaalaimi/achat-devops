package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFournisseur;
    
    private String code;
    private String libelle;
    
    @OneToOne
    @JoinColumn(name = "detail_fournisseur_id")
    private DetailFournisseur detailFournisseur;
    
    @OneToMany(mappedBy = "fournisseur")
    private Set<Facture> factures;
    
    @ManyToMany
    private Set<SecteurActivite> secteurActivites;

    // Getters
    public Long getIdFournisseur() { return idFournisseur; }
    public String getCode() { return code; }
    public String getLibelle() { return libelle; }
    public DetailFournisseur getDetailFournisseur() { return detailFournisseur; }
    public Set<Facture> getFactures() { return factures; }
    public Set<SecteurActivite> getSecteurActivites() { return secteurActivites; }

    // Setters
    public void setIdFournisseur(Long idFournisseur) { this.idFournisseur = idFournisseur; }
    public void setCode(String code) { this.code = code; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setDetailFournisseur(DetailFournisseur detailFournisseur) { this.detailFournisseur = detailFournisseur; }
    public void setFactures(Set<Facture> factures) { this.factures = factures; }
    public void setSecteurActivites(Set<SecteurActivite> secteurActivites) { this.secteurActivites = secteurActivites; }
}
