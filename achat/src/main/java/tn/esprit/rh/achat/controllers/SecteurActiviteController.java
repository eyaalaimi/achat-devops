package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.SecteurActiviteDTO;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des secteurActivites")
@RequestMapping("/secteurActivite")
public class SecteurActiviteController {

    private static final Logger log = LoggerFactory.getLogger(SecteurActiviteController.class);

    @Autowired
    ISecteurActiviteService secteurActiviteService;

    private SecteurActiviteDTO convertToDTO(SecteurActivite entity) {
        if (entity == null) return null;
        return new SecteurActiviteDTO(
            entity.getIdSecteurActivite(),
            entity.getCodeSecteur(),
            entity.getLibelleSecteur()
        );
    }

    private SecteurActivite convertToEntity(SecteurActiviteDTO dto) {
        if (dto == null) return null;
        SecteurActivite entity = new SecteurActivite();
        entity.setIdSecteurActivite(dto.getIdSecteurActivite());
        entity.setCodeSecteur(dto.getCodeSecteur());
        entity.setLibelleSecteur(dto.getLibelleSecteur());
        return entity;
    }

    @GetMapping("/retrieve-all-secteurActivite")
    @ResponseBody
    public List<SecteurActiviteDTO> getSecteurActivites() {
        return secteurActiviteService.retrieveAllSecteurActivite()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-secteurActivite/{secteurActivite-id}")
    @ResponseBody
    public SecteurActiviteDTO retrieveSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
        return convertToDTO(secteurActiviteService.retrieveSecteurActivite(secteurActiviteId));
    }

    @PostMapping("/add-secteurActivite")
    @ResponseBody
    public SecteurActiviteDTO addSecteurActivite(@RequestBody SecteurActiviteDTO secteurActiviteDTO) {
        SecteurActivite entity = convertToEntity(secteurActiviteDTO);
        SecteurActivite saved = secteurActiviteService.addSecteurActivite(entity);
        log.info("Added secteurActivite with id: {}", saved.getIdSecteurActivite());
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-secteurActivite/{secteurActivite-id}")
    @ResponseBody
    public void removeSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
        log.info("Deleting secteurActivite with id: {}", secteurActiviteId);
        secteurActiviteService.deleteSecteurActivite(secteurActiviteId);
    }

    @PutMapping("/modify-secteurActivite")
    @ResponseBody
    public SecteurActiviteDTO modifySecteurActivite(@RequestBody SecteurActiviteDTO secteurActiviteDTO) {
        SecteurActivite entity = convertToEntity(secteurActiviteDTO);
        SecteurActivite updated = secteurActiviteService.updateSecteurActivite(entity);
        log.info("Modified secteurActivite with id: {}", updated.getIdSecteurActivite());
        return convertToDTO(updated);
    }
}
