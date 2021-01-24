package com.puig.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.puig.proyecto.databinding.FragmentHistoryBinding;
import com.puig.proyecto.databinding.ViewholderHistorialBinding;


import java.util.List;


public class HistoryFragment extends Fragment {

    private RecetasViewModel recetasViewModel;
    private FragmentHistoryBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle HistoryInstanceState) {
        return (binding = FragmentHistoryBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle HistoryInstanceState) {
        super.onViewCreated(view, HistoryInstanceState);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);
        navController = Navigation.findNavController(view);

        HistoryFragment.RecetasAdapter recetasAdapter = new HistoryFragment.RecetasAdapter();
        binding.recycler.setAdapter(recetasAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetasAdapter.setRecetaList(recetas);
            }
        });

        NavController navController = Navigation.findNavController(view);

    }

    class RecetasAdapter extends RecyclerView.Adapter<HistoryFragment.historialViewHolder> {

        List<Receta> recetaList;

        @NonNull
        @Override
        public HistoryFragment.historialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HistoryFragment.historialViewHolder(ViewholderHistorialBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryFragment.historialViewHolder holder, int position) {
            Receta receta = recetaList.get(position);

            holder.binding.nombreReceta.setText(receta.nombreReceta);
            Glide.with(requireView()).load(receta.imagen).into(holder.binding.recetaPicture);

            holder.itemView.setOnClickListener(v -> {
                recetasViewModel.seleccionar(receta);
                navController.navigate(R.id.recetaFragment);
            });
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

    static class historialViewHolder extends RecyclerView.ViewHolder {
        ViewholderHistorialBinding binding;

        public historialViewHolder(@NonNull ViewholderHistorialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}