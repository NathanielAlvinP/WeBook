package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class AddToDoActivity extends AppCompatActivity {
    private EditText taskTodo;
    private ImageView save;
    private static final String TAG = "AddToDoActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_activity);

        taskTodo = findViewById(R.id.toDoTask);
        save = findViewById(R.id.simpanTodo);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo(taskTodo.getText().toString());
                Intent intent = new Intent(AddToDoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addTodo(String taskTodo) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ToDo toDo = new ToDo(taskTodo,false, new Timestamp(new Date()), userId);

        FirebaseFirestore.getInstance()
                .collection("ToDo")
                .add(toDo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: Succesfully added the Task...");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddToDoActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
