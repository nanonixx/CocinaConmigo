package com.puig.proyecto;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Receta {
@PrimaryKey(autoGenerate = true)
        int id;

    String nombreReceta;
    String imagen;
    int idcreator;
     String ingredientes;
     String pasos;

    int tiempo;
    int personas;

    boolean vegano;
    boolean celiaco;

    @Ignore
    public Receta(String nombreReceta, String imagen, int tiempo, int personas, boolean vegano, boolean celiaco) {
        this.nombreReceta = nombreReceta;
        this.imagen = imagen;
        this.tiempo = tiempo;
        this.personas = personas;
        this.vegano = vegano;
        this.celiaco = celiaco;
    }

    @Ignore
    public Receta(String nombreReceta, String imagen, int tiempo, int personas, boolean vegano, boolean celiaco, String ingredientes, String pasos) {
        this.nombreReceta = nombreReceta;
        this.imagen = imagen;
        this.tiempo = tiempo;
        this.personas = personas;
        this.vegano = vegano;
        this.celiaco = celiaco;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }


    public Receta(String nombreReceta, String imagen, int tiempo, int personas) {
        this.nombreReceta = nombreReceta;
        this.tiempo = tiempo;
        this.imagen = imagen;
        this.personas = personas;
    }
}
