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
}
