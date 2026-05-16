package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService {

    private static final Logger log = LoggerFactory.getLogger(SecteurActiviteServiceImpl.class);

    @Autowired
    SecteurActiviteRepository secteurActiviteRepository;

    @Override
    public List<SecteurActivite> retrieveAllSecteurActivite() {
        List<SecteurActivite> secteurActivites = new ArrayList<>();
        secteurActiviteRepository.findAll().forEach(secteurActivites::add);
        for (SecteurActivite secteurActivite : secteurActivites) {
            log.info("secteurActivite : {}", secteurActivite);
        }
        return secteurActivites;
    }

    @Override
    public SecteurActivite addSecteurActivite(SecteurActivite sa) {
        return secteurActiviteRepository.save(sa);
    }

    @Override
    public SecteurActivite updateSecteurActivite(SecteurActivite sa) {
        if (sa == null || sa.getIdSecteurActivite() == null) {
            log.warn("Cannot update null secteurActivite or without ID");
            return null;
        }
        SecteurActivite existing = secteurActiviteRepository.findById(sa.getIdSecteurActivite()).orElse(null);
        if (existing == null) {
            log.warn("SecteurActivite not found with id: {}", sa.getIdSecteurActivite());
            return null;
        }
        existing.setCodeSecteur(sa.getCodeSecteur());
        existing.setLibelleSecteur(sa.getLibelleSecteur());
        return secteurActiviteRepository.save(existing);
    }

    @Override
    public SecteurActivite retrieveSecteurActivite(Long id) {
        return secteurActiviteRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSecteurActivite(Long id) {
        secteurActiviteRepository.deleteById(id);
        log.info("Deleted secteurActivite with id: {}", id);
    }
}
