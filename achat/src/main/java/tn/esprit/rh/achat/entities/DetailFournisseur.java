package tn.esprit.rh.achat.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailFournisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetailFournisseur;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dateDebutCollaboration;
	private String adresse;
	private String matricule;
	@OneToOne(mappedBy="detailFournisseur")
	@JsonIgnore
	private Fournisseur fournisseur;
	// Getters
    public Long getIdDetailFournisseur() { return idDetailFournisseur; }
    public Date getDateDebutCollaboration() { return dateDebutCollaboration; }
    public String getAdresse() { return adresse; }
    public String getEmail() { return email; }
    public Fournisseur getFournisseur() { return fournisseur; }

    // Setters
    public void setIdDetailFournisseur(Long idDetailFournisseur) { this.idDetailFournisseur = idDetailFournisseur; }
    public void setDateDebutCollaboration(Date dateDebutCollaboration) { this.dateDebutCollaboration = dateDebutCollaboration; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setEmail(String email) { this.email = email; }
    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }
}	
	
}
