package tn.esprit.rh.achat.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailFacture implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetailFacture;
	private Integer qteCommandee;
	private float prixTotalDetail;
	private Integer pourcentageRemise;
	private float montantRemise;
	@ManyToOne
	private Produit produit;
	@ManyToOne
	@JsonIgnore
	Facture facture;
	 // Getters
    public Long getIdDetailFacture() { return idDetailFacture; }
    public Integer getQteCommandee() { return qteCommandee; }
    public Float getPrixTotalDetail() { return prixTotalDetail; }
    public Float getPourcentageRemise() { return pourcentageRemise; }
    public Float getMontantRemise() { return montantRemise; }
    public Facture getFacture() { return facture; }
    public Produit getProduit() { return produit; }

    // Setters
    public void setIdDetailFacture(Long idDetailFacture) { this.idDetailFacture = idDetailFacture; }
    public void setQteCommandee(Integer qteCommandee) { this.qteCommandee = qteCommandee; }
    public void setPrixTotalDetail(Float prixTotalDetail) { this.prixTotalDetail = prixTotalDetail; }
    public void setPourcentageRemise(Float pourcentageRemise) { this.pourcentageRemise = pourcentageRemise; }
    public void setMontantRemise(Float montantRemise) { this.montantRemise = montantRemise; }
    public void setFacture(Facture facture) { this.facture = facture; }
    public void setProduit(Produit produit) { this.produit = produit; }
}
}
