package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Rutina;

import java.util.List;

public class ListRutinasViewAdapter extends RecyclerView.Adapter<ListRutinasViewAdapter.RutinaViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(Rutina rutina);
    }

    private List<Rutina> rutinas;
    private final OnItemClickListener listener;

    public ListRutinasViewAdapter(List<Rutina> listRutinas, OnItemClickListener listener){
        this.rutinas = listRutinas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RutinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_recyclerview_rutina, parent, false);
        return new RutinaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinaViewHolder holder, int position) {
        Rutina rutina = rutinas.get(position);
        holder.bindUser(rutina, listener);
    }

    @Override
    public int getItemCount() {
        return rutinas.size();
    }

    protected class RutinaViewHolder extends RecyclerView.ViewHolder {

        private TextView diaTextView;

        public RutinaViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView=(TextView)itemView.findViewById(R.id.dia);
        }

        public void bindUser(final Rutina rutina, final OnItemClickListener listener) {

//            diaTextView.setText(/*rutina.getNombre()*/"");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(rutina);
                }
            });
        }

    }
}
