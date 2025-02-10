package com.ProgettoISS;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private static Giocatore instance; // Istanza singleton
    private String nome; // Nome del giocatore
    private int posizioneX; // Posizione X del giocatore
    private int posizioneY; // Posizione Y del giocatore
    private List<Oggetto> oggetti; // Lista degli oggetti raccolti dal giocatore
    private Diario diario; // Diario per tenere traccia degli eventi
    private Inventario inventario;


    // Costruttore privato per impedire istanziazione esterna
    private Giocatore(String nome) {
        this.nome = nome;
        this.posizioneX = 0; // Inizializza la posizione X a 0
        this.posizioneY = 0; // Inizializza la posizione Y a 0
        this.oggetti = new ArrayList<>(); // Inizializza la lista degli oggetti
        this.diario = new Diario(); // Inizializza il diario
    }

    // Metodo per ottenere l'istanza del giocatore
    public static Giocatore getInstance(String nome) {
        if (instance == null) {
            instance = new Giocatore(nome); // Crea un'istanza se non esiste
        }
        return instance; // Restituisce l'istanza del giocatore
    }

    public String getNome() {
        return nome; // Restituisce il nome del giocatore
    }

    public int getPosizioneX() {
        return this.posizioneX; // Restituisce la posizione X del giocatore
    }

    public int getPosizioneY() {
        return this.posizioneY; // Restituisce la posizione Y del giocatore
    }

    public void sposta(int deltaX, int deltaY) {
        // Controllo dei limiti della mappa (esempio con limiti fittizi)
        if (posizioneX + deltaX >= 0 && posizioneX + deltaX <= 1280) { 
            posizioneX += deltaX; // Aggiorna la posizione X se è valida
        }
        if (posizioneY + deltaY >= 0 && posizioneY + deltaY <= 720) { 
            posizioneY += deltaY; // Aggiorna la posizione Y se è valida
        }
    }

    public void aggiungiOggetto(Oggetto oggetto) {
        oggetti.add(oggetto); // Aggiunge un oggetto alla lista degli oggetti raccolti
        diario.aggiungiEvento("Hai raccolto: " + oggetto.getNome()); // Aggiunge un evento al diario
    }

    public List<Oggetto> getOggetti() {
        return oggetti; // Restituisce la lista degli oggetti raccolti dal giocatore
    }

    public Diario getDiario() {
        return diario; // Restituisce il diario del giocatore
    }

    public Inventario getInventario() {return inventario;}
}