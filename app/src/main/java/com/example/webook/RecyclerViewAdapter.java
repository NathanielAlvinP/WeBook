package com.example.webook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NotesHolder> {
    private List<Notes> notes;
    private FirebaseFirestore firestore;

    public RecyclerViewAdapter(List<Notes> notes){
        this.notes = notes;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notes_recycler,
                parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new NotesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.NotesHolder holder, int position) {
        holder.titleNotes.setText(notes.get(position).getNotesJudul());
        holder.descNotes.setText(notes.get(position).getNotesIsi());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesHolder extends RecyclerView.ViewHolder {
        TextView titleNotes;
        TextView descNotes;
        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            titleNotes = itemView.findViewById(R.id.judulNotes);
            descNotes = itemView.findViewById(R.id.isiNotes);

        }
    }
}
