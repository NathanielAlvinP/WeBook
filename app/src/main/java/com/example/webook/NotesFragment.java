package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private RecyclerView recyclerViewNotes;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_notes,container,false);
        recyclerViewNotes = rootView.findViewById(R.id.recycler_viewNotes);

        showNotes();

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.floatButtonTambahData);

        //floating button to add
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(requireContext(), AddNotes.class);
                startActivity(add);
            }
        });
        return rootView;
    }

    public void showNotes(){
        final ArrayList<Notes> notes = new ArrayList<>();
        Task<QuerySnapshot> reference = firestore.collection("Notes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                Notes nts = new Notes();
                                nts.setNotesJudul(documentSnapshot.get("isi").toString());
                                nts.setNotesIsi(documentSnapshot.get("judul").toString());
                                notes.add(nts);
                            }
                            recyclerViewNotes.setHasFixedSize(true);
                            recyclerViewNotes.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter = new RecyclerViewAdapter(notes);
                            recyclerViewNotes.setAdapter(adapter);
                        }else {
                            Toast.makeText(getContext(), "Failed to Retrieve Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
