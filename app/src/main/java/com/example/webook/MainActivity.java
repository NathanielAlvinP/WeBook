package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("Notes");

    private NotesViewAdapter notesViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to show message when the user is login
        Bundle bundle = getIntent().getExtras();
        String s = null;
        if (bundle != null) {
            s = bundle.getString("email");
        }
        Toast.makeText(this, "Welcome, " + s, Toast.LENGTH_SHORT).show();

        //floating button to notes
        FloatingActionButton floatingActionButtonNotes = findViewById(R.id.fab_notes);
        floatingActionButtonNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ke Klik Notes Bang", Toast.LENGTH_SHORT).show();
                Intent notes = new Intent(MainActivity.this, AddData.class);
                startActivity(notes);
            }
        });

        //floating button to ToDo list
        FloatingActionButton floatingActionButtonTodo = findViewById(R.id.fab_todo);
        floatingActionButtonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ke Klik Todo Bang", Toast.LENGTH_SHORT).show();
            }
        });

        setUpRecyclerView();
    }

    //set recycler view for every data added into apps
    private void setUpRecyclerView() {
        //to ordered notes by title
        Query query = collectionReference.orderBy("title", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<UserData> options = new FirestoreRecyclerOptions.Builder<UserData>()
                .setQuery(query, UserData.class)
                .build();

        notesViewAdapter = new NotesViewAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesViewAdapter);
        notesViewAdapter.notifyDataSetChanged();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notesViewAdapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
    }

    //provide menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar_main_activity, menu);
        return true;
    }

    //function when one of the menu bar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Kepencet Profile Gan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Kepencet Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "Ini About", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notesViewAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (notesViewAdapter != null) {
            notesViewAdapter.stopListening();
        }
    }
}
