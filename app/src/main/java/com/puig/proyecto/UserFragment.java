package com.puig.proyecto;

import android.content.Intent;
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
import com.puig.proyecto.databinding.FragmentListaRecetasBinding;
import com.puig.proyecto.databinding.FragmentUserBinding;
import com.puig.proyecto.databinding.ViewholderUserrecipesBinding;

import java.util.List;

public class UserFragment extends Fragment {


    private FragmentUserBinding binding;
    private RecetasViewModel recetasViewModel;
    private NavController navController;

    public UserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle UserInstanceState) {
        return (binding = FragmentUserBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);
        navController = Navigation.findNavController(view);

        UserFragment.RecetasAdapter recetasAdapter = new UserFragment.RecetasAdapter();
        binding.recycler.setAdapter(recetasAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetasAdapter.setRecetaList(recetas);
            }
        });

        Glide.with(requireView()).load("file:///android_asset/user.png").into(binding.imageView3);
    }


        class RecetasAdapter extends RecyclerView.Adapter<UserFragment.UserViewHolder> {

            List<Receta> recetaList;

            @NonNull
            @Override
            public UserFragment.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UserFragment.UserViewHolder(ViewholderUserrecipesBinding.inflate(getLayoutInflater(), parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull UserFragment.UserViewHolder holder, int position) {
                Receta receta = recetaList.get(position);

                holder.binding.nombreReceta.setText(receta.nombreReceta);
                holder.binding.minutes.setText(String.valueOf(receta.tiempo)+"'");
                holder.binding.numofpeople.setText(String.valueOf(receta.personas)+"p");
                Glide.with(requireView()).load(receta.imagen).into(holder.binding.recetaPicture);

                if (receta.celiaco) holder.binding.vegcel.setImageResource(R.drawable.ic_gluten_free);
                if (receta.vegano) holder.binding.vegcel.setImageResource(R.drawable.ic_vegan);
                if (receta.vegano && receta.celiaco) holder.binding.vegcel.setImageResource(R.drawable.ic_veg_cel);




                holder.binding.recetaPicture.setOnClickListener(v -> {
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

        static class UserViewHolder extends RecyclerView.ViewHolder {
            ViewholderUserrecipesBinding binding;

            public UserViewHolder(@NonNull ViewholderUserrecipesBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }


        }

    }