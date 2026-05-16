package tn.esprit.rh.achat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.List;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService{

	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;
	@Override
	public List<SecteurActivite> retrieveAllSecteurActivite() {
    return secteurActiviteRepository.findAll();
}

	@Override
	public SecteurActivite addSecteurActivite(SecteurActivite sa) {
    return secteurActiviteRepository.save(sa);
}


	@Override
	public void deleteSecteurActivite(Long id) {
		secteurActiviteRepository.deleteById(id);
		
	}

	@Override
	public SecteurActivite updateSecteurActivite(SecteurActivite sa) {
    if (sa == null || sa.getIdSecteurActivite() == null) {
        return null;
    }
    SecteurActivite existing = secteurActiviteRepository.findById(sa.getIdSecteurActivite()).orElse(null);
    if (existing == null) {
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
}
