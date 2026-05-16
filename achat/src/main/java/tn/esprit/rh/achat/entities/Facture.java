package tn.esprit.rh.achat.entities;

import lombok.*;
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
    
    // relationships...
}
