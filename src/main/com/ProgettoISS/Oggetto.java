package com.ProgettoISS;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Oggetto {

    private String nome;
    private String descrizione;
    private BufferedImage icona; // Icona dell'oggetto
    private boolean interattivo;
    private int x;
    private int y;
    private int larghezza, altezza; // Per la collisione

    public Oggetto(String nome, String descrizione, BufferedImage icona, boolean interattivo, int x, int y) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.icona = icona;
        this.interattivo = interattivo;
        this.x = x;
        this.y = y;
        this.larghezza = larghezza;
        this.altezza = altezza;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public BufferedImage getIcona() {
        return icona;
    }

    public boolean isInterattivo() {
        return interattivo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, larghezza, altezza);
    }

    public void interagisci() {
        if (interattivo) {
            System.out.println("Hai raccolto: " + nome);
            // Qui puoi aggiungere logica per rimuovere l'oggetto dalla mappa
            // e aggiornare il diario o eseguire altre azioni
        } else {
            System.out.println("Non puoi interagire con " + nome);
        }
    }
}