package com.ProgettoISS;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class VisualeTest {
    private Visuale visuale;
    private Gioco gioco; // Aggiungi un'istanza di Gioco

    @BeforeEach
    public void setUp() {
        gioco = new Gioco(); // Crea un'istanza del gioco
        visuale = new Visuale("percorso_mappa.png", 1280, 720, gioco); // Passa l'istanza di Gioco
    }

    @Test
    public void testDimensioniVista() {
        assertEquals(1280, visuale.getWidth()); // Verifica che la larghezza della vista sia corretta
        assertEquals(720, visuale.getHeight()); // Verifica che l'altezza della vista sia corretta
    }

    @Test
    public void testAggiornamentoVista() {
        visuale.aggiornaVista(300, 200); // Aggiorna la vista in base alla posizione del giocatore
        // Verifica le coordinate della vista (dovrai implementare metodi getter se non esistono)
        // assertEquals(....); // Aggiungi le asserzioni appropriate qui
    }
}