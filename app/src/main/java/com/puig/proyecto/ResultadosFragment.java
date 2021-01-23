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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.puig.proyecto.databinding.FragmentResultadosBinding;
import com.puig.proyecto.databinding.ViewholderGuadadosBinding;
import com.puig.proyecto.databinding.ViewholderUserrecipesBinding;

import java.util.List;

public class ResultadosFragment extends Fragment {

    private RecetasViewModel recetasViewModel;
    private FragmentResultadosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle ResultadosInstanceState) {
        return (binding = FragmentResultadosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle ResultadosInstanceState) {
        super.onViewCreated(view, ResultadosInstanceState);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);

        ResultadosFragment.RecetasAdapter recetasAdapter = new ResultadosFragment.RecetasAdapter();
        binding.recycler.setAdapter(recetasAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetasAdapter.setRecetaList(recetas);
            }
        });

        NavController navController = Navigation.findNavController(view);

    }

    class RecetasAdapter extends RecyclerView.Adapter<ResultadosFragment.userrecipesViewHolder> {

        List<Receta> recetaList;

        @NonNull
        @Override
        public ResultadosFragment.userrecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ResultadosFragment.userrecipesViewHolder(ViewholderUserrecipesBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ResultadosFragment.userrecipesViewHolder holder, int position) {
            Receta receta = recetaList.get(position);

            holder.binding.nombreReceta.setText(receta.nombreReceta);
//            holder.binding.minutes.setText(receta.tiempo);
            Glide.with(requireView()).load(receta.imagen).into(holder.binding.recetaPicture);
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

    static class userrecipesViewHolder extends RecyclerView.ViewHolder {
        ViewholderUserrecipesBinding binding;

        public userrecipesViewHolder(@NonNull ViewholderUserrecipesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }

}