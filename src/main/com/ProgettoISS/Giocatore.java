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
    private String stanzaAttuale; // Aggiunto per gestire la mappa/stanza attuale



    // Costruttore privato per impedire istanziazione esterna
    private Giocatore(String nome) {
        this.nome = nome;
        this.posizioneX = 784; // Inizializza la posizione X a 0
        this.posizioneY = 1000; // Inizializza la posizione Y a 0
        this.oggetti = new ArrayList<>(); // Inizializza la lista degli oggetti
        this.diario = new Diario(); // Inizializza il diario
        this.stanzaAttuale = "StanzaIniziale"; // Stanza di default, si deve aggiungere
        this.inventario = new Inventario();

    }

    // Metodo per ottenere l'istanza del giocatore
    public static Giocatore getInstance() {  // ✅ Rimuovi il parametro String nome
        if (instance == null) {
            instance = new Giocatore("Detective"); // ✅ Il nome è predefinito e NON può essere modificato
        }
        return instance;
    }

    public Inventario getInventario() {
        if (this.inventario == null) {
            this.inventario = new Inventario(); // ✅ Se è null, lo inizializziamo
        }
        return this.inventario;
    }

    public Diario getDiario() {
        if (this.diario == null) {
            this.diario = new Diario(); // ✅ Se è null, lo inizializziamo
        }
        return this.diario;
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

    public void setOggetti(List<Oggetto> nuoviOggetti) { this.oggetti = new ArrayList<>(nuoviOggetti); }



    public void setPosizioneX(int posizioneX) { this.posizioneX = posizioneX; }

    public void setPosizioneY(int posizioneY) { this.posizioneY = posizioneY; }

    // ✅ Getter e setter per la stanza attuale
    public String getStanzaAttuale() { return stanzaAttuale; }

    public void setStanzaAttuale(String stanzaAttuale) { this.stanzaAttuale = stanzaAttuale; }

    // ✅ Salvataggio stato
    public GiocatoreMemento salvaStato() { return new GiocatoreMemento(this); }

    // ✅ Ripristino stato
    public void ripristinaStato(GiocatoreMemento memento) {
        this.posizioneX = memento.getPosizioneX();
        this.posizioneY = memento.getPosizioneY();

        // ✅ Verifica e inizializza l'inventario prima di ripristinarlo
        if (memento.getInventarioMemento() != null) {
            this.getInventario().ripristinaStato(memento.getInventarioMemento());
        }

        // ✅ Verifica e inizializza il diario prima di ripristinarlo
        if (memento.getDiarioMemento() != null) {
            this.getDiario().ripristinaStato(memento.getDiarioMemento());
        }
    }

    public static void ripristinaIstanza(Giocatore giocatoreCaricato) {
        if (instance == null) {
            instance = giocatoreCaricato; // Se non esiste, assegna il nuovo giocatore caricato
        } else {
            // ✅ Assicura che tutti gli attributi vengano aggiornati
            instance.posizioneX = giocatoreCaricato.getPosizioneX();
            instance.posizioneY = giocatoreCaricato.getPosizioneY();
            instance.nome = giocatoreCaricato.getNome();

            // ✅ Controlla che l'inventario non sia null
            if (giocatoreCaricato.getInventario() != null) {
                instance.inventario = giocatoreCaricato.getInventario();
            } else {
                instance.inventario = new Inventario(); // Evita che sia null
            }

            // ✅ Controlla che il diario non sia null
            if (giocatoreCaricato.getDiario() != null) {
                instance.diario = giocatoreCaricato.getDiario();
            } else {
                instance.diario = new Diario();
            }
        }
    }

    public void setInventario(Inventario inventario) {
        if (inventario != null) {
            this.inventario = inventario;
        } else {
            System.err.println("⚠️ Attenzione: Tentativo di impostare un inventario null.");
        }
    }

    public void setDiario(Diario diario) {
        if (diario != null) {
            this.diario = diario;
        } else {
            System.err.println("⚠️ Attenzione: Tentativo di impostare un diario null.");
        }
    }


}