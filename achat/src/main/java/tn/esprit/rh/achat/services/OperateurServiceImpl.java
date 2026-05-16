package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.List;

@Service
public class OperateurServiceImpl implements IOperateurService {

    private static final Logger log = LoggerFactory.getLogger(OperateurServiceImpl.class);

    @Autowired
    OperateurRepository operateurRepository;

    @Override
    public List<Operateur> retrieveAllOperateurs() {
        List<Operateur> operateurs = operateurRepository.findAll();
        for (Operateur operateur : operateurs) {
            log.info("operateur : {}", operateur);
        }
        return operateurs;
    }

    @Override
    public Operateur addOperateur(Operateur o) {
        return operateurRepository.save(o);
    }

    @Override
    public Operateur updateOperateur(Operateur o) {
        if (o == null || o.getIdOperateur() == null) {
            log.warn("Cannot update null operateur or operateur without ID");
            return null;
        }
        Operateur existing = operateurRepository.findById(o.getIdOperateur()).orElse(null);
        if (existing == null) {
            log.warn("Operateur not found with id: {}", o.getIdOperateur());
            return null;
        }
        existing.setNom(o.getNom());
        existing.setPrenom(o.getPrenom());
        existing.setPassword(o.getPassword());
        return operateurRepository.save(existing);
    }

    @Override
    public Operateur retrieveOperateur(Long id) {
        return operateurRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOperateur(Long id) {
        operateurRepository.deleteById(id);
        log.info("Deleted operateur with id: {}", id);
    }
}
