package com.ProgettoISS;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*; //è mecessario?
import java.io.*;

public class SalvataggiTest {

    //Dichiarazione funzionamento del metodo SaveGame
    public static class SaveGame {
        private String filePath;

        public SaveGame(String filePath) {
            this.filePath = filePath;
        }

        //Salvataggio generico in un file
        public <T> void save(T objectToSave) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(objectToSave);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Caricamento generico da un file
        public <T> T load() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                return (T) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private SaveGame saveGame;
    private final String testFilePath = "test_save.dat";

    @BeforeEach
    void setUp() {
        saveGame = new SaveGame(testFilePath);
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete(); // Pulizia dopo ogni test
        }
    }

    @Test
    void testSalvataggioECaricamentoGenerico() {
        // Creiamo un oggetto generico da salvare
        String statoGenerico = "Diario, inventario, posizione nella mappa";

        // Salviamo l'oggetto
        saveGame.save(statoGenerico);

        // Verifica che il file di salvataggio esista con assertTrue
        File saveFile = new File(testFilePath);
        assertTrue(saveFile.exists(), "Il file di salvataggio deve esistere");

        // Carichiamo l'oggetto
        String statoCaricato = saveGame.load();

        // Verifica che il contenuto caricato sia lo stesso
        assertNotNull(statoCaricato, "Il contenuto deve essere caricato correttamente");
        assertEquals(statoGenerico, statoCaricato, "Il contenuto caricato deve essere uguale a quello salvato");
    }
}