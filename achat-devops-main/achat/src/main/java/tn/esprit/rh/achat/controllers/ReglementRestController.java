package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.ReglementDTO;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des reglements")
@RequestMapping("/reglement")
public class ReglementRestController {

    private static final Logger log = LoggerFactory.getLogger(ReglementRestController.class);

    @Autowired
    IReglementService reglementService;

    private ReglementDTO convertToDTO(Reglement entity) {
        if (entity == null) return null;
        return new ReglementDTO(
            entity.getIdReglement(),
            entity.getMontantPaye(),
            entity.getMontantRestant(),
            entity.getPayee(),
            entity.getDateReglement()
        );
    }

    private Reglement convertToEntity(ReglementDTO dto) {
        if (dto == null) return null;
        Reglement entity = new Reglement();
        entity.setIdReglement(dto.getIdReglement());
        entity.setMontantPaye(dto.getMontantPaye());
        entity.setMontantRestant(dto.getMontantRestant());
        entity.setPayee(dto.getPayee());
        entity.setDateReglement(dto.getDateReglement());
        return entity;
    }

    @GetMapping("/retrieve-all-reglements")
    @ResponseBody
    public List<ReglementDTO> getReglements() {
        return reglementService.retrieveAllReglements()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-reglement/{reglement-id}")
    @ResponseBody
    public ReglementDTO retrieveReglement(@PathVariable("reglement-id") Long reglementId) {
        return convertToDTO(reglementService.retrieveReglement(reglementId));
    }

    @PostMapping("/add-reglement")
    @ResponseBody
    public ReglementDTO addReglement(@RequestBody ReglementDTO reglementDTO) {
        Reglement entity = convertToEntity(reglementDTO);
        Reglement saved = reglementService.addReglement(entity);
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-reglement/{reglement-id}")
    @ResponseBody
    public void removeReglement(@PathVariable("reglement-id") Long reglementId) {
    log.info("Deleting reglement with id: {}", reglementId);    }

    @PutMapping("/modify-reglement")
    @ResponseBody
    public ReglementDTO modifyReglement(@RequestBody ReglementDTO reglementDTO) {
        log.info("Modifying reglement: {}", reglementDTO);
        return reglementDTO;
       
    }
}
