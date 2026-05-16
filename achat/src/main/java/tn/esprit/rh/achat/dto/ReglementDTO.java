package tn.esprit.rh.achat.dto;

import java.util.Date;

public class ReglementDTO {
    private Long idReglement;
    private Float montantPaye;
    private Float montantRestant;
    private Boolean payee;
    private Date dateReglement;

    public ReglementDTO() {}

    public ReglementDTO(Long idReglement, Float montantPaye, Float montantRestant, Boolean payee, Date dateReglement) {
        this.idReglement = idReglement;
        this.montantPaye = montantPaye;
        this.montantRestant = montantRestant;
        this.payee = payee;
        this.dateReglement = dateReglement;
    }

    // Getters
    public Long getIdReglement() { return idReglement; }
    public Float getMontantPaye() { return montantPaye; }
    public Float getMontantRestant() { return montantRestant; }
    public Boolean getPayee() { return payee; }
    public Date getDateReglement() { return dateReglement; }

    // Setters
    public void setIdReglement(Long idReglement) { this.idReglement = idReglement; }
    public void setMontantPaye(Float montantPaye) { this.montantPaye = montantPaye; }
    public void setMontantRestant(Float montantRestant) { this.montantRestant = montantRestant; }
    public void setPayee(Boolean payee) { this.payee = payee; }
    public void setDateReglement(Date dateReglement) { this.dateReglement = dateReglement; }
}
