package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.FournisseurDTO;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.services.IFournisseurService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des fournisseurs")
@RequestMapping("/fournisseur")
public class FournisseurRestController {

    @Autowired
    IFournisseurService fournisseurService;

    // Convert Entity to DTO
    private FournisseurDTO convertToDTO(Fournisseur entity) {
        if (entity == null) return null;
        return new FournisseurDTO(
            entity.getIdFournisseur(),
            entity.getCode(),
            entity.getLibelle(),
            entity.getDateDebutCollaboration()
        );
    }

    // Convert DTO to Entity
    private Fournisseur convertToEntity(FournisseurDTO dto) {
        if (dto == null) return null;
        Fournisseur entity = new Fournisseur();
        entity.setIdFournisseur(dto.getIdFournisseur());
        entity.setCode(dto.getCode());
        entity.setLibelle(dto.getLibelle());
        entity.setDateDebutCollaboration(dto.getDateDebutCollaboration());
        return entity;
    }

    @GetMapping("/retrieve-all-fournisseurs")
    @ResponseBody
    public List<FournisseurDTO> getFournisseurs() {
        return fournisseurService.retrieveAllFournisseurs()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-fournisseur/{fournisseur-id}")
    @ResponseBody
    public FournisseurDTO retrieveFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
        return convertToDTO(fournisseurService.retrieveFournisseur(fournisseurId));
    }

    @PostMapping("/add-fournisseur")
    @ResponseBody
    public FournisseurDTO addFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        Fournisseur entity = convertToEntity(fournisseurDTO);
        Fournisseur saved = fournisseurService.addFournisseur(entity);
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-fournisseur/{fournisseur-id}")
    @ResponseBody
    public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
        fournisseurService.deleteFournisseur(fournisseurId);
    }

    @PutMapping("/modify-fournisseur")
    @ResponseBody
    public FournisseurDTO modifyFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        Fournisseur entity = convertToEntity(fournisseurDTO);
        Fournisseur updated = fournisseurService.updateFournisseur(entity);
        return convertToDTO(updated);
    }
}
