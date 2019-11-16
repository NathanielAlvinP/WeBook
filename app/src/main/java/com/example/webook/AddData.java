package com.example.webook;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class AddData extends AppCompatActivity {
    private EditText mTitle, mDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);

        //supposedly to set back arrow in the toolbar
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow);
        setTitle("Your Newly Notes");

        mTitle = findViewById(R.id.titleNote);
        mDescription = findViewById(R.id.descriptionNote);

    }

    //create menu toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_tambah_data, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //menu toolbar function when clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            //when clicked the save icon
            case R.id.save:
                saveNote();
                break;
            case R.id.linkImageUrl:
                Toast.makeText(this, "Buat Tambah Image", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //function to save note
    private void saveNote() {
        String titleNote = mTitle.getText().toString();
        String desc = mDescription.getText().toString();

        //check if the user fill or not both the fields
        if (titleNote.trim().isEmpty() && desc.trim().isEmpty()) {
            Toast.makeText(this, "Cannot Create Empty Notes\nPlease Try Again", Toast.LENGTH_SHORT).show();
        }
        //collection reference for added data
        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("Notes");
        collectionReference.add(new UserData(titleNote, desc));
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
