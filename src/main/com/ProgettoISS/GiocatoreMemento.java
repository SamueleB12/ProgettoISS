package com.ProgettoISS;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class GiocatoreMemento implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final int posizioneX;
    private final int posizioneY;
    private final List<Oggetto> oggetti;
    private final DiarioMemento diarioMemento;
    private final InventarioMemento inventarioMemento;
    private final String stanzaAttuale; // Nuovo campo per salvare la mappa/stanza attuale

    public GiocatoreMemento(Giocatore giocatore) {
        this.nome = giocatore.getNome();
        this.posizioneX = giocatore.getPosizioneX(); // Usa il getter
        this.posizioneY = giocatore.getPosizioneY(); // Usa il getter
        this.oggetti = new ArrayList<>(giocatore.getOggetti()); // Usa il getter
        this.diarioMemento = giocatore.getDiario().salvaStato();
        this.inventarioMemento = giocatore.getInventario().salvaStato();
        this.stanzaAttuale = giocatore.getStanzaAttuale(); // Aggiunto per salvare la stanza attuale
    }

    public void ripristina(Giocatore giocatore) {
        if (giocatore == null) {
            throw new IllegalArgumentException("Giocatore non può essere null!");
        }

        giocatore.setPosizioneX(this.posizioneX); // Usa il setter
        giocatore.setPosizioneY(this.posizioneY); // Usa il setter
        giocatore.setOggetti(new ArrayList<>(this.oggetti)); // Usa il setter
        if (this.diarioMemento != null) {
            giocatore.getDiario().ripristinaStato(this.diarioMemento);
        }
        if (this.inventarioMemento != null) {
            giocatore.getInventario().ripristinaStato(this.inventarioMemento);
        }
        giocatore.setStanzaAttuale(this.stanzaAttuale); // Aggiunto per ripristinare la stanza
    }
    // ✅ Metodo per ottenere la posizione X salvata
    public int getPosizioneX() {
        return posizioneX;
    }

    // ✅ Metodo per ottenere la posizione Y salvata
    public int getPosizioneY() {
        return posizioneY;
    }

    // ✅ Metodo per ottenere lo stato dell'inventario salvato
    public InventarioMemento getInventarioMemento() {
        return inventarioMemento;
    }

    // ✅ Metodo per ottenere lo stato del diario salvato
    public DiarioMemento getDiarioMemento() {
        return diarioMemento;
    }
}
