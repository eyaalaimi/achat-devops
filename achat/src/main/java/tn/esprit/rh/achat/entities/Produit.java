package tn.esprit.rh.achat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    
    private String code;
    private String libelle;
    private Float prixUnitaire;
    private Integer quantite;
    
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    
    @ManyToOne
    @JoinColumn(name = "categorie_produit_id")
    private CategorieProduit categorieProduit;
}
