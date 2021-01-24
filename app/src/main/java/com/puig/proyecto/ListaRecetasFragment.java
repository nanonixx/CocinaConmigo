package com.puig.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.puig.proyecto.databinding.ActivityBottomBinding;
import com.puig.proyecto.databinding.FragmentListaRecetasBinding;
import com.puig.proyecto.databinding.ViewholderRecetaBinding;

import java.util.List;

public class ListaRecetasFragment extends Fragment {

    private FragmentListaRecetasBinding binding;
    private RecetasViewModel recetasViewModel;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentListaRecetasBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);

        RecetaAdapter recetaAdapter = new RecetaAdapter();

        binding.recycler.setAdapter(recetaAdapter);

        recetasViewModel.todoRecetas().observe(getViewLifecycleOwner(), new Observer<List<Receta>>() {
            @Override
            public void onChanged(List<Receta> recetas) {
                recetaAdapter.setRecetaList(recetas);
            }
        });

        binding.irAInsertarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_listaRecetasFragment_to_insertarRecetaFragment);
            }

        });

    }

    class RecetaAdapter extends RecyclerView.Adapter<RecetaViewHolder> {

        List<Receta> RecetaList;


        public Receta getReceta(int posicion){
            return RecetaList.get(posicion);
        }

        @NonNull
        @Override
        public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecetaViewHolder(ViewholderRecetaBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {

            Receta receta = RecetaList.get(position);

            holder.binding.recetaNombre.setText(receta.nombreReceta);
            Glide.with(requireView()).load(receta.imagen).into(holder.binding.recetaImage);

            holder.itemView.setOnClickListener(v -> {
                recetasViewModel.seleccionar(receta);
                navController.navigate(R.id.recetaFragment);

            });

        }

        @Override
        public int getItemCount() {
            return RecetaList != null ? RecetaList.size() : 0;
        }

        public void setRecetaList(List<Receta> RecetaList){
            this.RecetaList = RecetaList;
            notifyDataSetChanged();
        }
    }


    class RecetaViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderRecetaBinding binding;

        public RecetaViewHolder(ViewholderRecetaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
