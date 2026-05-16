package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;

import java.util.Date;
import java.util.List;

@Service
public class FactureServiceImpl implements IFactureService {

    private static final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    @Autowired
    FactureRepository factureRepository;

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
    public float pourcentageRecouvrement(Date startDate, Date endDate) {
        List<Facture> factures = factureRepository.findByDateFactureBetween(startDate, endDate);
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
