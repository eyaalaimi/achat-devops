package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.FactureDTO;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des factures")
@RequestMapping("/facture")
public class FactureRestController {

    private static final Logger log = LoggerFactory.getLogger(FactureRestController.class);

    @Autowired
    IFactureService factureService;

    // Convert Entity to DTO
    private FactureDTO convertToDTO(Facture entity) {
        if (entity == null) return null;
        return new FactureDTO(
            entity.getIdFacture(),
            entity.getMontantRemise(),
            entity.getMontantFacture(),
            entity.getDateFacture(),
            entity.getActive()
        );
    }

    // Convert DTO to Entity
    private Facture convertToEntity(FactureDTO dto) {
        if (dto == null) return null;
        Facture entity = new Facture();
        entity.setIdFacture(dto.getIdFacture());
        entity.setMontantRemise(dto.getMontantRemise());
        entity.setMontantFacture(dto.getMontantFacture());
        entity.setDateFacture(dto.getDateFacture());
        entity.setActive(dto.getActive());
        return entity;
    }

    @GetMapping("/retrieve-all-factures")
    @ResponseBody
    public List<FactureDTO> getFactures() {
        return factureService.retrieveAllFactures()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-facture/{facture-id}")
    @ResponseBody
    public FactureDTO retrieveFacture(@PathVariable("facture-id") Long factureId) {
        return convertToDTO(factureService.retrieveFacture(factureId));
    }

    @PostMapping("/add-facture")
    @ResponseBody
    public FactureDTO addFacture(@RequestBody FactureDTO factureDTO) {
        Facture entity = convertToEntity(factureDTO);
        Facture saved = factureService.addFacture(entity);
        log.info("Added facture with id: {}", saved.getIdFacture());
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-facture/{facture-id}")
    @ResponseBody
    public void removeFacture(@PathVariable("facture-id") Long factureId) {
        log.info("Deleting facture with id: {}", factureId);
    }

    @PutMapping("/modify-facture")
    @ResponseBody
    public FactureDTO modifyFacture(@RequestBody FactureDTO factureDTO) {
        Facture entity = convertToEntity(factureDTO);
        Facture updated = factureService.addFacture(entity);
        log.info("Modified facture with id: {}", updated.getIdFacture());
        return convertToDTO(updated);
    }
}
