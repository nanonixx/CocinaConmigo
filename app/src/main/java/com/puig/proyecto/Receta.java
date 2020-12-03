package com.puig.proyecto;

import androidx.room.Entity;

@Entity
public class Receta {

    String nombreReceta;
    String imagen;

    int tiempo;
    int personas;

    boolean vegano;
    boolean celiaco;

    public Receta(String nombreReceta, String imagen, int tiempo, int personas, boolean vegano, boolean celiaco) {
        this.nombreReceta = nombreReceta;
        this.imagen = imagen;
        this.tiempo = tiempo;
        this.personas = personas;
        this.vegano = vegano;
        this.celiaco = celiaco;
    }

}
