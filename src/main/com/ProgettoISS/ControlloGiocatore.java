package com.ProgettoISS;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ControlloGiocatore extends KeyAdapter {

    private Gioco gioco;
    private Giocatore giocatore;
    private Visuale visuale;
    private static final int GRID_SIZE = 50; // Dimensione delle celle nella griglia
    private Diario diario;

    public ControlloGiocatore(Gioco gioco, Giocatore giocatore, Visuale visuale, Diario diario) {
        this.gioco = gioco;
        this.giocatore = giocatore;
        this.visuale = visuale;
        this.diario = diario;
        diario = new Diario(); // Inizializza il diario
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                muoviGiocatore(0, -GRID_SIZE);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                muoviGiocatore(0, GRID_SIZE);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                muoviGiocatore(-GRID_SIZE, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                muoviGiocatore(GRID_SIZE, 0);
                break;
            case KeyEvent.VK_Z:
                interagisciConOggetto();
                break;
        }
    }

    private void muoviGiocatore(int deltaX, int deltaY) {
        int nuovaPosizioneX = giocatore.getPosizioneX() + deltaX;
        int nuovaPosizioneY = giocatore.getPosizioneY() + deltaY;

        if (puoMuoversi(nuovaPosizioneX / GRID_SIZE, nuovaPosizioneY / GRID_SIZE)) {
            giocatore.sposta(deltaX, deltaY);
            visuale.aggiornaVista(giocatore.getPosizioneX(), giocatore.getPosizioneY());
        }
    }

    private boolean puoMuoversi(int cellaX, int cellaY) {
        // Ottieni la mappa dal gioco
        int[][] mappa = visuale.getGioco().getMappa();

        // Controlla se le coordinate sono fuori dai limiti della mappa
        if (cellaX < 0 || cellaY < 0 || cellaX >= mappa[0].length || cellaY >= mappa.length) {
            return false; // Fuori dai limiti
        }

        // Controlla se la cella è libera (valore 0)
        return mappa[cellaY][cellaX] == 0;
    }

    private void interagisciConOggetto() {
        for (Oggetto oggetto : getOggettiNelleVicinanze()) {
            if (oggetto.isInterattivo()) {
                oggetto.interagisci();
                diario.aggiungiEnigmaCompletato(oggetto.getNome()); // Aggiorna il diario
                gioco.mostraDiario(); // Mostra automaticamente il diario
                // Aggiorna il diario quando interagisci con un oggetto
                diario.aggiungiEnigmaCompletato("Enigma risolto");
                System.out.println(diario.visualizzaDiario());
                return;
            }
        }
    }

    private List<Oggetto> getOggettiNelleVicinanze() {
        List<Oggetto> oggettiVicini = new ArrayList<>();

        // Logica per ottenere gli oggetti nelle vicinanze

        return oggettiVicini;
    }
}