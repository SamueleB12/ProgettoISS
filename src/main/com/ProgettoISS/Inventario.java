package com.ProgettoISS;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Oggetto> oggetti;

    public Inventario() {
        this.oggetti = new ArrayList<>();
    }

    public void aggiungiOggetto(Oggetto oggetto) {
        oggetti.add(oggetto); // Aggiungi l'oggetto all'inventario
    }

    public void rimuoviOggetto(Oggetto oggetto) {
        oggetti.remove(oggetto); // Rimuovi l'oggetto dall'inventario
    }

    public List<Oggetto> getOggetti() {
        return oggetti; // Restituisci la lista degli oggetti
    }
}