package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.ProduitDTO;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    IProduitService produitService;

    // Convert Entity to DTO
    private ProduitDTO convertToDTO(Produit entity) {
        if (entity == null) return null;
        return new ProduitDTO(
            entity.getIdProduit(),
            entity.getCode(),
            entity.getLibelle(),
            entity.getPrixUnitaire(),
            entity.getQuantite()
        );
    }

    // Convert DTO to Entity
    private Produit convertToEntity(ProduitDTO dto) {
        if (dto == null) return null;
        Produit entity = new Produit();
        entity.setIdProduit(dto.getIdProduit());
        entity.setCode(dto.getCode());
        entity.setLibelle(dto.getLibelle());
        entity.setPrixUnitaire(dto.getPrixUnitaire());
        entity.setQuantite(dto.getQuantite());
        return entity;
    }

    @GetMapping("/retrieve-all-produits")
    @ResponseBody
    public List<ProduitDTO> getProduits() {
        return produitService.retrieveAllProduits()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-produit/{produit-id}")
    @ResponseBody
    public ProduitDTO retrieveProduit(@PathVariable("produit-id") Long produitId) {
        return convertToDTO(produitService.retrieveProduit(produitId));
    }

    @PostMapping("/add-produit")
    @ResponseBody
    public ProduitDTO addProduit(@RequestBody ProduitDTO produitDTO) {
        Produit entity = convertToEntity(produitDTO);
        Produit saved = produitService.addProduit(entity);
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-produit/{produit-id}")
    @ResponseBody
    public void removeProduit(@PathVariable("produit-id") Long produitId) {
        produitService.deleteProduit(produitId);
    }

    @PutMapping("/modify-produit")
    @ResponseBody
    public ProduitDTO modifyProduit(@RequestBody ProduitDTO produitDTO) {
        Produit entity = convertToEntity(produitDTO);
        Produit updated = produitService.updateProduit(entity);
        return convertToDTO(updated);
    }
}
