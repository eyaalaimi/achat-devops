package tn.esprit.rh.achat.dto;

import java.util.Date;

public class FactureDTO {
    private Long idFacture;
    private Double montantRemise;
    private Double montantFacture;
    private Date dateFacture;
    private Boolean active;
    
    // Constructors
    public FactureDTO() {}
    
    public FactureDTO(Long idFacture, Double montantRemise, Double montantFacture, Date dateFacture, Boolean active) {
        this.idFacture = idFacture;
        this.montantRemise = montantRemise;
        this.montantFacture = montantFacture;
        this.dateFacture = dateFacture;
        this.active = active;
    }
    
    // Getters and Setters
    public Long getIdFacture() { return idFacture; }
    public void setIdFacture(Long idFacture) { this.idFacture = idFacture; }
    
    public Double getMontantRemise() { return montantRemise; }
    public void setMontantRemise(Double montantRemise) { this.montantRemise = montantRemise; }
    
    public Double getMontantFacture() { return montantFacture; }
    public void setMontantFacture(Double montantFacture) { this.montantFacture = montantFacture; }
    
    public Date getDateFacture() { return dateFacture; }
    public void setDateFacture(Date dateFacture) { this.dateFacture = dateFacture; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
