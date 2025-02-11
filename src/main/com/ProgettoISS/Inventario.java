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

    public void setOggetti(List<Oggetto> nuoviOggetti) {
        this.oggetti = new ArrayList<>(nuoviOggetti);
    }

    public InventarioMemento salvaStato() {
        return new InventarioMemento(this);
    }

    public void ripristinaStato(InventarioMemento memento) {
        memento.ripristina(this);
    }
}