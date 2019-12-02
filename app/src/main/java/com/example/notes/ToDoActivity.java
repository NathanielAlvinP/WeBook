package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDoActivity extends Fragment {
    private RecyclerView recyclerViewToDo;
    private FloatingActionButton fabToDo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_todo,container,false);
//        Toolbar toolbar = v.findViewById(R.id.toolbarTodo);
//        setSupportActionBar(toolbar);

        recyclerViewToDo = v.findViewById(R.id.recyclerViewTodo);
        fabToDo = v.findViewById(R.id.fabTodo);

        fabToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(),AddToDoActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_todo);
//
//
////        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav_Bar);
////        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
////        Menu menu = bottomNavigationView.getMenu();
////        MenuItem menuItem = menu.getItem(1);
////
////        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
////                switch (menuItem.getItemId()){
////                    case R.id.notesBottomBar:
////                        Intent intent = new Intent(ToDoActivity.this, MainActivity.class);
////                        startActivity(intent);
////                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
////                        finish();
////                        break;
////                    case R.id.todoBottomBar:
////                        break;
////                }
////                return false;
////            }
////        });
//
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile:
                Toast.makeText(requireActivity(), "Your Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(requireActivity(), ProfileActivity.class));
                return true;
            case R.id.action_logout:
                Toast.makeText(requireActivity(), "Logout", Toast.LENGTH_SHORT).show();
                AuthUI.getInstance().signOut(requireActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
