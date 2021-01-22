package com.puig.proyecto;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Receta {
@PrimaryKey(autoGenerate = true)
        int id;

    String nombreReceta;
    String imagen;
//    String pasos;

//    List<String> ingredientes
//    List<String> consejos;

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

    public Receta(String nombreReceta, String imagen, int tiempo) {
        this.nombreReceta = nombreReceta;
        this.tiempo = tiempo;
        this.imagen = imagen;
    }
}
