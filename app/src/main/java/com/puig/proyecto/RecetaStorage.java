package com.puig.proyecto;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RecetaStorage {

    private final AppBaseDeDatos.AppDao appDao;

    Executor executor = Executors.newSingleThreadExecutor();

    RecetaStorage(Application application){
        appDao = AppBaseDeDatos.getInstance(application).obtenerAppDao();
    }

    public void insertar(String nombre, String imagenSeleccionada, int tiempo) {
        executor.execute(()->{
            appDao.insertarReceta(new Receta(nombre, imagenSeleccionada, tiempo));
        });
    }

    public LiveData<List<Receta>> obtenerTodas(){
        return appDao.todasRecetas();
    }
}
