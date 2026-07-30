package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.CategorieProduitDTO;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des categories Produit")
@RequestMapping("/categorieProduit")
public class CategorieProduitController {

    @Autowired
    ICategorieProduitService categorieProduitService;
    
    // Convert Entity to DTO
    private CategorieProduitDTO convertToDTO(CategorieProduit entity) {
        if (entity == null) return null;
        return new CategorieProduitDTO(
            entity.getIdCategorieProduit(),
            entity.getCodeCategorie(),
            entity.getLibelleCategorie()
        );
    }
    
    // Convert DTO to Entity
    private CategorieProduit convertToEntity(CategorieProduitDTO dto) {
        if (dto == null) return null;
        CategorieProduit entity = new CategorieProduit();
        entity.setIdCategorieProduit(dto.getIdCategorieProduit());
        entity.setCodeCategorie(dto.getCodeCategorie());
        entity.setLibelleCategorie(dto.getLibelleCategorie());
        return entity;
    }

    @GetMapping("/retrieve-all-categorieProduit")
    @ResponseBody
    public List<CategorieProduitDTO> getCategorieProduit() {
        List<CategorieProduit> entities = categorieProduitService.retrieveAllCategorieProduits();
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-categorieProduit/{categorieProduit-id}")
    @ResponseBody
    public CategorieProduitDTO retrieveCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
        CategorieProduit entity = categorieProduitService.retrieveCategorieProduit(categorieProduitId);
        return convertToDTO(entity);
    }

    @PostMapping("/add-categorieProduit")
    @ResponseBody
    public CategorieProduitDTO addCategorieProduit(@RequestBody CategorieProduitDTO cpDTO) {
        CategorieProduit entity = convertToEntity(cpDTO);
        CategorieProduit saved = categorieProduitService.addCategorieProduit(entity);
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-categorieProduit/{categorieProduit-id}")
    @ResponseBody
    public void removeCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
        categorieProduitService.deleteCategorieProduit(categorieProduitId);
    }

    @PutMapping("/modify-categorieProduit")
    @ResponseBody
    public CategorieProduitDTO modifyCategorieProduit(@RequestBody CategorieProduitDTO categorieProduitDTO) {
        CategorieProduit entity = convertToEntity(categorieProduitDTO);
        CategorieProduit updated = categorieProduitService.updateCategorieProduit(entity);
        return convertToDTO(updated);
    }
}
