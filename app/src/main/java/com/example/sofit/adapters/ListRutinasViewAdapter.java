package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Routine;

import java.util.List;

public class ListRutinasViewAdapter extends RecyclerView.Adapter<ListRutinasViewAdapter.RutinaViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(Routine rutina);

    }
    public interface DeleteListener{
        void deleteRoutine(Routine rutina);

    }

    private List<Routine> rutinas;
    private final OnItemClickListener listener;
    private final DeleteListener deleteListener;

    public ListRutinasViewAdapter(List<Routine> listRoutines, OnItemClickListener listener,DeleteListener deleteListener){
        this.rutinas = listRoutines;
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public RutinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_recyclerview_rutine, parent, false);
        return new RutinaViewHolder(itemView,listener,deleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinaViewHolder holder, int position) {
        Routine rutina = rutinas.get(position);
        holder.bindUser(rutina, listener,deleteListener);
    }

    @Override
    public int getItemCount() {
        return rutinas.size();
    }

    protected class RutinaViewHolder extends RecyclerView.ViewHolder {

        private TextView rutinaTextView;
        private ImageButton deleteButton;

        public RutinaViewHolder(@NonNull View itemView,OnItemClickListener listener, DeleteListener deleteListener) {
            super(itemView);
            rutinaTextView=(TextView)itemView.findViewById(R.id.rutina);
            deleteButton = (ImageButton) itemView.findViewById(R.id.imageButton2);
        }

        public void bindUser(@NonNull final Routine rutina, final OnItemClickListener listener,DeleteListener deleteListener) {

            rutinaTextView.setText(rutina.getNombre_rutina());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(rutina);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteListener.deleteRoutine(rutina);
                }
            });

        }


    }
}
