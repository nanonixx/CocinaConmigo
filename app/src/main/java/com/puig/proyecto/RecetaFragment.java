package com.puig.proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.puig.proyecto.databinding.FragmentRecetaBinding;


public class RecetaFragment extends Fragment {
        private FragmentRecetaBinding binding;
        private NavController navController;
        private RecetasViewModel recetasViewModel;


        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return (binding = com.puig.proyecto.databinding.FragmentRecetaBinding.inflate(inflater, container, false)).getRoot();
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);
        navController = Navigation.findNavController(view);

        recetasViewModel.seleccionado().observe(getViewLifecycleOwner(), receta -> {
            binding.nombrereceta.setText(receta.nombreReceta);
            binding.minutes.setText(String.valueOf(receta.tiempo)+" minutos");
            binding.numofpeople.setText(String.valueOf(receta.personas)+" personas");

            Glide.with(requireView())
                    .load(receta.imagen)
                    .fitCenter()
                    .centerCrop()
                    .into(binding.imageView4);
        });

        binding.gotouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recetaFragment_to_userFragment);
            }
        });



        binding.sharebutton.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Te envío la recetinga: http://bitly.com/98K8eH");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
    }
}
