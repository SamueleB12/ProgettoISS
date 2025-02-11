package com.ProgettoISS;

import java.awt.image.BufferedImage;

public class Chiave extends Oggetto {

    public Chiave(String nome, String descrizione, BufferedImage icona, int x, int y) {
        super(nome, descrizione, icona, true, x, y);
    }

    @Override
    public void interagisci() {
        System.out.println("Hai usato la chiave!");
        // Aggiungi qui la logica specifica per l'interazione con la chiave
    }
}