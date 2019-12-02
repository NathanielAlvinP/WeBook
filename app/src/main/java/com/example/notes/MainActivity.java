package com.example.notes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.lang.reflect.Field;
import java.util.Date;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav_Bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NoteFragment()).commit();
//        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.notesBottomBar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NoteFragment()).commit();
                        break;
                    case R.id.todoBottomBar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ToDoActivity()).commit();
                        break;
                }
                return false;
            }
        });
    }

//    private void showAlertDialog() {
//        final EditText noteEditText = new EditText(this);
//
//        new AlertDialog.Builder(this)
//                .setTitle("Add Note")
//                .setView(noteEditText)
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.d(TAG, "onClick: " + noteEditText.getText());
//                        addNote(noteEditText.getText().toString());
//                    }
//                }).setNegativeButton("Cancel", null)
//                .show();
//    }

//    private void addNote(String text) {
//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        Note note = new Note(text, "isi",false, new Timestamp(new Date()), userId);
//
//        FirebaseFirestore.getInstance()
//                .collection("Notes")
//                .add(note)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "onSuccess: Succesfully added the note...");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }













//    @Override
//    public void handleCheckChanged(boolean isChecked, DocumentSnapshot snapshot) {
//        Log.d(TAG, "handleCheckChanged: " + isChecked);
//        snapshot.getReference().update("completed", isChecked).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.d(TAG, "onSuccess: ");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, "onFailure: " + e.getLocalizedMessage());
//            }
//        });
//    }


//    public static class BottomNavigationHelper {
//        private static final String TAG = "BottomNavigationHelper";
//
//        @SuppressLint("RestrictedApi")
//        public static void disableShiftMode(BottomNavigationView view) {
//            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
//            try {
//                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//                shiftingMode.setAccessible(true);
//                shiftingMode.setBoolean(menuView, false);
//                shiftingMode.setAccessible(false);
//                for (int i = 0; i < menuView.getChildCount(); i++) {
//                    BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
//
//                    itemView.setShifting(false);
//
//                    itemView.setChecked(itemView.getItemData().isChecked());
//                }
//            } catch (NoSuchFieldException e) {
//                Log.e("BNVHelper", "Unable to get shift mode field", e);
//            } catch (IllegalAccessException e) {
//                Log.e("BVNHelper", "Unable to change value of shift mode", e);
//            }
//        }
//    }
}
