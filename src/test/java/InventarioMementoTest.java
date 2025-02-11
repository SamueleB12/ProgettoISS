package com.ProgettoISS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class InventarioMementoTest {
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario(); // Inizializza un inventario nuovo prima di ogni test
    }

    @Test
    void testSalvataggioERipristinoInventario() {
        // 🔹 Crea alcuni oggetti di test
        Oggetto oggetto1 = new Oggetto("Oggetto1", true, 5, 10);
        Oggetto oggetto2 = new Oggetto("Oggetto2", false, 3, 15);

        // 🔹 Aggiungi oggetti all'inventario
        inventario.aggiungiOggetto(oggetto1);
        inventario.aggiungiOggetto(oggetto2);

        // 🔹 Salva lo stato attuale dell'inventario
        InventarioMemento memento = inventario.salvaStato();

        // 🔹 Crea un nuovo inventario e ripristina lo stato salvato
        Inventario nuovoInventario = new Inventario();
        nuovoInventario.ripristinaStato(memento);

        // 🔹 Verifica che il numero di oggetti sia identico
        assertEquals(2, nuovoInventario.getOggetti().size(), "Il numero di oggetti ripristinati non è corretto.");

        // 🔹 Verifica che gli oggetti siano stati ripristinati correttamente
        List<Oggetto> oggettiRipristinati = nuovoInventario.getOggetti();
        List<Oggetto> oggettiOriginali = Arrays.asList(oggetto1, oggetto2);
        assertEquals(oggettiOriginali, oggettiRipristinati, "Gli oggetti ripristinati non corrispondono a quelli salvati.");
    }

    @Test
    void testSalvataggioInventarioVuoto() {
        // 🔹 Salva lo stato di un inventario vuoto
        InventarioMemento memento = inventario.salvaStato();

        // 🔹 Ripristina lo stato in un altro inventario
        Inventario nuovoInventario = new Inventario();
        nuovoInventario.ripristinaStato(memento);

        // 🔹 Verifica che l'inventario ripristinato sia vuoto
        assertTrue(nuovoInventario.getOggetti().isEmpty(), "L'inventario ripristinato dovrebbe essere vuoto.");
    }
}
