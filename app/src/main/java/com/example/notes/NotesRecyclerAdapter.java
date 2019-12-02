package com.example.notes;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class NotesRecyclerAdapter extends FirestoreRecyclerAdapter<Note, NotesRecyclerAdapter.NoteViewHolder> {

    NoteListener noteListener;
    private static final String TAG = "NotesRecyclerAdapter";

    public NotesRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Note> options, NoteListener noteListener) {
        super(options);
        this.noteListener = noteListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        if(note.getJudul().isEmpty()){
            holder.titleTextView.setVisibility(View.GONE);
        }else if(note.getIsi().isEmpty()){
            holder.descTextView.setVisibility(View.GONE);
        }else{

        }
        holder.titleTextView.setText(note.getJudul());
        holder.descTextView.setText(note.getIsi());
//        holder.checkBox.setChecked(note.isCompletedTask());
        CharSequence dateCharSeq = DateFormat.format("EEEE, MMM d, yyyy h:mm:ss a", note.getCreatedDate().toDate());
        holder.dateTextView.setText(dateCharSeq);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_row, parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        TextView dateTextView;
//        CheckBox checkBox;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.noteTitle);
            descTextView = itemView.findViewById(R.id.noteDesc);
            dateTextView = itemView.findViewById(R.id.noteDate);
//            checkBox = itemView.findViewById(R.id.checkBox);

//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    DocumentSnapshot snapshot = getSnapshots().getSnapshot(getAdapterPosition());
//                    Note note = getItem(getAdapterPosition());
//                    if (note.isCompletedTask() == isChecked) {
//                        noteListener.handleCheckChanged(isChecked, snapshot);
//                    }
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocumentSnapshot snapshot = getSnapshots().getSnapshot(getAdapterPosition());
                    noteListener.handleEditNote(snapshot);
                }
            });
        }

        public void deleteItem() {
            noteListener.handleDeleteItem(getSnapshots().getSnapshot(getAdapterPosition()));
        }
    }

    interface NoteListener {
//        public void handleCheckChanged(boolean isChecked, DocumentSnapshot snapshot);

        public void handleEditNote(DocumentSnapshot snapshot);

        public void handleDeleteItem(DocumentSnapshot snapshot);
    }
}
