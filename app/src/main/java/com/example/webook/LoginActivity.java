package com.example.webook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.webook.FragmentBeforeLogin.HomeNoLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText Email, Pass;
    private FirebaseAuth mAuth;
    private TextView guestMode;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Email=findViewById(R.id.Email);
        guestMode = findViewById(R.id.GuestMode);
        Pass = findViewById(R.id.Password);
        Button loginButton = findViewById(R.id.LoginButton);
        TextView signup = findViewById(R.id.SignUp);
        //Go To user GusetHouse
        guestMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guesMode = new Intent(LoginActivity.this, HomeNoLogin.class);
                finish();
                startActivity(guesMode);
            }
        });
        //check apakah user sudah login
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String emailInp = Email.getText().toString();
                final String passInp = Pass.getText().toString();

                //cek field kosong
                if(emailInp.isEmpty()){
                    Email.setError("Please Enter Your Email");
                    Email.requestFocus();
                }else if(passInp.isEmpty()){
                    Pass.setError("Please Enter Your Password");
                    Pass.requestFocus();
                }

                //jika semua terisi
                else{
                    mAuth.signInWithEmailAndPassword(emailInp, passInp).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //jika berhasil
                            if(task.isSuccessful()){
                                    Intent main = new Intent(LoginActivity.this,MainActivity.class);
                                    Bundle b = new Bundle();
                                    b.putString("email",emailInp);
                                    main.putExtras(b);

                                sharedPreferences = getPreferences(MODE_PRIVATE);
                                String userId = sharedPreferences.getString("FirebaseKey ", "");

                                    startActivity(main);
                            }else
                                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
}
