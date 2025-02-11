package com.ProgettoISS;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class InventarioMemento implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Oggetto> oggettiSalvati;

    public InventarioMemento(Inventario inventario) {
        this.oggettiSalvati = new ArrayList<>(inventario.getOggetti()); // Copia gli oggetti attuali
    }

    public void ripristina(Inventario inventario) {
        inventario.setOggetti(this.oggettiSalvati); // Usa il metodo pubblico setOggetti()
    }


}
