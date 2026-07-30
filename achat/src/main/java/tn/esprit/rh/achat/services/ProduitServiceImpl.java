package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import javax.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;
	private static final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);

	@Override
	public List<Produit> retrieveAllProduits() {
    List<Produit> produits = produitRepository.findAll();
    for (Produit produit : produits) {
        log.info("Produit : {}", produit);
    }
    return produits;
}
	@Transactional
	public Produit addProduit(Produit p) {
		produitRepository.save(p);
		return p;
	}

	

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}

	@Override
	public Produit updateProduit(Produit p) {
		return produitRepository.save(p);
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
    Produit produit = produitRepository.findById(produitId).orElse(null);
    log.info("produit : {}", produit);
    return produit;
}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
    Produit produit = produitRepository.findById(idProduit).orElse(null);
    if (produit == null) {
        log.warn("Produit not found with id: {}", idProduit);
        return;
    }
    Stock stock = stockRepository.findById(idStock).orElse(null);
    if (stock == null) {
        log.warn("Stock not found with id: {}", idStock);
        return;
    }
    produit.setStock(stock);
    produitRepository.save(produit);
    log.info("Assigned stock {} to produit {}", idStock, idProduit);
}

}
