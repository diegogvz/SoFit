package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;

import java.util.List;

public class ListaEjerciciosViewAdapter extends RecyclerView.Adapter<ListaEjerciciosViewAdapter.EjercicioViewHolder> {

    private final ListaEjerciciosViewAdapter.OnItemClickListener listener;
    private final List<String> ejercicios;
    public ListaEjerciciosViewAdapter(List<String> ejercicios, ListaEjerciciosViewAdapter.OnItemClickListener listener) {
        this.ejercicios = ejercicios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListaEjerciciosViewAdapter.EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_recycler_exercise, parent, false);
        return new EjercicioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEjerciciosViewAdapter.EjercicioViewHolder holder, int position) {
        String ej = ejercicios.get(position);
        holder.bindUser(ej, listener);

    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    protected class EjercicioViewHolder extends RecyclerView.ViewHolder {

        private final TextView ejercicioTextView;

        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            ejercicioTextView = (TextView) itemView.findViewById(R.id.ejercicio);
        }

        public void bindUser(final String ejercicio, final ListaEjerciciosViewAdapter.OnItemClickListener listener) {

            ejercicioTextView.setText(ejercicio);

            itemView.setOnClickListener(v -> listener.onItemClick(ejercicio));
        }

    }
}
