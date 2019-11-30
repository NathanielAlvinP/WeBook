package com.example.webook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private static final String KEY_JUDUL = "judul";
    private static final String KEY_DESC = "isi";
    private TextView judulNotes;

    private RecyclerView recyclerViewNotes;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private RecyclerViewAdapterNotes adapter;
    private TextView textViewJudul;
    private TextView textViewIsi;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    ProgressDialog progressDialog;
    private StaggeredGridLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        recyclerViewNotes = rootView.findViewById(R.id.recycler_viewNotes);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //judulNotes = rootView.findViewById(R.id.judulNotes);
        textViewJudul = rootView.findViewById(R.id.judulNotes);
        textViewIsi = rootView.findViewById(R.id.isiNotes);

        showNotes();

        progressDialog = new ProgressDialog(getContext());


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

//    @Override
//    public void onResume() {
//        super.onResume();
//        showNotes();
//    }

    @Override
    public void onStart() {
        super.onStart();
        showNotes();
    }

//    public void detailsNotes(RecyclerViewAdapterNotes.NotesHolder notesHolder, final Notes notes, int pos){
//        notesHolder.titleNotes.setText(notes.getNotesJudul());
//        notesHolder.descNotes.setText(notes.getNotesIsi());
//
//        notesHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), AddNotes.class);
//                intent.putExtra("judul", notes.getNotesJudul());
//                intent.putExtra("isi", notes.getNotesIsi());
//                startActivity(intent);
//            }
//        });
//    }

//    public void deleteNote(int index){
//        progressDialog.setTitle("Deleting Data...");
//        progressDialog.show();
//        firestore.collection(uid).document()
//    }

    public void showNotes() {
        final ArrayList<Notes> notes = new ArrayList<>();
        final Task<QuerySnapshot> reference = firestore.collection(uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Notes nts = new Notes();
                        nts.setNotesJudul(documentSnapshot.get("judul").toString());
                        nts.setNotesIsi(documentSnapshot.get("isi").toString());

                        boolean isTitleEmpty = TextUtils.isEmpty(nts.getNotesJudul());
                        boolean isDescEmpty = TextUtils.isEmpty(nts.getNotesIsi());

//                        if(isTitleEmpty)
//                            textViewJudul.setVisibility(TextView.GONE);
//                        else if(isDescEmpty)
//                            textViewIsi.setVisibility(TextView.GONE);

                        notes.add(nts);
                    }
                    recyclerViewNotes.setHasFixedSize(true);
                    recyclerViewNotes.setLayoutManager(manager);
                    adapter = new RecyclerViewAdapterNotes(notes);
                    recyclerViewNotes.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Failed to Retrieve Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
