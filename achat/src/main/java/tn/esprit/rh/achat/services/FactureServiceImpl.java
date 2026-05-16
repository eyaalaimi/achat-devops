package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.Date;
import java.util.List;

@Service
public class FactureServiceImpl implements IFactureService {

    private static final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    @Autowired
    FactureRepository factureRepository;
    
    @Autowired
    OperateurRepository operateurRepository;

    @Override
    public List<Facture> retrieveAllFactures() {
        List<Facture> factures = factureRepository.findAll();
        for (Facture facture : factures) {
            log.info("facture : {}", facture);
        }
        return factures;
    }

    @Override
    public Facture addFacture(Facture f) {
        return factureRepository.save(f);
    }

    @Override
    public Facture updateFacture(Facture f) {
        return factureRepository.save(f);
    }

    @Override
    public Facture retrieveFacture(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    @Override
    public List<Facture> getFacturesByFournisseur(Long idFournisseur) {
        return factureRepository.findByFournisseurId(idFournisseur);
    }

    @Override
    public void cancelFacture(Long idFacture) {
        Facture facture = factureRepository.findById(idFacture).orElse(null);
        if (facture != null) {
            facture.setActive(false);
            factureRepository.save(facture);
            log.info("Cancelled facture with id: {}", idFacture);
        }
    }

    @Override
    public void assignOperateurToFacture(Long idOperateur, Long idFacture) {
        Facture facture = factureRepository.findById(idFacture).orElse(null);
        Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
        if (facture != null && operateur != null) {
            facture.getOperateurs().add(operateur);
            factureRepository.save(facture);
            log.info("Assigned operateur {} to facture {}", idOperateur, idFacture);
        }
    }

    @Override
    public float pourcentageRecouvrement(Date startDate, Date endDate) {
        List<Facture> factures = factureRepository.findAll();
        if (factures == null || factures.isEmpty()) {
            return 0;
        }
        float total = 0;
        for (Facture facture : factures) {
            if (facture.getMontantFacture() != null) {
                total += facture.getMontantFacture();
            }
        }
        return total;
    }
}
