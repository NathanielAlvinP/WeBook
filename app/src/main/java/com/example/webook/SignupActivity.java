package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    public EditText emailSignup,passSignup,passCheck;
    public Button button;
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailSignup = findViewById(R.id.emailSign);
        passSignup = findViewById(R.id.passSign);
        passCheck = findViewById(R.id.passCheck);
        button = findViewById(R.id.signupButton);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mAuth = FirebaseAuth.getInstance();
                String email = emailSignup.getText().toString();
                String pass = passSignup.getText().toString();
                String repass = passCheck.getText().toString();

                //cek field kosong
                if(email.isEmpty()){
                    emailSignup.setError("Please Enter Your Email");
                    emailSignup.requestFocus();
                }else if(pass.isEmpty()){
                    passSignup.setError("Please Create Your Password");
                    passSignup.requestFocus();
                }else if(repass.isEmpty()){
                    passCheck.setError("Please re-enter Your Password");
                    passCheck.requestFocus();
                }

                //jika reenter pass tidak sesuai dengan pass
                else if(!pass.equalsIgnoreCase(repass)){
                    Toast.makeText(SignupActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }

                //jika semua sesuai ketentuan
                else if(!(pass.isEmpty()&& email.isEmpty()) && pass.equals(repass)){
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                Intent Login = new Intent(SignupActivity.this,LoginActivity.class);
                                finish();
                                startActivity(Login);
                            }else
                                Toast.makeText(SignupActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
