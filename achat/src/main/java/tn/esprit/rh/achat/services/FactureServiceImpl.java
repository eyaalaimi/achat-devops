package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.DetailFactureRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.List;

@Service
public class FactureServiceImpl implements IFactureService {

    private static final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    @Autowired
    FactureRepository factureRepository;
    
    @Autowired
    DetailFactureRepository detailFactureRepository;
    
    @Autowired
    FournisseurRepository fournisseurRepository;
    
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
    public Facture addFacture(Facture f, Long idFournisseur) {
        Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
        if (fournisseur != null) {
            f.setFournisseur(fournisseur);
        }
        return factureRepository.save(f);
    }

    @Override
    public List<Facture> getFacturesByFournisseur(Long idFournisseur) {
        Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
        if (fournisseur != null) {
            return fournisseur.getFactures();
        }
        return null;
    }

    @Override
    public void assignOperateurToFacture(Long idOperateur, Long idFacture) {
        Facture facture = factureRepository.findById(idFacture).orElse(null);
        Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
        if (facture != null && operateur != null) {
            facture.getOperateurs().add(operateur);
            factureRepository.save(facture);
        }
    }

    @Override
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        // Implementation for calculating revenue between two dates
        List<Facture> factures = factureRepository.findByDateFactureBetween(startDate, endDate);
        float total = 0;
        for (Facture facture : factures) {
            total += facture.getMontantFacture();
        }
        return total;
    }

    @Override
    public float getMontantFactureEntreDeuxDate(Date startDate, Date endDate) {
        List<Facture> factures = factureRepository.findByDateFactureBetween(startDate, endDate);
        float total = 0;
        for (Facture facture : factures) {
            total += facture.getMontantFacture();
        }
        return total;
    }

    @Override
    public List<Facture> getFacturesByOperateur(Long idOperateur) {
        Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
        if (operateur != null) {
            return (List<Facture>) operateur.getFactures();
        }
        return null;
    }

    @Override
    public Float getMontantFactureByFactureId(Long idFacture) {
        Facture facture = factureRepository.findById(idFacture).orElse(null);
        if (facture != null) {
            return facture.getMontantFacture();
        }
        return null;
    }

    @Override
    public Facture addFactureWithDetails(Facture facture, List<DetailFacture> details) {
        facture = factureRepository.save(facture);
        for (DetailFacture detail : details) {
            detail.setFacture(facture);
            detailFactureRepository.save(detail);
        }
        return facture;
    }
}
