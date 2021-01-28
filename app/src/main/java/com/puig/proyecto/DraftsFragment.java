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
import com.puig.proyecto.databinding.FragmentDraftsBinding;
import com.puig.proyecto.databinding.ViewholderBorradoresBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;


public class DraftsFragment extends Fragment {

    private RecetasViewModel recetasViewModel;
    private FragmentDraftsBinding binding;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle DraftsInstanceState) {
        return (binding = FragmentDraftsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle DraftsInstanceState) {
        super.onViewCreated(view, DraftsInstanceState);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);
        navController = Navigation.findNavController(view);

        RecetasAdapter recetasAdapter = new RecetasAdapter();
        binding.recyclerview.setAdapter(recetasAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetasAdapter.setRecetaList(recetas);
            }
        });

    }

    class RecetasAdapter extends RecyclerView.Adapter<BorradoresViewHolder> {

        List<Receta> recetaList;

        @NonNull
        @Override
        public BorradoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BorradoresViewHolder(ViewholderBorradoresBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BorradoresViewHolder holder, int position) {
            Receta receta = recetaList.get(position);

            holder.binding.nombreReceta.setText(receta.nombreReceta);
            Glide.with(requireView()).load(receta.imagen).into(holder.binding.imagen);

            holder.binding.imagen.setOnClickListener(v -> {
                recetasViewModel.seleccionar(receta);
                navController.navigate(R.id.recetaFragment);
            });

            PushDownAnim.setPushDownAnimTo(holder.binding.share).setOnClickListener(v -> {  });

            PushDownAnim.setPushDownAnimTo(holder.binding.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EliminarOverlay eliminarOverlay = new EliminarOverlay();
                    eliminarOverlay.show(getFragmentManager(), " r");
                }

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

    static class BorradoresViewHolder extends RecyclerView.ViewHolder {
        ViewholderBorradoresBinding binding;

        public BorradoresViewHolder(@NonNull ViewholderBorradoresBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }


}