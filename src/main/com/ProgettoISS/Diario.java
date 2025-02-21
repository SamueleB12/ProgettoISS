package com.ProgettoISS;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Diario extends Oggetto {
    private List<String> enigmiCompletati;
    private List<String> enigmiNonCompletati;
    private String obiettivoPrincipale;
    private List<String> missioniPrincipali;
    private List<String> missioniSecondarie;
    private BufferedImage icona; // Icona del diario


    // Costruttore senza argomenti
   public Diario() {
        super("Diario", "Un diario per annotare missioni ed enigmi", null, false, 0, 0);
        this.obiettivoPrincipale = "Obiettivo non definito";
        this.enigmiCompletati = new ArrayList<>();
        this.enigmiNonCompletati = new ArrayList<>();
        this.missioniPrincipali = new ArrayList<>();
        this.missioniSecondarie = new ArrayList<>();
    }

    // Costruttore con un argomento
    public Diario(String obiettivoPrincipale, String percorsoIcona) {
        super("Diario", "Un diario per annotare missioni ed enigmi", caricaImmagine(percorsoIcona), false, 0, 0);
        this.obiettivoPrincipale = obiettivoPrincipale;
        this.enigmiCompletati = new ArrayList<>();
        this.enigmiNonCompletati = new ArrayList<>();
        this.missioniPrincipali = new ArrayList<>();
        this.missioniSecondarie = new ArrayList<>();
        try {
            this.icona = ImageIO.read(getClass().getResource(percorsoIcona));
        } catch (IOException e) {
            System.out.println("Errore caricamento immagine");
        }
    }

    private static BufferedImage caricaImmagine(String percorsoIcona) {
        try {
            URL urlIcona = Diario.class.getResource(percorsoIcona);
            if (urlIcona == null) {
                System.err.println("Immagine non trovata: " + percorsoIcona);
                return null;
            }
            return ImageIO.read(urlIcona);
        } catch (IOException e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
            return null;
        }
    }

    // ✅ Getter per ogni variabile
    public List<String> getEnigmiCompletati() {
        return new ArrayList<>(enigmiCompletati);
    }

    public List<String> getEnigmiNonCompletati() {
        return new ArrayList<>(enigmiNonCompletati);
    }

    public String getObiettivoPrincipale() {
        return obiettivoPrincipale;
    }

    public List<String> getMissioniPrincipali() {
        return new ArrayList<>(missioniPrincipali);
    }

    public List<String> getMissioniSecondarie() {
        return new ArrayList<>(missioniSecondarie);
    }

    // ✅ Setter per ogni variabile
    public void setEnigmiCompletati(List<String> enigmiCompletati) {
        this.enigmiCompletati = new ArrayList<>(enigmiCompletati);
    }

    public void setEnigmiNonCompletati(List<String> enigmiNonCompletati) {
        this.enigmiNonCompletati = new ArrayList<>(enigmiNonCompletati);
    }

    public void setObiettivoPrincipale(String obiettivoPrincipale) {
        this.obiettivoPrincipale = obiettivoPrincipale;
    }

    public void setMissioniPrincipali(List<String> missioniPrincipali) {
        this.missioniPrincipali = new ArrayList<>(missioniPrincipali);
    }

    public void setMissioniSecondarie(List<String> missioniSecondarie) {
        this.missioniSecondarie = new ArrayList<>(missioniSecondarie);
    }

    // ✅ Metodi per aggiungere singoli elementi
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

    public void aggiungiEvento(String evento) {
        enigmiCompletati.add(evento); // Oppure potresti scegliere un'altra lista o struttura per gli eventi
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

    // ✅ Funzioni per il Memento
    public DiarioMemento salvaStato() {
        return new DiarioMemento(this);
    }

    public void ripristinaStato(DiarioMemento memento) {
        memento.ripristina(this);
    }

    /*@Override
    public BufferedImage getIcona() {
        return icona;
    }*/

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