package com.ProgettoISS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GiocatoreMementoTest {
    private Giocatore giocatore;

    @BeforeEach
    void setUp() {
        // 🔹 Crea un'istanza del giocatore
        giocatore = Giocatore.getInstance();
        giocatore.setPosizioneX(100);
        giocatore.setPosizioneY(200);
    }

    @Test
    void testSalvataggioERipristinoGiocatore() {
        // 🔹 Salva lo stato attuale
        GiocatoreMemento memento = giocatore.salvaStato();

        // 🔹 Modifica i valori del giocatore (simuliamo un cambio di stato)
        giocatore.setPosizioneX(500);
        giocatore.setPosizioneY(600);

        // 🔹 Ripristina lo stato salvato
        giocatore.ripristinaStato(memento);

        // 🔹 Verifica che il nome sia invariato
        assertEquals("Detective", giocatore.getNome(), "Il nome del giocatore non dovrebbe cambiare.");

        // 🔹 Verifica che la posizione sia stata ripristinata correttamente
        assertEquals(100, giocatore.getPosizioneX(), "La posizione X non è stata ripristinata correttamente.");
        assertEquals(200, giocatore.getPosizioneY(), "La posizione Y non è stata ripristinata correttamente.");
    }

    @Test
    void testSalvataggioGiocatoreConPosizioneZero() {
        // 🔹 Imposta la posizione a (0,0) e salva
        giocatore.setPosizioneX(0);
        giocatore.setPosizioneY(0);
        GiocatoreMemento memento = giocatore.salvaStato();

        // 🔹 Modifica la posizione
        giocatore.setPosizioneX(300);
        giocatore.setPosizioneY(400);

        // 🔹 Ripristina lo stato originale
        giocatore.ripristinaStato(memento);

        // 🔹 Verifica che la posizione sia tornata a (0,0)
        assertEquals(0, giocatore.getPosizioneX(), "La posizione X dovrebbe essere 0.");
        assertEquals(0, giocatore.getPosizioneY(), "La posizione Y dovrebbe essere 0.");
    }

    @Test
    void testSalvataggioGiocatoreConPosizioneNegativa() {
        // 🔹 Imposta una posizione negativa e salva
        giocatore.setPosizioneX(-50);
        giocatore.setPosizioneY(-75);
        GiocatoreMemento memento = giocatore.salvaStato();

        // 🔹 Modifica la posizione
        giocatore.setPosizioneX(999);
        giocatore.setPosizioneY(999);

        // 🔹 Ripristina lo stato originale
        giocatore.ripristinaStato(memento);

        // 🔹 Verifica che la posizione sia tornata ai valori negativi originali
        assertEquals(-50, giocatore.getPosizioneX(), "La posizione X dovrebbe essere -50.");
        assertEquals(-75, giocatore.getPosizioneY(), "La posizione Y dovrebbe essere -75.");
    }
}
