package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.List;

@Service
public class StockServiceImpl implements IStockService {

    private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    StockRepository stockRepository;

    @Override
    public List<Stock> retrieveAllStocks() {
        log.info("In method retrieveAllStocks");
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            log.info("Stock : {}", stock);
        }
        log.info("out of method retrieveAllStocks");
        return stocks;
    }

    @Override
    public Stock addStock(Stock s) {
        return stockRepository.save(s);
    }

    @Override
    public Stock updateStock(Stock s) {
        return stockRepository.save(s);
    }

    @Override
    public Stock retrieveStock(Long id) {
        log.info("In method retrieveStock");
        Stock stock = stockRepository.findById(id).orElse(null);
        log.info("Stock : {}", stock);
        log.info("out of method retrieveStock");
        return stock;
    }

    @Override
    public void deleteStock(Long id) {
        log.info("In method deleteStock");
        stockRepository.deleteById(id);
        log.info("out of method deleteStock");
    }

    @Override
    public String retrieveStatusStock() {
        List<Stock> stocks = stockRepository.findAll();
        StringBuilder status = new StringBuilder();
        for (Stock stock : stocks) {
            if (stock.getQte() < stock.getQteMin()) {
                status.append("Stock ").append(stock.getLibelleStock()).append(": LOW STOCK! Qte=")
                      .append(stock.getQte()).append(", Min=").append(stock.getQteMin()).append("\n");
                log.warn("Stock {} is below minimum level", stock.getLibelleStock());
            } else {
                status.append("Stock ").append(stock.getLibelleStock()).append(": OK. Qte=")
                      .append(stock.getQte()).append(", Min=").append(stock.getQteMin()).append("\n");
            }
        }
        return status.toString();
    }
}
