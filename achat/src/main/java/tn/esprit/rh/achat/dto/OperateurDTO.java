package tn.esprit.rh.achat.dto;

public class OperateurDTO {
    private Long idOperateur;
    private String nom;
    private String prenom;
    private String password;

    public OperateurDTO() {}

    public OperateurDTO(Long idOperateur, String nom, String prenom, String password) {
        this.idOperateur = idOperateur;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }

    // Getters
    public Long getIdOperateur() { return idOperateur; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getPassword() { return password; }

    // Setters
    public void setIdOperateur(Long idOperateur) { this.idOperateur = idOperateur; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setPassword(String password) { this.password = password; }
}
