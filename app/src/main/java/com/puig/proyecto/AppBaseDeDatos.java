package com.puig.proyecto;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {Receta.class}, version = 1, exportSchema = false)
public abstract class AppBaseDeDatos extends RoomDatabase {

    private static volatile AppBaseDeDatos INSTANCE;

    public abstract AppDao obtenerAppDao();

    public static AppBaseDeDatos getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppBaseDeDatos.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppBaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Dao
    interface AppDao{
        @Insert
        void insertarReceta(Receta receta);

        @Query("SELECT * FROM Receta")
        LiveData<List<Receta>> todasRecetas();
    }
}
