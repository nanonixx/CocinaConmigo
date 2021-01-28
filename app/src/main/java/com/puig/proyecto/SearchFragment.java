package com.puig.proyecto;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.puig.proyecto.databinding.FragmentSearchBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    public SearchFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentSearchBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);


        Button button = view.findViewById(R.id.searchButton);
        PushDownAnim.setPushDownAnimTo( button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_searchFragment_to_resultadosFragment);
            }
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


    }

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