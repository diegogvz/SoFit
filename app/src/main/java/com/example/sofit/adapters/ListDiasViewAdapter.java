package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Day;

import java.util.List;

public class ListDiasViewAdapter extends RecyclerView.Adapter<ListDiasViewAdapter.DayViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Day item);
    }
    private List<Day> days;
    private final OnItemClickListener listener;

    public ListDiasViewAdapter(List<Day> listaDays, OnItemClickListener listener) {
        this.days = listaDays;
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
        Day day = days.get(position);
        holder.bindUser(day, listener);

    }

    @Override
    public int getItemCount() {
        return days.size();
    }


     protected class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView diaTextView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView=(TextView)itemView.findViewById(R.id.day);
        }

         public void bindUser(final Day day, final OnItemClickListener listener) {

            diaTextView.setText(day.getNombre());

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onItemClick(day);
                 }
             });
         }

    }
}
