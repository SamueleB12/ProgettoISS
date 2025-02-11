package com.ProgettoISS;

import java.awt.image.BufferedImage;

public class Broccolo extends Oggetto {

    public Broccolo(String nome, String descrizione, BufferedImage icona, int x, int y) {
        super(nome, descrizione, icona, true, x, y);
    }

    @Override
    public void interagisci() {
        System.out.println("Questo è un broccolo!");
        // Aggiungi qui la logica specifica per l'interazione con il broccolo
    }
}