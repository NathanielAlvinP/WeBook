package com.example.webook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapterNotes extends RecyclerView.Adapter<RecyclerViewAdapterNotes.NotesHolder> {
    private List<Notes> notes;
    private FirebaseFirestore firestore;

    public RecyclerViewAdapterNotes(List<Notes> notes){
        this.notes = notes;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterNotes.NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notes_recycler,
                parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new NotesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterNotes.NotesHolder holder, int position) {
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
    private String getDateToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String today = dateFormat.format(date);
        return today;
    }
}
