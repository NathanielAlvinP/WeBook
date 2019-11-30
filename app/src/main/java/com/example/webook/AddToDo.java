package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddToDo extends AppCompatActivity {
    private EditText mDescriptionToDo;

    private static final String TAG = "AddTodo";

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private static final String KEY_DESC = "isi"; //notes desc

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotes); //activity when adding notes

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDescriptionToDo = findViewById(R.id.todoList);
        Button simpan = findViewById(R.id.simpanNotes);

        //saving notes
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo();
                finish();
            }
        });
    }

    //function save notes
    private void addTodo() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String isi = mDescriptionToDo.getText().toString().trim();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_DESC, isi);

        firestore.collection(uid).document().set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Todo Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Todo Cannot Be Saved", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}