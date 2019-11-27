package com.example.webook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddNotes extends AppCompatActivity {
    private EditText mTitle;
    private EditText mDescription;

    private static final String TAG = "AddNotes";

    private static final String KEY_TITLE = "judul"; //the notes title
    private static final String KEY_DESC = "isi"; //notes desc

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data); //activity when adding notes


        mTitle = findViewById(R.id.titleNote);
        mDescription = findViewById(R.id.descriptionNote);
        Button simpan = findViewById(R.id.simpanNotes);

        //saving notes
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotes();
                finish();
            }
        });
    }

    //function save notes
    private void addNotes() {
        String judul = mTitle.getText().toString().trim();
        String isi = mDescription.getText().toString().trim();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, judul);
        note.put(KEY_DESC, isi);

        firestore.collection("Notes").document().set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Note Cannot Be Saved", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}