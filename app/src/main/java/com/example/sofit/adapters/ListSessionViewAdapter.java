package com.example.sofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.R;
import com.example.sofit.model.Session;

import java.util.List;

public class ListSessionViewAdapter extends RecyclerView.Adapter<ListSessionViewAdapter.DayViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Session item);
    }
    private List<Session> sessions;
    private final OnItemClickListener listener;

    public ListSessionViewAdapter(List<Session> listaSessions, OnItemClickListener listener) {
        this.sessions = listaSessions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListSessionViewAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_recyclerview_day, parent, false);
        return new DayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Session session = sessions.get(position);
        holder.bindUser(session, listener);

    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }


     protected class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView diaTextView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView=(TextView)itemView.findViewById(R.id.session);
        }

         public void bindUser(final Session session, final OnItemClickListener listener) {

             diaTextView.setText(session.getName());

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onItemClick(session);
                 }
             });
         }

    }
}
