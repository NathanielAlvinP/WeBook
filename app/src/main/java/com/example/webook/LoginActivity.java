package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class    LoginActivity extends AppCompatActivity {
    private EditText Email, Pass;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    FirebaseUser currentUser;
    String TAG = "login";
    Button loginButton;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        Email = findViewById(R.id.Email);
        Pass = findViewById(R.id.Password);
        loginButton = findViewById(R.id.LoginButton);
        signup = findViewById(R.id.SignUp);

        //to check if the user's already login or not
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailInp = Email.getText().toString();
                final String passInp = Pass.getText().toString();
                //if the field is empty
                if (emailInp.isEmpty()) {
                    Email.setError("Please Enter Your Email");
                    Email.requestFocus();
                } else if (passInp.isEmpty()) {
                    Pass.setError("Please Enter Your Password");
                    Pass.requestFocus();
                }

                //if all the field are filled
                else {
                    mAuth.signInWithEmailAndPassword(emailInp, passInp)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //if login succeed
                                    if (task.isSuccessful()) {
                                        if (currentUser != null)
                                            Log.d(TAG, "Sign In With Email : Success");
                                            Intent mainActivity = new Intent(LoginActivity.this,
                                                    MainActivity.class);
                                            startActivity(mainActivity);
                                            finish();

                                    } else {
                                        Toast.makeText(LoginActivity.this,
                                                "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        //when the user click sign up, the code goes here
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signup);
            }

        });
    }
}
