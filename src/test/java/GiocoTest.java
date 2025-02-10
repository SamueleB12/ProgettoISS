package com.ProgettoISS;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GiocoTest {
    private Gioco gioco;

    @BeforeEach
    public void setUp() {
        gioco = new Gioco(); // Crea una nuova istanza del gioco
    }

    @Test
    public void testInizializzazioneGioco() {
        assertNotNull(gioco); // Verifica che il gioco sia stato inizializzato
       // List<Oggetto> oggetti = gioco.getOggetti(); // Ottiene gli oggetti dal gioco
      //  assertEquals(3, oggetti.size()); // Verifica che ci siano 3 oggetti inizializzati (Chiave, Libro, Broccolo)
        
        // Verifica i nomi degli oggetti
       /* assertEquals("Chiave", oggetti.get(0).getNome());
        assertEquals("Libro", oggetti.get(1).getNome());
        assertEquals("Broccolo", oggetti.get(2).getNome());*/
    }
}