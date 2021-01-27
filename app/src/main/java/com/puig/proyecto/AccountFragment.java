package com.puig.proyecto;

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
import android.view.View;
import android.view.ViewGroup;

import com.puig.proyecto.databinding.FragmentAccountBinding;
import com.puig.proyecto.databinding.FragmentListaRecetasBinding;


public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private NavController navController;
    private RecetasViewModel recetasViewModel;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentAccountBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);

        binding.editPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzadorGaleria.launch("image/*");
            }
        });

        binding.gotoDrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_accountFragment_to_draftsFragment);
            }


        });

        binding.myRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_accountFragment_to_misRecetasFragment);
            }

        });

                binding.logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDialogFragment myDialogFragment = new MyDialogFragment();
                        myDialogFragment.show(getFragmentManager(), " r");
                    }

                }


        );
    }
    private final ActivityResultLauncher<String> lanzadorGaleria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri ->
                    recetasViewModel.establecerImagenSeleccionada(uri));
}