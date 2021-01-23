package com.puig.proyecto;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.puig.proyecto.ui.Ingredientes;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Receta {
@PrimaryKey(autoGenerate = true)
        int id;

    String nombreReceta;
    String imagen;
    String user;
//    String[] ingredientes;

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

    public Receta(String nombreReceta, String imagen, int tiempo, int personas) {
        this.nombreReceta = nombreReceta;
        this.tiempo = tiempo;
        this.imagen = imagen;
        this.personas = personas;
    }
}
