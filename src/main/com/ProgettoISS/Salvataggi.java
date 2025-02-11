package com.ProgettoISS;

import javax.swing.*;
import java.io.*;

public class Salvataggi {

    /**
     * Salva lo stato del gioco su uno dei tre file di salvataggio.
     * @param giocatore Il giocatore di cui salvare lo stato.
     * @param numeroSalvataggio Il numero del salvataggio (1, 2, 3).
     */


    /**
     * Carica lo stato del gioco da uno dei tre file di salvataggio.
     * @param giocatore Il giocatore su cui applicare il salvataggio.
     * @param numeroSalvataggio Il numero del salvataggio (1, 2, 3).
     * @return True se il caricamento è riuscito, False altrimenti.
     */

    public static void salvaGioco(Giocatore giocatore, int numeroSalvataggio) {
        // ✅ Controllo che il giocatore non sia null
        if (giocatore == null) {
            JOptionPane.showMessageDialog(null, "Errore: Il giocatore è null, impossibile salvare!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Controllo che il giocatore abbia un inventario e un diario validi
        if (giocatore.getInventario() == null) {
            giocatore.setInventario(new Inventario()); // Se l'inventario è null, inizializzalo
        }
        if (giocatore.getDiario() == null) {
            giocatore.setDiario(new Diario("Obiettivo iniziale")); // Se il diario è null, inizializzalo
        }

        // ✅ Creazione della cartella "saves" se non esiste
        File directory = new File("saves");
        if (!directory.exists() && !directory.mkdir()) {
            JOptionPane.showMessageDialog(null, "Errore: Impossibile creare la cartella dei salvataggi!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String filePath = "saves/save" + numeroSalvataggio + ".dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            GiocatoreMemento memento = giocatore.salvaStato(); // ✅ Salva lo stato del giocatore
            oos.writeObject(memento);

            System.out.println("✅ Salvataggio completato in: " + filePath);
            System.out.println("📌 Dati salvati -> Posizione: X = " + giocatore.getPosizioneX() + ", Y = " + giocatore.getPosizioneY());

            JOptionPane.showMessageDialog(null, "Salvataggio completato!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il salvataggio: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            System.err.println("❌ Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public static boolean caricaGioco(Giocatore giocatore, int slot) {
        String filePath = "saves/save" + slot + ".dat";
        File file = new File(filePath);

        // 🔹 Controlla se il file esiste
        if (!file.exists()) {
            System.out.println("❌ Nessun file di salvataggio trovato. Creazione di un nuovo file...");
            salvaGioco(giocatore, slot);
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            System.out.println(" Caricando file da: " + filePath);

            GiocatoreMemento memento = (GiocatoreMemento) ois.readObject();

            // ✅ Ripristina lo stato nel Singleton Giocatore
            giocatore = Giocatore.getInstance();
            giocatore.ripristinaStato(memento);

            System.out.println(" Caricamento completato dallo Slot " + slot);
            System.out.println(" Posizione ripristinata: X = " + giocatore.getPosizioneX() + ", Y = " + giocatore.getPosizioneY());

            return true;
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            System.err.println(" Errore durante il caricamento: " + e.getMessage());
            return false;
        }
    }

}
