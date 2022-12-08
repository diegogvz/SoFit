package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Exercise;

import java.util.List;

public class ListaEjerciciosViewAdapter extends RecyclerView.Adapter<ListaEjerciciosViewAdapter.EjercicioViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Exercise item);
    }
    public interface DeleteListener {
        void deleteItem(Exercise item);
    }

    private List<Exercise> ejercicios;
    private final OnItemClickListener listener;
    private final DeleteListener deleteListener;

    public ListaEjerciciosViewAdapter(List<Exercise> ejercicios, ListaEjerciciosViewAdapter.OnItemClickListener listener, DeleteListener deleteListener) {
        this.ejercicios = ejercicios;
        this.listener = listener;
        this.deleteListener=deleteListener;
    }

    @NonNull
    @Override
    public ListaEjerciciosViewAdapter.EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_recycler_exercise, parent, false);
        return new EjercicioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEjerciciosViewAdapter.EjercicioViewHolder holder, int position) {
        Exercise ej= ejercicios.get(position);
        holder.bindUser(ej, listener, deleteListener);

    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }


    protected class EjercicioViewHolder extends RecyclerView.ViewHolder {

        private TextView ejercicioTextView;
        private ImageButton imgbtn;

        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            ejercicioTextView=(TextView) itemView.findViewById(R.id.ejercicio);
            imgbtn=(ImageButton) itemView.findViewById(R.id.imageButton3);
        }

        public void bindUser(final Exercise ejercicio, final ListaEjerciciosViewAdapter.OnItemClickListener listener, final DeleteListener deleteListener) {

            ejercicioTextView.setText(ejercicio.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(ejercicio);
                }
            });
            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteListener.deleteItem(ejercicio);
                }
            });
        }

    }
}
