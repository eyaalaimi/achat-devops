package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.List;

@RestController
@Api(tags = "Gestion des factures")
@RequestMapping("/facture")
public class FactureRestController {

    private static final Logger log = LoggerFactory.getLogger(FactureRestController.class);

    @Autowired
    IFactureService factureService;

    @GetMapping("/retrieve-all-factures")
    @ResponseBody
    public List<Facture> getFactures() {
        return factureService.retrieveAllFactures();
    }

    @GetMapping("/retrieve-facture/{facture-id}")
    @ResponseBody
    public Facture retrieveFacture(@PathVariable("facture-id") Long factureId) {
        return factureService.retrieveFacture(factureId);
    }

    @PostMapping("/add-facture")
    @ResponseBody
    public Facture addFacture(@RequestBody Facture facture) {
        return factureService.addFacture(facture);
    }

    @DeleteMapping("/remove-facture/{facture-id}")
    @ResponseBody
    public void removeFacture(@PathVariable("facture-id") Long factureId) {
        log.info("Deleting facture with id: {}", factureId);
    }

    @PutMapping("/modify-facture")
    @ResponseBody
    public Facture modifyFacture(@RequestBody Facture facture) {
        log.info("Modifying facture: {}", facture);
        return facture;
    }
}
