package com.example.webook;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


public class AddData extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);

//        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
//        setTitle("Add Notes");

        // expandable floating button to add image
//        FloatingActionButton floatingActionButtonImage = findViewById(R.id.fab_image);
//        floatingActionButtonImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("Add Image");
//            }
//        });

        // expandable floating button to add image
//        FloatingActionButton floatingActionButtonLink = findViewById(R.id.fab_link);
//        floatingActionButtonLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("Add Link");
//            }
//        });

        // expandable floating button to add image
//        FloatingActionButton floatingActionButtonNotes = findViewById(R.id.fab_notes);
//        floatingActionButtonNotes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("Add Notes");
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_tambah_data, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.save) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void saveNote() {

    }
}
