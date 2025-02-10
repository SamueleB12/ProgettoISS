package com.ProgettoISS;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaricatoreImmagini {

    BufferedImage image;

    public BufferedImage caricaImmagine(String posizione){

        try {
            image = ImageIO.read(getClass().getResource(posizione));
        } catch (IOException ex) {
            System.out.println("Immagine alla posizione:"+posizione+" caricata correttamente");
            Logger.getLogger(CaricatoreImmagini.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;
    }
}
