package com.puig.proyecto;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RecetaStorage {

    private final AppBaseDeDatos.AppDao appDao;

    Executor executor = Executors.newSingleThreadExecutor();

    RecetaStorage(Application application){
        appDao = AppBaseDeDatos.getInstance(application).obtenerAppDao();
    }

    public void insertar(String nombre, String imagenSeleccionada) {


        executor.execute(()->{
            appDao.insertarReceta(new Receta(nombre, imagenSeleccionada));
        });


    }
}
