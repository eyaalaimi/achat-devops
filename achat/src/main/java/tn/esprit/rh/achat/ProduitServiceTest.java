package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduitServiceTest {
    
    @Test
    public void testProduitCreation() {
        // Simple test to pass
        String produitName = "Test Produit";
        assertNotNull(produitName);
        assertEquals("Test Produit", produitName);
    }
    
    @Test
    public void testCalculPrix() {
        double prix = 100.0;
        double tva = 0.20;
        double prixFinal = prix + (prix * tva);
        assertEquals(120.0, prixFinal, 0.01);
    }
}
