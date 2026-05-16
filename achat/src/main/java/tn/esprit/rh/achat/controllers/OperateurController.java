package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.OperateurDTO;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des operateurs")
@RequestMapping("/operateur")
public class OperateurController {

    @Autowired
    IOperateurService operateurService;

    // Convert Entity to DTO
    private OperateurDTO convertToDTO(Operateur entity) {
        if (entity == null) return null;
        return new OperateurDTO(
            entity.getIdOperateur(),
            entity.getNom(),
            entity.getPrenom(),
            entity.getPassword()
        );
    }

    // Convert DTO to Entity
    private Operateur convertToEntity(OperateurDTO dto) {
        if (dto == null) return null;
        Operateur entity = new Operateur();
        entity.setIdOperateur(dto.getIdOperateur());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    @GetMapping("/retrieve-all-operateurs")
    @ResponseBody
    public List<OperateurDTO> getOperateurs() {
        return operateurService.retrieveAllOperateurs()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-operateur/{operateur-id}")
    @ResponseBody
    public OperateurDTO retrieveOperateur(@PathVariable("operateur-id") Long operateurId) {
        return convertToDTO(operateurService.retrieveOperateur(operateurId));
    }

    @PostMapping("/add-operateur")
    @ResponseBody
    public OperateurDTO addOperateur(@RequestBody OperateurDTO operateurDTO) {
        Operateur entity = convertToEntity(operateurDTO);
        Operateur saved = operateurService.addOperateur(entity);
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-operateur/{operateur-id}")
    @ResponseBody
    public void removeOperateur(@PathVariable("operateur-id") Long operateurId) {
        operateurService.deleteOperateur(operateurId);
    }

    @PutMapping("/modify-operateur")
    @ResponseBody
    public OperateurDTO modifyOperateur(@RequestBody OperateurDTO operateurDTO) {
        Operateur entity = convertToEntity(operateurDTO);
        Operateur updated = operateurService.updateOperateur(entity);
        return convertToDTO(updated);
    }
}
