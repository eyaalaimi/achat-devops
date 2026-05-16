package tn.esprit.rh.achat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facture implements Serializable {

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
    private Fournisseur fournisseur;
    
    @ManyToMany
    @JsonIgnore
    private Set<Operateur> operateurs;
}
