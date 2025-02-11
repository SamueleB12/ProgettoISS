package com.ProgettoISS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CameraTest {


    @Test
    void testCameraInizializzazione(){

        Camera camera = new Camera(2000, 2000, 1080, 720, 1.0);

        assertEquals(0, camera.getCameraX(), "La posizione X iniziale della camera dovrebbe essere 0");
        assertEquals(0, camera.getCameraY(), "La posizione Y iniziale della camera dovrebbe essere 0");
        assertEquals(1.0, camera.getZoom(), "Lo zoom iniziale deve essere 1.0");
    }

    @Test
    void testCameraSiMuoveConGiocatore() {
        Camera camera = new Camera(2000, 2000, 1080, 720, 1.0);

        camera.update(1000, 1000);

        assertTrue(camera.getCameraX()>0, "La camera deve spostarsi con il cambiare della posizione del giocatore");
        assertTrue(camera.getCameraY()>0, "La camera deve spostarsi con il cambiare della posizione y del giocatore");

    }

    @Test
    void testCameraNonSuperaIBordiMappa(){

        Camera camera = new Camera(2000, 2000, 1080, 720, 1.0);
        camera.update(10, 10); //posizioniamo il giocatore ai bordi della mappa
        assertEquals(0, camera.getCameraX(), "La camera non deve superare i bordi");
        assertEquals(0, camera.getCameraY(), "La camera non deve superare i bordi");

        camera.update(1990, 1990);
        assertTrue(camera.getCameraX()< 2000 - 1080, "La camera non deve superare i bordi");
        assertTrue(camera.getCameraY()< 2000 - 720, "La camera non deve superare i bordi");
        //test non passato, risolto rendendo impossibilitato il giocatore dal raggiungere i bordi mappa
    }

}
