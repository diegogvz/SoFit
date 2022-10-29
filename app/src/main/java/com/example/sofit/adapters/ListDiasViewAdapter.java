package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Dia;

import java.util.List;

public class ListDiasViewAdapter extends RecyclerView.Adapter<ListDiasViewAdapter.DayViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Dia item);
    }
    private List<Dia> dias;
    private final OnItemClickListener listener;

    public ListDiasViewAdapter(List<Dia> listaDias, OnItemClickListener listener) {
        this.dias = listaDias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListDiasViewAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_recyclerview_day, parent, false);
        return new DayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Dia dia= dias.get(position);
        holder.bindUser(dia, listener);

    }

    @Override
    public int getItemCount() {
        return dias.size();
    }


     protected class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView diaTextView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView=(TextView)itemView.findViewById(R.id.dia);
        }

         public void bindUser(final Dia dia, final OnItemClickListener listener) {

            diaTextView.setText(dia.getNombre());

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onItemClick(dia);
                 }
             });
         }

    }
}
