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
import com.puig.proyecto.databinding.FragmentMisRecetasBinding;
import com.puig.proyecto.databinding.ViewholderMisrecetasBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;


public class MisRecetasFragment extends Fragment {



    private RecetasViewModel recetasViewModel;
    private FragmentMisRecetasBinding binding;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle MisRecetasInstanceState) {
        return (binding = FragmentMisRecetasBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle MisRecetasInstanceState) {
        super.onViewCreated(view, MisRecetasInstanceState);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);
        navController = Navigation.findNavController(view);

        MisRecetasFragment.RecetasAdapter recetasAdapter = new MisRecetasFragment.RecetasAdapter();
        binding.recycler.setAdapter(recetasAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetasAdapter.setRecetaList(recetas);
            }
        });


    }

    class RecetasAdapter extends RecyclerView.Adapter<MisRecetasFragment.MisrecetasViewHolder> {

        List<Receta> recetaList;

        @NonNull
        @Override
        public MisRecetasFragment.MisrecetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MisRecetasFragment.MisrecetasViewHolder(ViewholderMisrecetasBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MisRecetasFragment.MisrecetasViewHolder holder, int position) {
            Receta receta = recetaList.get(position);

            holder.binding.nombreReceta.setText(receta.nombreReceta);
            Glide.with(requireView()).load(receta.imagen)
                    .centerCrop().into(holder.binding.imagen);

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

    static class MisrecetasViewHolder extends RecyclerView.ViewHolder {
        ViewholderMisrecetasBinding binding;

        public MisrecetasViewHolder(@NonNull ViewholderMisrecetasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
    
}