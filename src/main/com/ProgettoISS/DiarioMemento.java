package com.ProgettoISS;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class DiarioMemento implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<String> enigmiCompletati;
    private final List<String> enigmiNonCompletati;
    private final String obiettivoPrincipale;
    private final List<String> missioniPrincipali;
    private final List<String> missioniSecondarie;

    public DiarioMemento(Diario diario) {
        this.enigmiCompletati = new ArrayList<>(diario.getEnigmiCompletati()); // Cambiato da getEventi()
        this.enigmiNonCompletati = new ArrayList<>(diario.getEnigmiNonCompletati());
        this.obiettivoPrincipale = diario.getObiettivoPrincipale();
        this.missioniPrincipali = new ArrayList<>(diario.getMissioniPrincipali());
        this.missioniSecondarie = new ArrayList<>(diario.getMissioniSecondarie());
    }


    public void ripristina(Diario diario) {
        diario.setEnigmiCompletati(new ArrayList<>(this.enigmiCompletati));
        diario.setEnigmiNonCompletati(new ArrayList<>(this.enigmiNonCompletati));
        diario.aggiornaObiettivoPrincipale(this.obiettivoPrincipale);
        diario.setMissioniPrincipali(new ArrayList<>(this.missioniPrincipali));
        diario.setMissioniSecondarie(new ArrayList<>(this.missioniSecondarie));
    }
}
