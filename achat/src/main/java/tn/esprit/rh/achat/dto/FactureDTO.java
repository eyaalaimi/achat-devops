package tn.esprit.rh.achat.dto;

import java.util.Date;

public class FactureDTO {
    private Long idFacture;
    private Float montantRemise;
    private Float montantFacture;
    private Date dateFacture;
    private Boolean active;

    // Default constructor
    public FactureDTO() {}

    // Constructor with fields
    public FactureDTO(Long idFacture, Float montantRemise, Float montantFacture, Date dateFacture, Boolean active) {
        this.idFacture = idFacture;
        this.montantRemise = montantRemise;
        this.montantFacture = montantFacture;
        this.dateFacture = dateFacture;
        this.active = active;
    }

    // Getters
    public Long getIdFacture() { return idFacture; }
    public Float getMontantRemise() { return montantRemise; }
    public Float getMontantFacture() { return montantFacture; }
    public Date getDateFacture() { return dateFacture; }
    public Boolean getActive() { return active; }

    // Setters
    public void setIdFacture(Long idFacture) { this.idFacture = idFacture; }
    public void setMontantRemise(Float montantRemise) { this.montantRemise = montantRemise; }
    public void setMontantFacture(Float montantFacture) { this.montantFacture = montantFacture; }
    public void setDateFacture(Date dateFacture) { this.dateFacture = dateFacture; }
    public void setActive(Boolean active) { this.active = active; }
}
