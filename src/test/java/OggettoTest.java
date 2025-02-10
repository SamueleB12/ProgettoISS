package com.ProgettoISS;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class OggettoTest {
    private Oggetto broccolo;

    @BeforeEach
    public void setUp() {
        broccolo = new Oggetto("Broccolo", true, 300, 200); // Crea un oggetto broccolo
    }

    @Test
    public void testNomeOggetto() {
        assertEquals("Broccolo", broccolo.getNome()); // Verifica che il nome sia corretto
    }

    @Test
    public void testInterattivo() {
        assertTrue(broccolo.isInterattivo()); // Verifica che l'oggetto sia interattivo
    }

    @Test
    public void testPosizione() {
        assertEquals(300, broccolo.getX()); // Verifica la posizione X dell'oggetto
        assertEquals(200, broccolo.getY()); // Verifica la posizione Y dell'oggetto
    }

    @Test
    public void testInteragisci() {
        // Qui possiamo catturare l'output per verificare se l'interazione funziona correttamente.
        // In un caso reale, potresti voler utilizzare una libreria di mocking o redirigere lo stream.
        broccolo.interagisci(); // Dovrebbe stampare "Hai raccolto: Broccolo"
    }
}