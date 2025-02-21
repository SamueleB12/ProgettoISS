package com.ProgettoISS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.util.ArrayList;

public class Salone extends Canvas implements Runnable, KeyListener {
    private BufferedImage sfondo;
    private BufferedImage personaggio;
    private Camera camera;
    private Giocatore giocatore;
    private int posX, posY;

    private ArrayList<Rectangle> collisioni;
    private boolean running = false;

    public Salone(JFrame finestra) {
        caricaRisorse();
        impostaCollisioni();
        addKeyListener(this); // ✅ Usa la stessa classe come KeyListener
        setFocusable(true);
        this.setPreferredSize(new Dimension(1080, 720));
        this.setBounds(0, 0, 1080, 720);
        camera = new Camera(sfondo.getWidth(), sfondo.getHeight(), 1080, 720, 1.5);
        avvia(finestra);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        createBufferStrategy(2); // Usa doppio buffering
    }

    private void caricaRisorse() {
        try {
            // Caricamento della nuova mappa del salone
            InputStream sfondoStream = getClass().getClassLoader().getResourceAsStream("maps/Salone.png");
            if (sfondoStream == null) {
                throw new IOException("File non trovato: maps/Salone.png");
            }
            sfondo = ImageIO.read(sfondoStream);

            personaggio = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Immagini/detectivErn.png"));

        } catch (IOException e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
        }
    }

    private void impostaCollisioni() {
        collisioni = new ArrayList<>();
        collisioni.add(new Rectangle(100, 100, 50, 50)); // Esempio di collisione nel salone
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                posY -= 3;
                break;
            case KeyEvent.VK_DOWN:
                posY += 3;
                break;
            case KeyEvent.VK_LEFT:
                posX -= 3;
                break;
            case KeyEvent.VK_RIGHT:
                posX += 3;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void avvia(JFrame finestra) {
        finestra.getContentPane().removeAll();
        finestra.add(this);
        finestra.revalidate();
        finestra.repaint();
        startGameLoop();
    }

    private void startGameLoop() {
        running = true;
        Thread gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    public void run() {
        while (running) {
            update();
            disegna(); // ✅ Usa il metodo corretto per disegnare
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        camera.update(posX, posY);
    }

    private void disegna() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) return;

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        // Disegna l'immagine del salone centrata
        if (sfondo != null) {
            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
        }

        if (personaggio != null) {
            g.drawImage(personaggio, posX, posY, this);
        }

        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void paint(Graphics g) {
        disegna(); // ✅ Usa il metodo `disegna()`
    }
}