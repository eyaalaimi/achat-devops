package tn.esprit.rh.achat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.List;

@Service
public class CategorieProduitServiceImpl implements ICategorieProduitService {

    @Autowired
    CategorieProduitRepository categorieProduitRepository;

    @Override
    public List<CategorieProduit> retrieveAllCategorieProduits() {
        return categorieProduitRepository.findAll();
    }

    @Override
    public CategorieProduit addCategorieProduit(CategorieProduit cp) {
        return categorieProduitRepository.save(cp);
    }

    @Override
    public CategorieProduit updateCategorieProduit(CategorieProduit cp) {
        if (cp == null || cp.getIdCategorieProduit() == null) {
            throw new IllegalArgumentException("Cannot update null entity or entity without ID");
        }
        CategorieProduit existing = categorieProduitRepository.findById(cp.getIdCategorieProduit())
            .orElseThrow(() -> new RuntimeException("CategorieProduit not found with id: " + cp.getIdCategorieProduit()));
        existing.setCodeCategorie(cp.getCodeCategorie());
        existing.setLibelleCategorie(cp.getLibelleCategorie());
        return categorieProduitRepository.save(existing);
    }

    @Override
    public CategorieProduit retrieveCategorieProduit(Long id) {
        return categorieProduitRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategorieProduit(Long id) {
        categorieProduitRepository.deleteById(id);
    }
}
