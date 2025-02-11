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
    private static Giocatore instance;
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
    private Inventario inventario;

    private static final String NOME_GIOCO = "L'eredità di Cincenzio";
    private Giocatore giocatore = Giocatore.getInstance(); // ✅ Usa il metodo statico getInstance()

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
    private JFrame finestraInventarioAperta = null;
    private JFrame finestraDiarioAperta = null;
    private boolean giocoInPausa = false; // Variabile per la pausa del gioco



    public Gioco() {
        caricaRisorse();
        impostaCollisioni(); // Aggiunge le zone di collisione
        addKeyListener(this);
        giocatore = Giocatore.getInstance(); // Singleton
        camera = new Camera(sfondo.getWidth(), sfondo.getHeight(), LARGHEZZA, ALTEZZA, 1.5);
        setFocusable(true); // Necessario per ricevere input da tastiera
        this.inventario = new Inventario(); // ✅ Assicura che l'inventario sia sempre inizializzato
        diario = new Diario(); // Inizializza il diario con l'obiettivo principale

        // Aggiunta oggetti inventario
        String percorsoIconaBroccolo = "/immagini/Broccolo.png";
        BufferedImage iconaBroccolo = caricaImmagine(percorsoIconaBroccolo);
        Broccolo broccolo = new Broccolo("Broccolo", "Un vegetale sano", iconaBroccolo, 150, 300);
        giocatore.getInventario().aggiungiOggetto(broccolo);

        // Crea e aggiungi la chiave all'inventario del giocatore
        String percorsoIconaChiave = "/immagini/Chiave.png";
        BufferedImage iconaChiave = caricaImmagine(percorsoIconaChiave);
        Chiave chiave = new Chiave("Chiave", "Una chiave dorata", iconaChiave, 200, 300);
        giocatore.getInventario().aggiungiOggetto(chiave);

        // Crea e aggiungi la lancetta di orologio all'inventario del giocatore
        String percorsoIconaLancetta = "/immagini/LancettaOrologio.png";
        BufferedImage iconaLancetta = caricaImmagine(percorsoIconaLancetta);
        LancettaOrologio lancetta = new LancettaOrologio("Lancetta Orologio", "Una lancetta di un orologio antico", iconaLancetta, 250, 350);
        giocatore.getInventario().aggiungiOggetto(lancetta);

        giocatore.getInventario().aggiungiOggetto(diario);


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

    private BufferedImage caricaImmagine(String percorso) {
        try {
            URL urlImmagine = getClass().getResource(percorso);
            if (urlImmagine != null) {
                return ImageIO.read(urlImmagine);
            } else {
                System.err.println("Immagine non trovata: " + percorso);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
            return null;
        }
    }

    public void avvia(JFrame finestra) {
        // Rimuovi tutti i componenti esistenti dalla finestra principale
        finestra.getContentPane().removeAll();

        // Crea un pannello per il pulsante "Apri Diario" e lo aggiunge alla finestra
        JPanel pannelloSuperiore = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton bottoneDiario = new JButton("Apri Diario");
        bottoneDiario.addActionListener(e -> mostraDiario());
        bottoneDiario.setPreferredSize(new Dimension(150, 40));
        pannelloSuperiore.add(bottoneDiario);

        JButton bottoneInventario = new JButton("Apri Inventario");
        bottoneInventario.addActionListener(e -> {
            mostraInventario(); // Mostra l'inventario quando clicchi sul pulsante
            giocoInPausa = true; // Metti il gioco in pausa
        });
        pannelloSuperiore.add(bottoneInventario, BorderLayout.EAST);

        JButton bottoneSalva = new JButton("Salva il Gioco");
        bottoneSalva.setPreferredSize(new Dimension(150, 40));
        bottoneSalva.addActionListener(e -> mostraFinestraSalvataggio());
        pannelloSuperiore.add(bottoneSalva);

        JButton bottoneCarica = new JButton("Carica Partita");
        bottoneCarica.setPreferredSize(new Dimension(150, 40));
        bottoneCarica.addActionListener(e -> mostraFinestraCaricamento());
        pannelloSuperiore.add(bottoneCarica);

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
       /* g.setColor(new Color(255, 0, 0, 100)); // Rosso trasparente
        for (Rectangle r : collisioni) {
            g.fillRect(r.x - cameraX, r.y - cameraY, r.width, r.height);
        }*/

        g.dispose();
        bufferStrategy.show();
    }

    public void mostraDiario() {
        if (finestraDiarioAperta == null || !finestraDiarioAperta.isVisible()) {
            finestraDiarioAperta = new JFrame("Diario di Bordo");

            // Carica l'immagine di sfondo
            String percorsoSfondoDiario = "/immagini/SfondoDiario.jpeg";
            BufferedImage sfondoDiario = caricaImmagine(percorsoSfondoDiario);

            // Crea un pannello con l'immagine di sfondo
            JPanel pannelloSfondo = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (sfondoDiario != null) {
                        // Disegna l'immagine di sfondo senza ridimensionarla
                        g.drawImage(sfondoDiario, 0, 0, sfondoDiario.getWidth(), sfondoDiario.getHeight(), this);
                    }
                }

                @Override
                public Dimension getPreferredSize() {
                    // Imposta le dimensioni preferite in base alle dimensioni dell'immagine
                    return new Dimension(sfondoDiario.getWidth(), sfondoDiario.getHeight());
                }
            };
            pannelloSfondo.setLayout(new BorderLayout());

            JTextArea areaTesto = new JTextArea(diario.visualizzaDiario());
            areaTesto.setEditable(false);
            areaTesto.setFont(new Font("Monospaced", Font.PLAIN, 12));
            areaTesto.setOpaque(false);

            // Aggiungi un pannello per i margini attorno all'area di testo
            JPanel pannelloMargini = new JPanel(new BorderLayout());
            pannelloMargini.setOpaque(false); // Rendi trasparente il pannello dei margini
            pannelloMargini.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 30)); // Margini: top, left, bottom, right (aumento ulteriore margine sinistro)
            pannelloMargini.add(areaTesto, BorderLayout.CENTER);

            JScrollPane scrollPane = new JScrollPane(pannelloMargini);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);

            pannelloSfondo.add(scrollPane, BorderLayout.CENTER);

            int larghezzaFinestra = sfondoDiario.getWidth() + 100; // Aumenta la larghezza della finestra
            int altezzaFinestra = sfondoDiario.getHeight();

            finestraDiarioAperta.setContentPane(pannelloSfondo);
            finestraDiarioAperta.setSize(larghezzaFinestra, altezzaFinestra);
            finestraDiarioAperta.setResizable(false);
            finestraDiarioAperta.setLocationRelativeTo(null);
            finestraDiarioAperta.setVisible(true);

            finestraDiarioAperta.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    finestraDiarioAperta = null;
                }
            });
        } else {
            finestraDiarioAperta.toFront();
        }
    }

    private void mostraFinestraSalvataggio() {
        System.out.println("Finestra di salvataggio aperta!"); // Debug
        JFrame finestraSalvataggio = new JFrame("Seleziona Slot di Salvataggio");
        JPanel pannello = new JPanel();
        pannello.setLayout(new GridLayout(3, 1));

        for (int i = 1; i <= 3; i++) {
            int slot = i;
            JButton slotButton = new JButton("Slot " + i);
            slotButton.addActionListener(e -> {
                System.out.println("Slot " + slot + " selezionato"); // Debug
                int conferma = JOptionPane.showConfirmDialog(
                        finestraSalvataggio,
                        "Vuoi salvare il gioco nello Slot " + slot + "?",
                        "Conferma Salvataggio",
                        JOptionPane.YES_NO_OPTION
                );
                if (conferma == JOptionPane.YES_OPTION) {
                    if (giocatore == null) {
                        JOptionPane.showMessageDialog(null, "Errore: il giocatore non è stato inizializzato!", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Salvataggi.salvaGioco(giocatore, slot);
                    JOptionPane.showMessageDialog(finestraSalvataggio, "Salvataggio completato!");
                    finestraSalvataggio.dispose(); // ✅ Chiude la finestra dopo il salvataggio
                }
            });

            pannello.add(slotButton);
        }

        finestraSalvataggio.add(pannello);
        finestraSalvataggio.setSize(300, 200);
        finestraSalvataggio.setLocationRelativeTo(null);
        finestraSalvataggio.setVisible(true);
    }

    private void mostraFinestraCaricamento() {
        JFrame finestraCaricamento = new JFrame("Seleziona Slot di Caricamento");
        JPanel pannello = new JPanel();
        pannello.setLayout(new GridLayout(3, 1));

        for (int i = 1; i <= 3; i++) {
            int slot = i;
            JButton slotButton = new JButton("Slot " + i);
            slotButton.addActionListener(e -> {
                Giocatore giocatoreCaricato = Giocatore.getInstance();
                if (Salvataggi.caricaGioco(giocatoreCaricato, slot)) {
                    JOptionPane.showMessageDialog(finestraCaricamento, "Caricamento completato!");
                    finestraCaricamento.dispose();

                    // ✅ Sincronizza i dati con il Singleton
                    Giocatore.ripristinaIstanza(giocatoreCaricato);
                    giocatore = Giocatore.getInstance();

                    // ✅ Verifica che non sia null
                    if (giocatore == null) {
                        System.err.println("❌ ERRORE: Il giocatore è null dopo il caricamento!");
                        return;
                    }

                    // ✅ Aggiorna la posizione
                    this.posX = giocatore.getPosizioneX();
                    this.posY = giocatore.getPosizioneY();
                    System.out.println("🎮 Personaggio aggiornato -> Posizione X: " + posX + ", Y: " + posY);
                } else {
                    JOptionPane.showMessageDialog(finestraCaricamento, "Errore nel caricamento dello Slot " + slot, "Errore", JOptionPane.ERROR_MESSAGE);
                }
            });

            pannello.add(slotButton);
        }

        finestraCaricamento.add(pannello);
        finestraCaricamento.setSize(300, 200);
        finestraCaricamento.setLocationRelativeTo(null);
        finestraCaricamento.setVisible(true);
    }


    public void mostraInventario() {
        if (finestraInventarioAperta == null || !finestraInventarioAperta.isVisible()) {
            finestraInventarioAperta = new JFrame("Inventario");

            // Carica l'immagine di sfondo
            String percorsoSfondoInventario = "/immagini/SfondoInventario.jpg";
            BufferedImage sfondoInventario = caricaImmagine(percorsoSfondoInventario);

            // Crea un pannello con l'immagine di sfondo
            JPanel pannelloSfondo = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (sfondoInventario != null) {
                        g.drawImage(sfondoInventario, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            BoxLayout boxLayout = new BoxLayout(pannelloSfondo, BoxLayout.Y_AXIS);
            pannelloSfondo.setLayout(boxLayout);

            // Aggiungi un margine superiore più grande al primo elemento
            pannelloSfondo.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // 30 pixel di margine superiore

            // Ottieni l'inventario del giocatore
            Inventario inventario = giocatore.getInventario();

            // Aggiungi ogni oggetto all'inventario
            for (Oggetto oggetto : inventario.getOggetti()) {
                JPanel oggettoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Pannello per ogni oggetto
                oggettoPanel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5)); // Aggiungi un po' di padding
                oggettoPanel.setOpaque(false); // Rendi trasparente il pannello dell'oggetto

                // Aggiungi l'immagine dell'oggetto (se presente)
                BufferedImage icona = oggetto.getIcona();
                if (icona != null) {
                    // Ridimensiona l'icona
                    Image immagineRidimensionata = icona.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Ridimensiona a 40x40
                    ImageIcon imageIcon = new ImageIcon(immagineRidimensionata);
                    JLabel iconaLabel = new JLabel(imageIcon);
                    oggettoPanel.add(iconaLabel);
                }

                // Aggiungi il nome dell'oggetto
                JLabel etichetta = new JLabel(oggetto.getNome()); // Nome dell'oggetto
                oggettoPanel.add(etichetta);

                // Pulsante "Usa"
                JButton usaButton = new JButton("Usa");
                usaButton.addActionListener(e -> {
                    // Logica per usare l'oggetto
                    System.out.println("Hai usato: " + oggetto.getNome());
                    oggetto.interagisci(); // Chiama il metodo interagisci dell'oggetto
                });
                oggettoPanel.add(usaButton);

                JButton esaminaButton = new JButton("Esamina");
                esaminaButton.addActionListener(e -> {
                    // Logica per esaminare l'oggetto
                    System.out.println("Esaminando: " + oggetto.getNome());
                    JOptionPane.showMessageDialog(finestraInventarioAperta, oggetto.getDescrizione()); // Mostra la descrizione in un popup
                });
                oggettoPanel.add(esaminaButton);

                pannelloSfondo.add(oggettoPanel);
            }

            JScrollPane scrollPane = new JScrollPane(pannelloSfondo);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);

            finestraInventarioAperta.setContentPane(scrollPane);
            finestraInventarioAperta.setSize(400, 600);
            finestraInventarioAperta.setLocationRelativeTo(null); // Centra la finestra
            finestraInventarioAperta.setVisible(true);

            // Quando la finestra dell'inventario viene chiusa, riprendi il gioco
            finestraInventarioAperta.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    finestraInventarioAperta = null;
                    giocoInPausa = false;
                    requestFocusInWindow(); // Riacquisisci il focus
                }
            });
        } else {
            finestraInventarioAperta.toFront(); // Porta la finestra esistente in primo piano
        }
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
                camera.setZoom(Math.min(camera.getZoom() + 0.1, 2.0)); // Limite max zoom 3.0x
                break;
            case KeyEvent.VK_MINUS: // Tasto - per ridurre lo zoom
                camera.setZoom(Math.max(camera.getZoom() - 0.1, 1.0)); // Limite min zoom 1.0x
                break;
            case KeyEvent.VK_I:
                mostraInventario();
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
            nuovoY -= 3; // Muovi verso l'alto
            direzione = "su";
            isMoving = true;
        }
        if (downPressed && posY + personaggio.getHeight() < 1224) { // Sostituisci ALTEZZA con un valore più alto
            nuovoY += 3;
            direzione = "giu";
            isMoving = true;
        }
        if (leftPressed && posX > 0) {
            nuovoX -= 3; // Muovi verso sinistra
            direzione = "sinistra";
            isMoving = true;
        }
        if (rightPressed && posX + personaggio.getWidth() < 1515) {
            nuovoX += 3; // Muovi verso destra
            direzione = "destra";
            isMoving = true;
        }

        System.out.println("Tentativo di spostamento: X=" + posX + " Y=" + posY);


        // **Controllo collisione prima di aggiornare la posizione**
        if (!verificaCollisione(nuovoX, nuovoY)) {
            posX = nuovoX;
            posY = nuovoY;
        }

        if (posX >=784 && posY == 712) { // Cambia le coordinate in base alla porta della magione
            entraNelSalone();
        }

        camera.update(posX, posY);

        if (isMoving) {
            frameDelay++;
            if (frameDelay >= FRAME_INTERVAL) {
                frameDelay = 0;
                frameIndex = (frameIndex + 1) % spriteGiu.length; // Alterna tra i frame
            }

            Giocatore giocatore = Giocatore.getInstance();
            giocatore.setPosizioneX(posX);
            giocatore.setPosizioneY(posY);
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

    private void entraNelSalone() {
        System.out.println("Entrando nel Salone...");
        JFrame finestra = (JFrame) SwingUtilities.getWindowAncestor(this);
        new Salone(finestra);
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


            finestra.setLayout(new BorderLayout());

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