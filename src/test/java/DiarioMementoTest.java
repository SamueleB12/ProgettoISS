package com.ProgettoISS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class DiarioMementoTest {
    private Diario diario;

    @BeforeEach
    void setUp() {
        diario = new Diario(); // Inizializza un diario nuovo prima di ogni test
    }

    @Test
    void testSalvataggioERipristinoDiario() {
        // 🔹 Aggiunge alcune voci al diario
        diario.aggiungiEvento("Evento 1: Indizio trovato");
        diario.aggiungiEvento("Evento 2: Porta aperta");
        diario.aggiungiEvento("Evento 3: Oggetto raccolto");

        // 🔹 Salva lo stato attuale del diario
        DiarioMemento memento = diario.salvaStato();

        // 🔹 Crea un nuovo diario e ripristina lo stato salvato
        Diario nuovoDiario = new Diario();
        nuovoDiario.ripristinaStato(memento);

        // 🔹 Verifica che il numero di eventi sia identico
        List<String> eventiRipristinati = nuovoDiario.getEnigmiCompletati();
        List<String> eventiOriginali = Arrays.asList(
                "Evento 1: Indizio trovato",
                "Evento 2: Porta aperta",
                "Evento 3: Oggetto raccolto"
        );
        assertEquals(eventiOriginali, eventiRipristinati, "Gli eventi ripristinati non corrispondono a quelli salvati.");
    }

    @Test
    void testSalvataggioDiarioVuoto() {
        // 🔹 Salva lo stato di un diario vuoto
        DiarioMemento memento = diario.salvaStato();

        // 🔹 Ripristina lo stato in un altro diario
        Diario nuovoDiario = new Diario();
        nuovoDiario.ripristinaStato(memento);

        // 🔹 Verifica che il diario ripristinato sia vuoto
        assertTrue(nuovoDiario.getEnigmiCompletati().isEmpty(), "Il diario ripristinato dovrebbe essere vuoto.");
    }
}
