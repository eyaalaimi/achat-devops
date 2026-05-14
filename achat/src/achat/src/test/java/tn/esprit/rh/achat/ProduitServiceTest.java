package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    private Produit produit;

    @BeforeEach
    void setUp() {
        produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCode("P001");
        produit.setLibelle("Test Product");
        produit.setPrixUnitaire(100.0);
        produit.setQuantite(10);
    }

    @Test
    void testRetrieveAllProduits() {
        // Given
        List<Produit> produits = Arrays.asList(produit);
        when(produitRepository.findAll()).thenReturn(produits);

        // When
        List<Produit> result = produitService.retrieveAllProduits();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(produit.getCode(), result.get(0).getCode());
        verify(produitRepository, times(1)).findAll();
    }

    @Test
    void testAddProduit() {
        // Given
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // When
        Produit result = produitService.addProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(produit.getCode(), result.getCode());
        assertEquals(produit.getLibelle(), result.getLibelle());
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void testUpdateProduit() {
        // Given
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // When
        Produit result = produitService.updateProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(produit.getCode(), result.getCode());
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void testRetrieveProduit() {
        // Given
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        // When
        Produit result = produitService.retrieveProduit(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdProduit());
        assertEquals(produit.getCode(), result.getCode());
        verify(produitRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteProduit() {
        // Given
        doNothing().when(produitRepository).deleteById(1L);

        // When
        produitService.deleteProduit(1L);

        // Then
        verify(produitRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRetrieveProduitNotFound() {
        // Given
        when(produitRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            produitService.retrieveProduit(99L);
        });
        
        assertNotNull(exception);
        verify(produitRepository, times(1)).findById(99L);
    }
}
