package com.puig.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.puig.proyecto.databinding.FragmentSavedBinding;
import com.puig.proyecto.databinding.ViewholderGuadadosBinding;

import java.util.List;


public class SavedFragment extends Fragment {

    private RecetasViewModel recetasViewModel;
    private FragmentSavedBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentSavedBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);

        RecetasAdapter recetasAdapter = new RecetasAdapter();
        binding.recyclerview.setAdapter(recetasAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetasAdapter.setRecetaList(recetas);
            }
        });

        NavController navController = Navigation.findNavController(view);

    }

    class RecetasAdapter extends RecyclerView.Adapter<GuardadosViewHolder> {

        List<Receta> recetaList;

        @NonNull
        @Override
        public GuardadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GuardadosViewHolder(ViewholderGuadadosBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull GuardadosViewHolder holder, int position) {
            Receta receta = recetaList.get(position);

            holder.binding.nombreReceta.setText(receta.nombreReceta);
            Glide.with(requireView()).load(receta.imagen).into(holder.binding.imagen);
        }

        @Override
        public int getItemCount() {
            return recetaList == null ? 0 : recetaList.size();
        }

        public void setRecetaList(List<Receta> recetaList) {
            this.recetaList = recetaList;
            notifyDataSetChanged();
        }
    }

    static class GuardadosViewHolder extends RecyclerView.ViewHolder {
        ViewholderGuadadosBinding binding;

        public GuardadosViewHolder(@NonNull ViewholderGuadadosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }


}