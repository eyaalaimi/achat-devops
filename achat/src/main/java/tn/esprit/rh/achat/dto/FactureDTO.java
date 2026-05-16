package tn.esprit.rh.achat.dto;

import java.util.Date;

public class FactureDTO {
    private Long idFacture;
    private Double montantRemise;
    private Double montantFacture;
    private Date dateFacture;
    private Boolean active;

    public FactureDTO() {}

    // Getters
    public Long getIdFacture() { return idFacture; }
    public Double getMontantRemise() { return montantRemise; }
    public Double getMontantFacture() { return montantFacture; }
    public Date getDateFacture() { return dateFacture; }
    public Boolean getActive() { return active; }

    // Setters
    public void setIdFacture(Long idFacture) { this.idFacture = idFacture; }
    public void setMontantRemise(Double montantRemise) { this.montantRemise = montantRemise; }
    public void setMontantFacture(Double montantFacture) { this.montantFacture = montantFacture; }
    public void setDateFacture(Date dateFacture) { this.dateFacture = dateFacture; }
    public void setActive(Boolean active) { this.active = active; }
}
