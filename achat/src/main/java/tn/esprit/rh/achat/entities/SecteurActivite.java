package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class SecteurActivite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSecteurActivite;
    
    private String codeSecteur;
    private String libelleSecteur;
    
    @ManyToMany(mappedBy = "secteurActivites")
    private Set<Fournisseur> fournisseurs;

    // Getters
    public Long getIdSecteurActivite() { return idSecteurActivite; }
    public String getCodeSecteur() { return codeSecteur; }
    public String getLibelleSecteur() { return libelleSecteur; }
    public Set<Fournisseur> getFournisseurs() { return fournisseurs; }

    // Setters
    public void setIdSecteurActivite(Long idSecteurActivite) { this.idSecteurActivite = idSecteurActivite; }
    public void setCodeSecteur(String codeSecteur) { this.codeSecteur = codeSecteur; }
    public void setLibelleSecteur(String libelleSecteur) { this.libelleSecteur = libelleSecteur; }
    public void setFournisseurs(Set<Fournisseur> fournisseurs) { this.fournisseurs = fournisseurs; }
}
