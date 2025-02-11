package com.ProgettoISS;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class LancettaOrologio extends Oggetto {

    public LancettaOrologio(String nome, String descrizione, BufferedImage icona, int x, int y) {
        super(nome, descrizione, icona, true, x, y);
    }


    @Override
    public void interagisci() {
        System.out.println("Hai trovato una lancetta dell'orologio!");
        // Aggiungi qui la logica specifica per l'interazione con la lancetta dell'orologio
    }
}