package com.example.sofit.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Serie;

import java.util.ArrayList;
import java.util.List;

public class ListaSerieViewAdapter extends RecyclerView.Adapter<ListaSerieViewAdapter.SerieViewHolder>{
    List<Serie> series=new ArrayList<>();
    public ListaSerieViewAdapter(List<Serie> series) {
        this.series = series;
    }
    @NonNull
    @Override
    public ListaSerieViewAdapter.SerieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaSerieViewAdapter.SerieViewHolder holder, int position) {

        Serie serie= series.get(position);
        holder.weight.setText(serie.getWeight());
        holder.reps.setText(serie.getReps());
    }

    @Override
    public int getItemCount() {
        return series.size();
    }
    protected class SerieViewHolder extends RecyclerView.ViewHolder {
        public EditText weight;
        public EditText reps;

        public SerieViewHolder(@NonNull View itemView) {
            super(itemView);
            reps=(EditText)itemView.findViewById(R.id.editTextReps);
            weight=(EditText) itemView.findViewById(R.id.editTextWeight);

        }



    }
}
