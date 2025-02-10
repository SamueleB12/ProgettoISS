package com.ProgettoISS;

import java.util.ArrayList;
import java.util.List;

public class Diario {
    private List<String> enigmiCompletati;
    private List<String> enigmiNonCompletati;
    private String obiettivoPrincipale;
    private List<String> missioniPrincipali;
    private List<String> missioniSecondarie;

    // Costruttore senza argomenti (default constructor)
    public Diario() {
        this.obiettivoPrincipale = "Obiettivo non definito";
        this.enigmiCompletati = new ArrayList<>();
        this.enigmiNonCompletati = new ArrayList<>();
        this.missioniPrincipali = new ArrayList<>();
        this.missioniSecondarie = new ArrayList<>();
    }

    // Costruttore con un argomento
    public Diario(String obiettivoPrincipale) {
        this.obiettivoPrincipale = obiettivoPrincipale;
        this.enigmiCompletati = new ArrayList<>();
        this.enigmiNonCompletati = new ArrayList<>();
        this.missioniPrincipali = new ArrayList<>();
        this.missioniSecondarie = new ArrayList<>();
    }

    public void aggiungiEvento(String evento) {
        enigmiCompletati.add(evento); // Puoi adattarlo alla tua logica
    }

    public List<String> getEventi() {
        return enigmiCompletati; // Puoi adattarlo alla tua logica
    }

    public void aggiungiEnigmaCompletato(String enigma) {
        if (!enigmiCompletati.contains(enigma)) {
            enigmiCompletati.add(enigma);
        }
    }

    public void aggiungiEnigmaNonCompletato(String enigma) {
        if (!enigmiNonCompletati.contains(enigma)) {
            enigmiNonCompletati.add(enigma);
        }
    }

    public void aggiornaObiettivoPrincipale(String nuovoObiettivo) {
        this.obiettivoPrincipale = nuovoObiettivo;
    }

    public void aggiungiMissionePrincipale(String missione) {
        missioniPrincipali.add(missione);
    }

    public void aggiungiMissioneSecondaria(String missione) {
        missioniSecondarie.add(missione);
    }

    public String visualizzaDiario() {
        StringBuilder diario = new StringBuilder();
        diario.append("Obiettivo Principale: ").append(obiettivoPrincipale).append("\n\n");

        diario.append("Enigmi Completati:\n");
        for (String enigma : enigmiCompletati) {
            diario.append("- ").append(enigma).append("\n");
        }

        diario.append("\nEnigmi Non Completati:\n");
        for (String enigma : enigmiNonCompletati) {
            diario.append("- ").append(enigma).append("\n");
        }

        diario.append("\nMissioni Principali:\n");
        for (String missione : missioniPrincipali) {
            diario.append("- ").append(missione).append("\n");
        }

        diario.append("\nMissioni Secondarie:\n");
        for (String missione : missioniSecondarie) {
            diario.append("- ").append(missione).append("\n");
        }

        return diario.toString();
    }
}