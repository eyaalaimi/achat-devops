package tn.esprit.rh.achat.dto;

public class SecteurActiviteDTO {
    private Long idSecteurActivite;
    private String codeSecteur;
    private String libelleSecteur;

    public SecteurActiviteDTO() {}

    public SecteurActiviteDTO(Long idSecteurActivite, String codeSecteur, String libelleSecteur) {
        this.idSecteurActivite = idSecteurActivite;
        this.codeSecteur = codeSecteur;
        this.libelleSecteur = libelleSecteur;
    }

    // Getters
    public Long getIdSecteurActivite() { return idSecteurActivite; }
    public String getCodeSecteur() { return codeSecteur; }
    public String getLibelleSecteur() { return libelleSecteur; }

    // Setters
    public void setIdSecteurActivite(Long idSecteurActivite) { this.idSecteurActivite = idSecteurActivite; }
    public void setCodeSecteur(String codeSecteur) { this.codeSecteur = codeSecteur; }
    public void setLibelleSecteur(String libelleSecteur) { this.libelleSecteur = libelleSecteur; }
}
