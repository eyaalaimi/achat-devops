package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Operateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperateur;
    
    private String nom;
    private String prenom;
    private String password;
    
    @ManyToMany(mappedBy = "operateurs")
    private Set<Facture> factures;

    // Getters
    public Long getIdOperateur() { return idOperateur; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getPassword() { return password; }
    public Set<Facture> getFactures() { return factures; }

    // Setters
    public void setIdOperateur(Long idOperateur) { this.idOperateur = idOperateur; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setPassword(String password) { this.password = password; }
    public void setFactures(Set<Facture> factures) { this.factures = factures; }
}
