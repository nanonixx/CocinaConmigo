package com.puig.proyecto;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Receta.class}, version = 1, exportSchema = false)
public abstract class AppBaseDeDatos extends RoomDatabase {

    private static volatile AppBaseDeDatos INSTANCE;

    public abstract AppDao obtenerAppDao();
    static Executor executor = Executors.newSingleThreadExecutor();

    public static AppBaseDeDatos getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppBaseDeDatos.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppBaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                                    super.onDestructiveMigration(db);
                                    insertarRecetasIniciales(INSTANCE.obtenerAppDao());
                                }
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    insertarRecetasIniciales(INSTANCE.obtenerAppDao());
                                }
                            })

                            .build();

                }
            }
        }
        return INSTANCE;
    }
    static void insertarRecetasIniciales(AppDao appDao){
        executor.execute(()->{
            appDao.insertarReceta(new Receta("Espaguetis a la Boloñesa", "file:///android_asset/espagueti.png", 30, 3, false, false));
            appDao.insertarReceta(new Receta("Perrito Caliente", "file:///android_asset/hotdog.png", 10, 2, false, false));
            appDao.insertarReceta(new Receta("Mochis de Arroz", "file:///android_asset/mochi.png", 70, 4, true, true));
            appDao.insertarReceta(new Receta("Pepinos con ajo", "file:///android_asset/pepinajo.png", 20, 2, true, true));
            appDao.insertarReceta(new Receta("Pizza Barbacoa", "file:///android_asset/pizzabbq.png", 60, 6, false, false));
            appDao.insertarReceta(new Receta("Bizcocho para celíacos", "file:///android_asset/bizcocho.png", 90, 4, false, true));
            appDao.insertarReceta(new Receta("Tarta de Queso", "file:///android_asset/cheesecake.png", 100, 6, false, false));
            appDao.insertarReceta(new Receta("Cocido Madrileño", "file:///android_asset/cosidito.png", 120, 8, false, false));
            appDao.insertarReceta(new Receta("Entrecot de Ternera", "file:///android_asset/entrecot.png", 15, 3, false, false));
            appDao.insertarReceta(new Receta("Ramen Miso", "file:///android_asset/ramen.png", 120, 4, false, false));
            appDao.insertarReceta(new Receta("Tortilla de Espinacas", "file:///android_asset/tortilla.png", 20, 4, false, true));
        });
    }

    @Dao
    interface AppDao{

        @Insert
        void insertarReceta(Receta receta);

        @Query("SELECT * FROM Receta")
        LiveData<List<Receta>> todasRecetas();
    }

}
