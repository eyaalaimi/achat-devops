package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.StockDTO;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
public class StockRestController {

    private static final Logger log = LoggerFactory.getLogger(StockRestController.class);

    @Autowired
    IStockService stockService;

    // Convert Entity to DTO
    private StockDTO convertToDTO(Stock entity) {
        if (entity == null) return null;
        return new StockDTO(
            entity.getIdStock(),
            entity.getLibelleStock(),
            entity.getQte(),
            entity.getQteMin()
        );
    }

    // Convert DTO to Entity
    private Stock convertToEntity(StockDTO dto) {
        if (dto == null) return null;
        Stock entity = new Stock();
        entity.setIdStock(dto.getIdStock());
        entity.setLibelleStock(dto.getLibelleStock());
        entity.setQte(dto.getQte());
        entity.setQteMin(dto.getQteMin());
        return entity;
    }

    @GetMapping("/retrieve-all-stocks")
    @ResponseBody
    public List<StockDTO> getStocks() {
        return stockService.retrieveAllStocks()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-stock/{stock-id}")
    @ResponseBody
    public StockDTO retrieveStock(@PathVariable("stock-id") Long stockId) {
        return convertToDTO(stockService.retrieveStock(stockId));
    }

    @PostMapping("/add-stock")
    @ResponseBody
    public StockDTO addStock(@RequestBody StockDTO stockDTO) {
        Stock entity = convertToEntity(stockDTO);
        Stock saved = stockService.addStock(entity);
        log.info("Added stock with id: {}", saved.getIdStock());
        return convertToDTO(saved);
    }

    @DeleteMapping("/remove-stock/{stock-id}")
    @ResponseBody
    public void removeStock(@PathVariable("stock-id") Long stockId) {
        log.info("Deleting stock with id: {}", stockId);
        stockService.deleteStock(stockId);
    }

    @PutMapping("/modify-stock")
    @ResponseBody
    public StockDTO modifyStock(@RequestBody StockDTO stockDTO) {
        Stock entity = convertToEntity(stockDTO);
        Stock updated = stockService.updateStock(entity);
        log.info("Modified stock with id: {}", updated.getIdStock());
        return convertToDTO(updated);
    }
}
