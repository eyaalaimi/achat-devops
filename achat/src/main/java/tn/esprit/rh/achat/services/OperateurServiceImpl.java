package tn.esprit.rh.achat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.List;

@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository operateurRepository;
	@Override
	public List<Operateur> retrieveAllOperateurs() {
		return (List<Operateur>) operateurRepository.findAll();
	}

	@Override
	public Operateur addOperateur(Operateur o) {
    return operateurRepository.save(o);
}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);
		
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
}
