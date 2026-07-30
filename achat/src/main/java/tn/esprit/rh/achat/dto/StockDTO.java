package tn.esprit.rh.achat.dto;

public class StockDTO {
    private Long idStock;
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;

    public StockDTO() {}

    public StockDTO(Long idStock, String libelleStock, Integer qte, Integer qteMin) {
        this.idStock = idStock;
        this.libelleStock = libelleStock;
        this.qte = qte;
        this.qteMin = qteMin;
    }

    // Getters
    public Long getIdStock() { return idStock; }
    public String getLibelleStock() { return libelleStock; }
    public Integer getQte() { return qte; }
    public Integer getQteMin() { return qteMin; }

    // Setters
    public void setIdStock(Long idStock) { this.idStock = idStock; }
    public void setLibelleStock(String libelleStock) { this.libelleStock = libelleStock; }
    public void setQte(Integer qte) { this.qte = qte; }
    public void setQteMin(Integer qteMin) { this.qteMin = qteMin; }
}
