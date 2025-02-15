package com.ProgettoISS;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SchermataIniziale extends JPanel {

    private BufferedImage sfondo; // Immagine di sfondo

    public SchermataIniziale(JFrame finestra, Runnable avviaGioco) {
        // Carica l'immagine di sfondo
        caricaSfondo();

        // Configura il pannello principale
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Bottoni
        JButton nuovaPartitaBtn = creaBottone("Nuova Partita", e -> {
            Gioco gioco = new Gioco();
            gioco.avvia(finestra); // Passa la finestra principale al gioco
        });
        JButton caricaPartitaBtn = creaBottone("Carica Partita", e -> caricaPartita());
        JButton esciBtn = creaBottone("Esci", e -> System.exit(0));

        // Aggiungi bottoni al pannello
        add(Box.createVerticalGlue());
        add(nuovaPartitaBtn);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(caricaPartitaBtn);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(esciBtn);
        add(Box.createVerticalGlue());
    }

    private void caricaSfondo() {
        try {
            sfondo = ImageIO.read(getClass().getResource("/Immagini/L'eredità di Cincenzio.jpg"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Errore nel caricamento dello sfondo: " + e.getMessage());
            sfondo = null; // Fallback se non trova l'immagine
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sfondo != null) {
            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private JButton creaBottone(String testo, ActionListener azione) {
        JButton bottone = new JButton(testo);
        bottone.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottone.setFont(new Font("Arial", Font.BOLD, 24));
        bottone.setFocusPainted(false);
        bottone.setBackground(Color.DARK_GRAY);
        bottone.setForeground(Color.WHITE);
        bottone.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bottone.addActionListener(azione);
        return bottone;
    }

    private void caricaPartita() {
        JFrame finestraCaricamento = new JFrame("Seleziona Slot di Caricamento");
        JPanel pannello = new JPanel();
        pannello.setLayout(new GridLayout(3, 1));

        for (int i = 1; i <= 3; i++) {
            int slot = i;
            JButton slotButton = new JButton("Slot " + i);
            slotButton.addActionListener(e -> {
                Giocatore giocatore = Giocatore.getInstance();

                // Se il salvataggio esiste, carica il gioco
                if (Salvataggi.caricaGioco(giocatore, slot)) {
                    JOptionPane.showMessageDialog(finestraCaricamento, "Caricamento completato!");
                    finestraCaricamento.dispose();

                    // Avvia il gioco dopo il caricamento
                    Gioco gioco = new Gioco();
                    gioco.avvia((JFrame) SwingUtilities.getWindowAncestor(this));
                } else {
                    JOptionPane.showMessageDialog(finestraCaricamento, "Errore nel caricamento dello Slot " + slot, "Errore", JOptionPane.ERROR_MESSAGE);
                }
            });

            pannello.add(slotButton);


            finestraCaricamento.add(pannello);
            finestraCaricamento.setSize(300, 200);
            finestraCaricamento.setLocationRelativeTo(null);
            finestraCaricamento.setVisible(true);
        }

    }
}
