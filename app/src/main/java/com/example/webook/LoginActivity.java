package com.example.webook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText Email, Pass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Email=findViewById(R.id.Email);
        Pass = findViewById(R.id.Password);
        Button loginButton = findViewById(R.id.LoginButton);
        TextView signup = findViewById(R.id.SignUp);

        //check apakah user sudah login
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(LoginActivity.this, "Already Logged In", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                }
            }
        };
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

    protected boolean CheckLogin(String email, String pass){
        return email.equals("admin") && pass.equals("123456");
    }
}
