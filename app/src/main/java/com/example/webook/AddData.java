package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;


public class AddData extends AppCompatActivity {
    private EditText mTitle, mDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);

        //to set toolbar
        Toolbar toolbar = findViewById(R.id.toolbarData);
        setSupportActionBar(toolbar);

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
            case R.id.save:
                saveNote();
                break;
            case R.id.linkImageUrl:
                addImageUrl();
                break;
            case android.R.id.home:
                Intent intent = new Intent(this, NotesFragment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //function to save note
    private void saveNote() {
        String title = mTitle.getText().toString();
        String desc = mDescription.getText().toString();

        //check if the user fill or not both the fields
        if (title.trim().isEmpty() && desc.trim().isEmpty()) {
            Toast.makeText(this, "Cannot Create Empty Notes\nPlease Try Again", Toast.LENGTH_SHORT).show();
        }
    }

    //function to add image and URL (to be determined)
    private void addImageUrl() {
    }
}
