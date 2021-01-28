package com.puig.proyecto;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.bumptech.glide.Glide;
import com.puig.proyecto.databinding.FragmentInsertarRecetaBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;


public class InsertarRecetaFragment extends Fragment {

    private FragmentInsertarRecetaBinding binding;
    private RecetasViewModel recetasViewModel;
    Uri imagenSeleccionada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentInsertarRecetaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.previsualizarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzadorGaleria.launch("image/*");
            }
        });

        recetasViewModel.imagenSeleccionada.observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                imagenSeleccionada = uri;
                Glide.with(requireView())
                        .load(uri)
                        .into(binding.previsualizarFoto);
            }
        });

        Button button = view.findViewById(R.id.guardar);
        PushDownAnim.setPushDownAnimTo( button).setOnClickListener(v -> {

        });

        Button button2 = view.findViewById(R.id.insertar);
        PushDownAnim.setPushDownAnimTo(button2).setOnClickListener(v -> {
            String nombre = binding.nombre.getText().toString();
            int tiempo = 10;
            int personas = 4;

            if (!(binding.editnum.getText().toString().equals(""))) tiempo = Integer.parseInt(binding.editnum.getText().toString());
            if (!(binding.editnum2.getText().toString().equals(""))) personas = Integer.parseInt(binding.editnum2.getText().toString());

            String pasos = binding.pasosAseguir.getText().toString();

            CheckBox vegan = ((CheckBox) view.findViewById(R.id.checkBoxVegan) );
            CheckBox gluten = ((CheckBox) view.findViewById(R.id.checkBoxGluten) );

            boolean isVegan = vegan.isChecked();
            boolean isGlutenFree = gluten.isChecked();

            String image = "file:///android_asset/noImageAvailable.png";
            if (!(imagenSeleccionada == null)) image=imagenSeleccionada.toString();

           recetasViewModel.insertar(nombre, image, tiempo, personas, isVegan, isGlutenFree, Strings.ingredientes, pasos);
           navController.popBackStack();
           recetasViewModel.establecerImagenSeleccionada(null);

        });

        binding.mas.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        if(pressedUp == false){
                            pressedUp = true;
                            new mastiempo().execute();
                        }
                    break;
                    case MotionEvent.ACTION_UP:

                        pressedUp = false;

                }
                return true;
            }
        });


        binding.menos.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        if(pressedUp == false){
                            pressedUp = true;
                            new menostiempo().execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        pressedUp = false;

                }
                return true;
            }
        });

        binding.mas2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        if(pressedUp == false){
                            pressedUp = true;
                            new maspersonas().execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        pressedUp = false;

                }
                return true;
            }
        });


        binding.menos2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        if(pressedUp == false){
                            pressedUp = true;
                            new menospersonas().execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        pressedUp = false;

                }

                return true;
            }
        });


        PushDownAnim.setPushDownAnimTo(binding.deleteButton).setOnClickListener(v -> {

        });


    }

    private final ActivityResultLauncher<String> lanzadorGaleria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri ->
            recetasViewModel.establecerImagenSeleccionada(uri));
            //Glide.with(requireView()).load(uri).into(binding.previsualizarFoto));

    boolean pressedUp = false;

    class mastiempo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            while(pressedUp) {
                if (!(binding.editnum.getText().toString().equals(""))) binding.editnum.setText(String.valueOf(Integer.parseInt(binding.editnum.getText().toString())+1));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    class menostiempo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            while(pressedUp) {
                if (!(binding.editnum.getText().toString().equals(""))) binding.editnum.setText(String.valueOf(Integer.parseInt(binding.editnum.getText().toString())-1));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    class maspersonas extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            while(pressedUp) {
                if (!(binding.editnum2.getText().toString().equals(""))) binding.editnum2.setText(String.valueOf(Integer.parseInt(binding.editnum2.getText().toString())+1));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    class menospersonas extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            while(pressedUp) {
                if (!(binding.editnum2.getText().toString().equals(""))) binding.editnum2.setText(String.valueOf(Integer.parseInt(binding.editnum2.getText().toString())-1));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}