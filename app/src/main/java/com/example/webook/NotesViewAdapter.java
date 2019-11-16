package com.example.webook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class NotesViewAdapter extends FirestoreRecyclerAdapter<UserData, NotesViewAdapter.NotesViewHolder> {

    private OnItemClickListener listener;


    public NotesViewAdapter(@NonNull FirestoreRecyclerOptions<UserData> options) {
        super(options);
    }

    @NonNull
    @Override
    protected void onBindViewHolder(@NonNull NotesViewHolder notesViewHolder, int i, @NonNull UserData userData) {
        notesViewHolder.judulNotes.setText(userData.getTitleNote());
        notesViewHolder.descNotes.setText(userData.getDesc());
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card_view, parent, false);
        return new NotesViewHolder(view);
    }

    public void deleteItem(int pos) {
        getSnapshots().getSnapshot(pos).getReference().delete();
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int pos);
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView judulNotes, descNotes;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            judulNotes = itemView.findViewById(R.id.NotesTitle);
            descNotes = itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(pos), pos);
                    }
                }
            });
        }
    }
}
