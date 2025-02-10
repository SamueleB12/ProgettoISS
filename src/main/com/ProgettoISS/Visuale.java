package com.ProgettoISS;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Visuale extends JPanel {

   private Image immagineMappa;  
   private int vistaX;  
   private int vistaY;
   private final int larghezzaVista;
   private final int altezzaVista;
   private List<GiocatoreObserver> observers = new ArrayList<>();

    private Gioco gioco;

    public Visuale(String percorsoImmagine, int larghezzaVista, int altezzaVista, Gioco gioco) {
        this.larghezzaVista = larghezzaVista;
        this.altezzaVista = altezzaVista;
        this.gioco = gioco;

        ImageIcon icona = new ImageIcon(percorsoImmagine);
        this.immagineMappa = icona.getImage();

        this.vistaX = 0;
        this.vistaY = 0;

        setPreferredSize(new Dimension(larghezzaVista, altezzaVista));
    }

    public Gioco getGioco() {
        return gioco;
    }


    public void addObserver(GiocatoreObserver observer) {
       observers.add(observer);
   }

   public void notifyObservers() {
       for (GiocatoreObserver observer : observers) {
           observer.update(vistaX, vistaY);
       }
   }

   public void aggiornaVista(int posizioneGiocatoreX, int posizioneGiocatoreY) {
       int metaLarghezza = larghezzaVista / 2;
       int metaAltezza = altezzaVista / 2;

       vistaX = Math.max(0, Math.min(posizioneGiocatoreX - metaLarghezza, immagineMappa.getWidth(null) - larghezzaVista));
       vistaY = Math.max(0, Math.min(posizioneGiocatoreY - metaAltezza, immagineMappa.getHeight(null) - altezzaVista));

       notifyObservers();
       repaint();
   }

   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawImage(immagineMappa, 0, 0, larghezzaVista, altezzaVista,
                   vistaX, vistaY, vistaX + larghezzaVista, vistaY + altezzaVista, null);
   }
}