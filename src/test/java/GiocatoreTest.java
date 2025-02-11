package com.ProgettoISS;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GiocatoreTest {
    private Giocatore giocatore;

    @BeforeEach
    public void setUp() {
        giocatore = Giocatore.getInstance(); // Crea un'istanza del giocatore
    }

    @Test
    public void testInizializzazione() {
        assertEquals("TestPlayer", giocatore.getNome()); // Verifica il nome del giocatore
        assertEquals(0, giocatore.getPosizioneX()); // Verifica che la posizione X sia inizializzata a 0
        assertEquals(0, giocatore.getPosizioneY()); // Verifica che la posizione Y sia inizializzata a 0
        assertTrue(giocatore.getOggetti().isEmpty()); // Verifica che la lista degli oggetti sia vuota
    }

    @Test
    public void testSpostamento() {
        giocatore.sposta(50, 50); // Sposta il giocatore
        assertEquals(50, giocatore.getPosizioneX()); // Verifica la nuova posizione X
        assertEquals(50, giocatore.getPosizioneY()); // Verifica la nuova posizione Y

        giocatore.sposta(-100, -100); // Prova a spostare oltre il limite negativo
        assertEquals(0, giocatore.getPosizioneX()); // Verifica che non vada oltre il limite
        assertEquals(0, giocatore.getPosizioneY()); // Verifica che non vada oltre il limite
    }

    @Test
    public void testAggiungiOggetto() {
        Oggetto broccolo = new Oggetto("Broccolo", true, 300, 200);
        giocatore.aggiungiOggetto(broccolo); // Aggiunge un oggetto al giocatore
        
        List<Oggetto> oggetti = giocatore.getOggetti(); // Ottiene gli oggetti raccolti
        assertEquals(1, oggetti.size()); // Verifica che ci sia un oggetto nella lista
        assertEquals("Broccolo", oggetti.get(0).getNome()); // Verifica che l'oggetto aggiunto sia corretto
    }
}