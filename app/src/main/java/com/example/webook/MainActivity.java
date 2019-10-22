package com.example.webook;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("username");
        Toast.makeText(this, "welcome, "+s, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }
}
