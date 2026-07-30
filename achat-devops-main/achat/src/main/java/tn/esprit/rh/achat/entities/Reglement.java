package tn.esprit.rh.achat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reglement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReglement;
    
    private Float montantPaye;
    private Float montantRestant;
    private Boolean payee;
    private Date dateReglement;

    @ManyToOne
    private Facture facture;

    // Getters
    public Long getIdReglement() { return idReglement; }
    public Float getMontantPaye() { return montantPaye; }
    public Float getMontantRestant() { return montantRestant; }
    public Boolean getPayee() { return payee; }
    public Date getDateReglement() { return dateReglement; }
    public Facture getFacture() { return facture; }

    // Setters
    public void setIdReglement(Long idReglement) { this.idReglement = idReglement; }
    public void setMontantPaye(Float montantPaye) { this.montantPaye = montantPaye; }
    public void setMontantRestant(Float montantRestant) { this.montantRestant = montantRestant; }
    public void setPayee(Boolean payee) { this.payee = payee; }
    public void setDateReglement(Date dateReglement) { this.dateReglement = dateReglement; }
    public void setFacture(Facture facture) { this.facture = facture; }
}
