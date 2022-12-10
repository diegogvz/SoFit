package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.ModelExercise;

import java.util.List;

public class ListaEjerciciosViewAdapter extends RecyclerView.Adapter<ListaEjerciciosViewAdapter.EjercicioViewHolder> {
    private final ListaEjerciciosViewAdapter.OnItemClickListener clickListener;
    private final ListaEjerciciosViewAdapter.DeleteListener deleteListener;
    private final List<ModelExercise> ejercicios;
    public ListaEjerciciosViewAdapter(List<ModelExercise> ejercicios, ListaEjerciciosViewAdapter.OnItemClickListener clickListener, ListaEjerciciosViewAdapter.DeleteListener deleteListener) {
        this.ejercicios = ejercicios;
        this.clickListener = clickListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ListaEjerciciosViewAdapter.EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_recycler_exercise, parent, false);
        return new ListaEjerciciosViewAdapter.EjercicioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEjerciciosViewAdapter.EjercicioViewHolder holder, int position) {
        ModelExercise ej = ejercicios.get(position);
        holder.bindUser(ej, clickListener,deleteListener);
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public interface DeleteListener {
        void deleteItem(ModelExercise item);
    }

    public interface OnItemClickListener {
        void onItemClick(ModelExercise item);
    }

    protected class EjercicioViewHolder extends RecyclerView.ViewHolder {

        private final TextView ejercicioTextView;
        private ImageButton imgbtn;

        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            ejercicioTextView = (TextView) itemView.findViewById(R.id.ejercicio);
        }

        public void bindUser(final ModelExercise ejercicio, final ListaEjerciciosViewAdapter.OnItemClickListener listener, final ListaEjerciciosViewAdapter.DeleteListener deleteListener) {

            ejercicioTextView.setText(ejercicio.getName());

            itemView.setOnClickListener(v -> listener.onItemClick(ejercicio));
        }

    }

}




