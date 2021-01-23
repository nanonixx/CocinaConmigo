package com.puig.proyecto.ui;

import java.util.List;

public class Ingredientes {

    private List<String> ingredientes;

    public Ingredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
