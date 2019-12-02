package com.example.notes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddToDoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener{
    private EditText taskTodo;
    private ImageView save,date, clock;
    private TextView setDate, setClock;
    private static final String TAG = "AddToDoActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_activity);

        taskTodo = findViewById(R.id.toDoTask);
        save = findViewById(R.id.simpanTodo);
        setDate = findViewById(R.id.date);
        date =findViewById(R.id.dateSave);
        clock = findViewById(R.id.clockSave);
        setClock = findViewById(R.id.clockSet);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo(taskTodo.getText().toString());
                Intent intent = new Intent(AddToDoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment date = new DatePickerFragment();
                date.show(getSupportFragmentManager(),"date picker");
            }
        });
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new ClockFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        setDate.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        setClock.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }
}
