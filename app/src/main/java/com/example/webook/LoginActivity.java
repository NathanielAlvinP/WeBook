package com.example.webook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText Email, Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.Email);
        Pass = findViewById(R.id.Password);
        Button loginButton = findViewById(R.id.LoginButton);
        TextView signup = findViewById(R.id.SignUp);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String emailInp = Email.getText().toString();
                String passInp = Pass.getText().toString();
                if(CheckLogin(emailInp,passInp)){
                    Intent main = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("username",emailInp);
                    main.putExtras(b);
                    finish();
                    startActivity(main);
                }else
                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signup = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(signup);
            }

        });
    }

    protected boolean CheckLogin(String email, String pass){
        return email.equals("admin") && pass.equals("123456");
    }
}
