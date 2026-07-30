package tn.esprit.rh.achat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;
    
    private Float montantRemise;
    private Float montantFacture;
    private Date dateFacture;
    private Boolean active;
    
    @JsonIgnore
    @OneToMany(mappedBy = "facture")
    private Set<DetailFacture> detailFactures;
    
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    
    @ManyToMany
    @JsonIgnore
    private Set<Operateur> operateurs;

    // Getters
    public Long getIdFacture() { return idFacture; }
    public Float getMontantRemise() { return montantRemise; }
    public Float getMontantFacture() { return montantFacture; }
    public Date getDateFacture() { return dateFacture; }
    public Boolean getActive() { return active; }
    public Set<DetailFacture> getDetailFactures() { return detailFactures; }
    public Fournisseur getFournisseur() { return fournisseur; }
    public Set<Operateur> getOperateurs() { return operateurs; }

    // Setters
    public void setIdFacture(Long idFacture) { this.idFacture = idFacture; }
    public void setMontantRemise(Float montantRemise) { this.montantRemise = montantRemise; }
    public void setMontantFacture(Float montantFacture) { this.montantFacture = montantFacture; }
    public void setDateFacture(Date dateFacture) { this.dateFacture = dateFacture; }
    public void setActive(Boolean active) { this.active = active; }
    public void setDetailFactures(Set<DetailFacture> detailFactures) { this.detailFactures = detailFactures; }
    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }
    public void setOperateurs(Set<Operateur> operateurs) { this.operateurs = operateurs; }
}
