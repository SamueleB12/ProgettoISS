package com.ProgettoISS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CaricatoreImmaginiTest {

    @Test
    void testCaricaImmagine() {
        CaricatoreImmagini loader = new CaricatoreImmagini();
        assertNotNull(loader.caricaImmagine("/immagini/sfondo.jpg"),
                "L'immagine dovrebbe essere caricata correttamente.");
    }
}
