package com.ProgettoISS;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ControlloGiocatoreTest {
    private Giocatore giocatore;
    private Visuale visuale;
    private ControlloGiocatore controlloGiocatore;
    private Gioco gioco; // Aggiungi un'istanza di Gioco
    private Diario diario; // Aggiungi un'istanza di Diario

    @BeforeEach
    public void setUp() {
        gioco = new Gioco(); // Crea un'istanza del gioco
        // Inizializza il diario
        diario = new Diario();
        giocatore = Giocatore.getInstance();
        visuale = new Visuale("percorso_mappa.png", 1280, 720, gioco); // Passa l'istanza di Gioco

        // Passa tutti i parametri richiesti al costruttore
        controlloGiocatore = new ControlloGiocatore(gioco, giocatore, visuale, diario);
    }

    @Test
    public void testMovimentoSu() {
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_UP, ' '));
        assertEquals(0, giocatore.getPosizioneY()); // Verifica che non si muova oltre il limite
    }

    @Test
    public void testMovimentoGiu() {
        giocatore.sposta(0, 700); // Porta il giocatore vicino al limite
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_DOWN, ' '));
        assertEquals(720, giocatore.getPosizioneY()); // Verifica che non superi il limite
    }

    @Test
    public void testInterazioneConOggetto() {
        BufferedImage icona = null;
        Oggetto broccolo = new Oggetto("Broccolo", "Un vegetale sano", icona, true, 300, 200);
        giocatore.aggiungiOggetto(broccolo); // Aggiungi un oggetto al giocatore
        // Simula la pressione del tasto Z per interagire con l'oggetto
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, ' '));
        // Dovresti verificare l'output o lo stato del diario qui (potrebbe richiedere una modifica al codice)
    }

    @Test
    public void testMovimentoVersoCellulaLibera() {
        // Inizializza la posizione del giocatore
        giocatore.sposta(0, 0); // Posizione iniziale

        // Simula la pressione del tasto giù
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_DOWN, ' '));
        assertEquals(50, giocatore.getPosizioneY()); // Verifica che si sia spostato verso il basso
    }

    @Test
    public void testMovimentoVersoCellulaOstacolata() {
        // Posiziona il giocatore vicino a un muro
        giocatore.sposta(0, 50); // Posizione iniziale sopra un muro

        // Simula la pressione del tasto giù
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_DOWN, ' '));
        assertEquals(50, giocatore.getPosizioneY()); // Verifica che non si sia spostato (rimane a Y=50)
    }

    @Test
    public void testMovimentoFuoriLimiti() {
        // Posiziona il giocatore nell'angolo superiore sinistro
        giocatore.sposta(0, 0);

        // Simula la pressione del tasto su
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_UP, ' '));
        assertEquals(0, giocatore.getPosizioneY()); // Verifica che non si sia spostato oltre il limite superiore

        // Simula la pressione del tasto sinistro
        controlloGiocatore.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_LEFT, ' '));
        assertEquals(0, giocatore.getPosizioneX()); // Verifica che non si sia spostato oltre il limite sinistro
    }
}