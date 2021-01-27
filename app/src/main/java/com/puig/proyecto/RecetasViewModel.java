package com.puig.proyecto;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RecetasViewModel extends AndroidViewModel {

    private final RecetaStorage recetaStorage;

    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();
    MutableLiveData<Receta> recetaSeleccionada = new MutableLiveData<>();


    public RecetasViewModel(@NonNull Application application) {
        super(application);
        recetaStorage = new RecetaStorage(application);
    }

    void establecerImagenSeleccionada(Uri uri){
        imagenSeleccionada.setValue(uri);
    }


    public void insertar(String nombre, String imagenSeleccionada, int tiempo, int personas, boolean vegano, boolean gluten, String ing, String pasos) {
        recetaStorage.insertar(nombre, imagenSeleccionada, tiempo, personas, vegano, gluten, ing, pasos);
    }

    public void eliminar(Receta receta) {
        recetaStorage.eliminar(receta);
    }

    void seleccionar (Receta receta){
        recetaSeleccionada.setValue(receta);
    }

    MutableLiveData<Receta> seleccionado(){
        return recetaSeleccionada;
    }

    LiveData<List<Receta>> todoRecetas(){
        return recetaStorage.obtenerTodas();
    }
}
