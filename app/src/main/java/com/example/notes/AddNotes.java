package com.example.notes;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class AddNotes extends AppCompatActivity {
    private ImageView save;
    private EditText title;
    private EditText desc;
    private static final String TAG = "AddNotes";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotes);
        save = findViewById(R.id.simpanNotes);
        title = findViewById(R.id.titleNote);
        desc = findViewById(R.id.descriptionNote);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote(title.getText().toString(), desc.getText().toString());
                Intent intent = new Intent(AddNotes.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void addNote(String title, String desc) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Note note = new Note(title, desc,false, new Timestamp(new Date()), userId);

        FirebaseFirestore.getInstance()
                .collection("Notes")
                .add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: Succesfully added the note...");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNotes.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
