package com.puig.proyecto;

import androidx.room.PrimaryKey;

import java.util.List;

public class Usuario {
    @PrimaryKey(autoGenerate = true)
    int id;

    String username;
    String fullname;

    String profilePicture;

//    List<Receta> usersRecipes;

    public Usuario(String username, String fullname, String profilePicture, List<Receta> usersRecipes) {
        this.username = username;
        this.fullname = fullname;
        this.profilePicture = profilePicture;
    }
}
