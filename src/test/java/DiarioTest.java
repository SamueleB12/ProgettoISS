package com.ProgettoISS;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DiarioTest {
    private Diario diario;

    @BeforeEach
    public void setUp() {
        diario = new Diario(); // Crea una nuova istanza del diario
    }

    @Test
    public void testAggiungiEvento() {
        diario.aggiungiEvento("Hai raccolto un broccolo."); // Aggiunge un evento
        List<String> eventi = diario.getEventi(); // Ottiene gli eventi dal diario
        assertEquals(1, eventi.size()); // Verifica che ci sia un evento nel diario
        assertEquals("Hai raccolto un broccolo.", eventi.get(0)); // Verifica che l'evento sia corretto
    }

    @Test
    public void testEventiIniziali() {
        List<String> eventi = diario.getEventi(); // Ottiene gli eventi dal diario
        assertTrue(eventi.isEmpty()); // Verifica che il diario sia vuoto all'inizio
    }
}