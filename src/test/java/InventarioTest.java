package com.ProgettoISS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {
    private Inventario inventario;
    private Oggetto chiave;
    private Oggetto libro;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        chiave = new Oggetto("Chiave d'oro","Una chiave dorata",null, true, 0, 0); // Usa i parametri corretti
        libro = new Oggetto("Libro antico", "Un libro rovinato",null ,true,2, 50);
    }

    @Test
    void testAggiuntaOggetto() {
        inventario.aggiungiOggetto(chiave);
        assertTrue(inventario.getOggetti().contains(chiave), "L'oggetto dovrebbe essere nell'inventario.");
    }

    @Test
    void testRimozioneOggetto() {
        inventario.aggiungiOggetto(libro);
        inventario.rimuoviOggetto(libro);
        assertFalse(inventario.getOggetti().contains(libro), "L'oggetto non dovrebbe più essere nell'inventario.");
    }

    @Test
    void testSalvataggioERipristino() {
        inventario.aggiungiOggetto(chiave);
        inventario.aggiungiOggetto(libro);

        InventarioMemento memento = inventario.salvaStato();
        Inventario nuovoInventario = new Inventario();
        nuovoInventario.ripristinaStato(memento);

        List<Oggetto> oggettiRipristinati = nuovoInventario.getOggetti();
        assertEquals(2, oggettiRipristinati.size(), "L'inventario ripristinato dovrebbe contenere due oggetti.");
        assertTrue(oggettiRipristinati.contains(chiave), "L'oggetto 'Chiave d'oro' dovrebbe essere presente.");
        assertTrue(oggettiRipristinati.contains(libro), "L'oggetto 'Libro antico' dovrebbe essere presente.");
    }

    @Test
    void testSetOggetti() {
        inventario.aggiungiOggetto(chiave);
        inventario.setOggetti(List.of(libro)); // Sostituisce gli oggetti

        List<Oggetto> oggetti = inventario.getOggetti();
        assertEquals(1, oggetti.size(), "Dovrebbe esserci un solo oggetto dopo il set.");
        assertEquals(libro, oggetti.get(0), "L'oggetto nell'inventario dovrebbe essere il libro.");
    }
}
