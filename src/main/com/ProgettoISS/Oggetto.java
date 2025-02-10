package com.ProgettoISS;

import java.awt.image.BufferedImage;

public class Oggetto {
    
    private String nome;  
    private boolean interattivo;  
    private int x;  
    private int y;
    private String descrizione;
    private BufferedImage icona; // Icona dell'oggetto


    public Oggetto(String nome, boolean interattivo, int x, int y) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.icona = icona;
        this.interattivo = interattivo;
        this.x = x;
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public boolean isInterattivo() {
        return interattivo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public BufferedImage getIcona() {
        return icona;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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