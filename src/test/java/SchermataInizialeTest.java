package com.ProgettoISS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

class SchermataInizialeTest {
    private JFrame finestra;
    private SchermataIniziale schermataIniziale;

    @BeforeEach
    void setUp() {
        finestra = new JFrame();
        schermataIniziale = new SchermataIniziale(finestra, () -> {});
    }

    @Test
    void testPulsantiPresenti() {
        // Controlla che i pulsanti siano stati aggiunti correttamente alla UI
        Component[] components = schermataIniziale.getComponents();
        boolean nuovaPartitaTrovato = false;
        boolean caricaPartitaTrovato = false;
        boolean esciTrovato = false;

        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals("Nuova Partita")) nuovaPartitaTrovato = true;
                if (button.getText().equals("Carica Partita")) caricaPartitaTrovato = true;
                if (button.getText().equals("Esci")) esciTrovato = true;
            }
        }

        assertTrue(nuovaPartitaTrovato, "Il pulsante 'Nuova Partita' non è stato trovato.");
        assertTrue(caricaPartitaTrovato, "Il pulsante 'Carica Partita' non è stato trovato.");
        assertTrue(esciTrovato, "Il pulsante 'Esci' non è stato trovato.");
    }

    @Test
    void testCaricaPartitaApreFinestra() {
        // Simula il click sul pulsante "Carica Partita"
        JButton caricaPartitaBtn = trovaPulsante("Carica Partita");
        assertNotNull(caricaPartitaBtn, "Il pulsante 'Carica Partita' non è stato trovato.");

        // Usa Mockito per simulare l'azione del pulsante
        ActionListener[] listeners = caricaPartitaBtn.getActionListeners();
        assertTrue(listeners.length > 0, "Il pulsante 'Carica Partita' non ha listener associati.");

        // Simula il click
        listeners[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "click"));

        // Controlla se la finestra di caricamento si è aperta
        Window[] windows = Window.getWindows();
        boolean finestraCaricamentoAperta = false;
        for (Window window : windows) {
            if (window instanceof JFrame && ((JFrame) window).getTitle().equals("Seleziona Slot di Caricamento")) {
                finestraCaricamentoAperta = true;
                break;
            }
        }
        assertTrue(finestraCaricamentoAperta, "La finestra di caricamento non si è aperta.");
    }

    @Test
    /*void testSfondoCaricatoCorrettamente() {
        // Verifica che il metodo caricaSfondo abbia caricato un'immagine
        schermataIniziale.repaint();
        try {
            //BufferedImage sfondo = ImageIO.read(getClass().getResource("/immagini/L'eredità di Cincenzio.jpg"));
            //assertNotNull(sfondo, "L'immagine di sfondo non è stata caricata correttamente.");
        } catch (IOException e) {
            fail("Errore nel caricamento dell'immagine di sfondo: " + e.getMessage());
        }
    }*/

    // Metodo di supporto per trovare un pulsante specifico
    private JButton trovaPulsante(String nome) {
        for (Component comp : schermataIniziale.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals(nome)) {
                    return button;
                }
            }
        }
        return null;
    }
}
