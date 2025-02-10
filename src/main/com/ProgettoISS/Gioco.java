package com.ProgettoISS;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferStrategy;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import com.badlogic.gdx.*;

public class Gioco extends Canvas implements KeyListener {

    private Camera camera;
    private static final int LARGHEZZA = 1080;
    private static final int ALTEZZA = 720;
    private Diario diario;
    private static final int[][] MAPPA = {
            {0, 0, 0, 1, 0}, // 0 = spazio libero, 1 = muro
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1},
            {0, 0, 0, 0, 0}
    };
    private ArrayList<Rectangle> collisioni; // Aree di collisione

    private static final String NOME_GIOCO = "L'eredità di Cincenzio";
    private Giocatore giocatore;

    private BufferedImage sfondo = null;
    private BufferedImage personaggio = null; // Immagine del personaggio
    private int posX = 784; // Posizione X del personaggio
    private int posY = 1000; // Posizione Y del personaggio

    private BufferedImage[] spriteSu;
    private BufferedImage[] spriteGiu;
    private BufferedImage[] spriteSinistra;
    private BufferedImage[] spriteDestra;

    private int frameIndex = 0; // Indice per animazione
    private int frameDelay = 0; // Ritardo per cambiare frame
    private static final int FRAME_INTERVAL = 20; // Numero di update prima di cambiare frame
    private String direzione = "giu"; // Direzione attuale

    // Variabili per gestire il movimento
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;


    public Gioco() {
        caricaRisorse();
        impostaCollisioni(); // Aggiunge le zone di collisione
        addKeyListener(this);
        camera = new Camera(sfondo.getWidth(), sfondo.getHeight(), LARGHEZZA, ALTEZZA, 1.5);
        setFocusable(true); // Necessario per ricevere input da tastiera
        diario = new Diario("Recuperare l'oggetto di valore"); // Inizializza il diario con l'obiettivo principale
    }

    private void impostaCollisioni() {
        collisioni = new ArrayList<>();
        int dimensioneCollisione = 50;

        // Aggiunta delle collisioni corrette rispetto all'immagine
        collisioni.add(new Rectangle(1135, 874, 3, 3));
        collisioni.add(new Rectangle(0, 964, 740, 240));
        collisioni.add(new Rectangle(856, 964, 700, 240));
        collisioni.add(new Rectangle(0, -1, 250, 1000));
        collisioni.add(new Rectangle(0, -1, 2000, 250));
        collisioni.add(new Rectangle(1364, -1, 2000, 2000));
        collisioni.add(new Rectangle(482, 865, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(654, 903, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(596, 897, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(473, 861, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(495, 901, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(399, 898, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(304, 896, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(250, 832, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(250, 643, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(250, 512, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(330, 524, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(331, 650, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(256, 375, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(295, 333, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(401, 251, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(493, 251, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(593, 251, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(693, 251, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(717, 313, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(745, 295, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(812, 272, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(814, 334, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(879, 250, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(972, 250, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1068, 250, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1105, 328, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1299, 250, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1189, 522, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1289, 522, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1194, 649, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1286, 649, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1235, 895, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1291, 907, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1038, 897, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(943, 894, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(879, 896, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(1132, 892, dimensioneCollisione, dimensioneCollisione));
        collisioni.add(new Rectangle(252, 0, 3000, 690));
        collisioni.add(new Rectangle(380, 690, 380, 70));
        collisioni.add(new Rectangle(836, 690, 380, 70));



    }

    public void avvia(JFrame finestra) {
        // Rimuovi tutti i componenti esistenti dalla finestra principale
        finestra.getContentPane().removeAll();

        // Crea un pannello per il pulsante "Apri Diario" e lo aggiunge alla finestra
        JPanel pannelloSuperiore = new JPanel(new BorderLayout());
        JButton bottoneDiario = new JButton("Apri Diario");
        bottoneDiario.addActionListener(e -> mostraDiario()); // Mostra il diario quando clicchi sul pulsante
        pannelloSuperiore.add(bottoneDiario, BorderLayout.WEST);

        finestra.add(pannelloSuperiore, BorderLayout.NORTH); // Aggiungi il pannello superiore
        finestra.add(this, BorderLayout.CENTER); // Aggiungi il pannello del gioco

        finestra.revalidate(); // Aggiorna il layout della finestra
        finestra.repaint(); // Forza il ridisegno

        this.createBufferStrategy(2); // Crea una strategia a doppio buffer

        startGameLoop(); // Avvia il ciclo di gioco
    }

    private void caricaRisorse() {
        try {
            // Caricamento dello sfondo
            InputStream sfondoStream = getClass().getClassLoader().getResourceAsStream("maps/EsternoMagione.png");
            if (sfondoStream == null) {
                throw new IOException("File non trovato: maps/EsternoMagione.png");
            }
            sfondo = ImageIO.read(sfondoStream);

            // Caricamento degli sprite
            spriteGiu = new BufferedImage[]{
                    ImageIO.read(requireStream("Immagini/detectivErn.png")),
                    ImageIO.read(requireStream("Immagini/detectivMov1.png")),
                    ImageIO.read(requireStream("Immagini/detectivMov2.png"))
            };

            spriteSu = new BufferedImage[]{
                    ImageIO.read(requireStream("Immagini/Detective gioco dietro fermo.png")),
                    ImageIO.read(requireStream("Immagini/Detective gioco dietro mov 1.png")),
                    ImageIO.read(requireStream("Immagini/Detective gioco dietro mov 2.png"))
            };

            spriteSinistra = new BufferedImage[]{
                    ImageIO.read(requireStream("Immagini/detectivErn_side 1.png")),
                    ImageIO.read(requireStream("Immagini/detectivErn_side-2.png")),
                    ImageIO.read(requireStream("Immagini/detectivErn_side-3.png"))
            };

            spriteDestra = new BufferedImage[]{
                    ImageIO.read(requireStream("Immagini/detectivErn_rightside-1.png")),
                    ImageIO.read(requireStream("Immagini/detectivErn_rightside-2.png")),
                    ImageIO.read(requireStream("Immagini/detectivErn_rightside-4.png"))
            };

            personaggio = spriteGiu[0]; // Carica l'immagine del personaggio

        } catch (IOException e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
        }
    }

    private boolean verificaCollisione(int x, int y) {
        Rectangle giocatore = new Rectangle(x, y, personaggio.getWidth(), personaggio.getHeight());
        for (Rectangle r : collisioni) {
            if (giocatore.intersects(r)) {
                return true; // Collisione trovata
            }
        }
        return false; // Nessuna collisione
    }

    // Metodo helper per evitare duplicazione del codice
    private InputStream requireStream(String path) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) {
            throw new IOException("File non trovato: " + path);
        }
        return is;
    }



    public int[][] getMappa() {
        return MAPPA;
    }

    private void disegna() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) return;

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, LARGHEZZA, ALTEZZA);

        double zoom = camera.getZoom();

        // Applica la scala per lo zoom
        g.scale(zoom, zoom);
        int cameraX = camera.getCameraX();
        int cameraY = camera.getCameraY();

        // Disegna la mappa con offset della camera
        if (sfondo != null) {
            g.drawImage(sfondo, -cameraX, -cameraY, sfondo.getWidth(), sfondo.getHeight(), this);
        }

        // Disegna il personaggio nella posizione relativa alla camera
        if (personaggio != null) {
            g.drawImage(personaggio, (posX - cameraX), (posY - cameraY), this);
        }

        // Disegna le collisioni per il debug
        g.setColor(new Color(255, 0, 0, 100)); // Rosso trasparente
        for (Rectangle r : collisioni) {
            g.fillRect(r.x - cameraX, r.y - cameraY, r.width, r.height);
        }

        g.dispose();
        bufferStrategy.show();
    }

    public void mostraDiario() {
        JFrame finestraDiario = new JFrame("Diario di Bordo");
        JTextArea areaTesto = new JTextArea(diario.visualizzaDiario());

        areaTesto.setEditable(false); // Rendi l'area di testo non modificabile
        areaTesto.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Imposta un font monospaziato

        JScrollPane scrollPane = new JScrollPane(areaTesto);

        finestraDiario.add(scrollPane);
        finestraDiario.setSize(400, 600);
        finestraDiario.setLocationRelativeTo(null); // Centra la finestra
        finestraDiario.setVisible(true);
    }

    public void mostraInventario() {
        JFrame finestraInventario = new JFrame("Inventario");
        JPanel pannello = new JPanel();
        pannello.setLayout(new BoxLayout(pannello, BoxLayout.Y_AXIS));

        // Ottieni l'inventario del giocatore
        Inventario inventario = giocatore.getInventario();

        // Aggiungi ogni oggetto all'inventario
        for (Oggetto oggetto : inventario.getOggetti()) {
            JLabel etichetta = new JLabel(oggetto.getNome()); // Nome dell'oggetto
            pannello.add(etichetta);

            // Aggiungi un'etichetta per la descrizione
            JLabel descrizioneLabel = new JLabel(oggetto.getDescrizione());
            pannello.add(descrizioneLabel);

            // Pulsante "Usa"
            JButton usaButton = new JButton("Usa");
            usaButton.addActionListener(e -> {
                // Logica per usare l'oggetto
                System.out.println("Hai usato: " + oggetto.getNome());
                // Puoi anche implementare logica per rimuovere l'oggetto dall'inventario se necessario
            });
            pannello.add(usaButton);

            // Pulsante "Esamina"
            JButton esaminaButton = new JButton("Esamina");
            esaminaButton.addActionListener(e -> {
                // Logica per esaminare l'oggetto
                System.out.println("Esaminando: " + oggetto.getNome());
            });
            pannello.add(esaminaButton);

            pannello.add(Box.createVerticalStrut(10)); // Spazio tra gli oggetti
        }

        finestraInventario.add(pannello);
        finestraInventario.setSize(400, 600);
        finestraInventario.setLocationRelativeTo(null); // Centra la finestra
        finestraInventario.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_D: // Premi il tasto D per aprire il diario
                mostraDiario(); // Chiama il metodo per mostrare il diario
                break;
            case KeyEvent.VK_PLUS: // Tasto + per aumentare lo zoom
                camera.setZoom(Math.min(camera.getZoom() + 0.1, 3.0)); // Limite max zoom 3.0x
                break;
            case KeyEvent.VK_MINUS: // Tasto - per ridurre lo zoom
                camera.setZoom(Math.max(camera.getZoom() - 0.1, 1.0)); // Limite min zoom 1.0x
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                upPressed = false; // Ripristina a false quando il tasto è rilasciato
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false; // Ripristina a false quando il tasto è rilasciato
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false; // Ripristina a false quando il tasto è rilasciato
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false; // Ripristina a false quando il tasto è rilasciato
                break;
        }
    }

    private void update() {
        boolean isMoving = false;

        int nuovoX = posX;
        int nuovoY = posY;

        if (upPressed && posY > 0) {
            nuovoY -= 2.5; // Muovi verso l'alto
            direzione = "su";
            isMoving = true;
        }
        if (downPressed && posY + personaggio.getHeight() < 1224) { // Sostituisci ALTEZZA con un valore più alto
            nuovoY += 2.5;
            direzione = "giu";
            isMoving = true;
        }
        if (leftPressed && posX > 0) {
            nuovoX -= 2.5; // Muovi verso sinistra
            direzione = "sinistra";
            isMoving = true;
        }
        if (rightPressed && posX + personaggio.getWidth() < 1515) {
            nuovoX += 2.5; // Muovi verso destra
            direzione = "destra";
            isMoving = true;
        }

        System.out.println("Tentativo di spostamento: X=" + posX + " Y=" + posY);


        // **Controllo collisione prima di aggiornare la posizione**
        if (!verificaCollisione(nuovoX, nuovoY)) {
            posX = nuovoX;
            posY = nuovoY;
        }

        camera.update(posX, posY);

        if (isMoving) {
            frameDelay++;
            if (frameDelay >= FRAME_INTERVAL) {
                frameDelay = 0;
                frameIndex = (frameIndex + 1) % spriteGiu.length; // Alterna tra i frame
            }
        } else {
            frameIndex = 0;
        }

        switch (direzione) {
            case "su":
                personaggio = spriteSu[frameIndex];
                break;
            case "giu":
                personaggio = spriteGiu[frameIndex];
                break;
            case "sinistra":
                personaggio = spriteSinistra[frameIndex];
                break;
            case "destra":
                personaggio = spriteDestra[frameIndex];
                break;
        }
    }


    private boolean giocoAvviato = false;

    private void startGameLoop() {
        if (giocoAvviato) return; // Evita di avviare più volte il ciclo di gioco
        giocoAvviato = true;

        Thread gameLoopThread = new Thread(() -> {
            while (true) {
                update(); // Aggiorna la posizione del personaggio
                disegna(); // Disegna la scena

                try {
                    Thread.sleep(16); // ~60 FPS (circa)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        gameLoopThread.start(); // Avvia il thread del ciclo di gioco
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame finestra = new JFrame(NOME_GIOCO);
            finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            finestra.setSize(LARGHEZZA, ALTEZZA);
            finestra.setResizable(false);
            finestra.setLocationRelativeTo(null);

            Gioco gioco = new Gioco();

            // Schermata iniziale
            SchermataIniziale schermataIniziale = new SchermataIniziale(finestra, () -> {
                finestra.getContentPane().removeAll();
                finestra.add(gioco);
                finestra.revalidate();
                finestra.repaint();
                gioco.avvia(finestra);
            });

            finestra.add(schermataIniziale);
            finestra.setVisible(true);
        });
    }

}