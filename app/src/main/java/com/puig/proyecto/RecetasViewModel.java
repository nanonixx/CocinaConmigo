package com.puig.proyecto;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class RecetasViewModel extends AndroidViewModel {

    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();


    public RecetasViewModel(@NonNull Application application) {
        super(application);
    }

    void establecerImagenSeleccionada(Uri uri){
        imagenSeleccionada.setValue(uri);
    }


}
