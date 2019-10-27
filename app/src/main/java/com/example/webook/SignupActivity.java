package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    public EditText emailSignup,passSignup,passCheck;
    public Button button;
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
                String email = emailSignup.getText().toString();
                String pass = passSignup.getText().toString();
                String repass = passCheck.getText().toString();
                if(pass.isEmpty() == false && email.isEmpty() == false && pass.equals(repass)){
                    Toast.makeText(SignupActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(SignupActivity.this,LoginActivity.class);
                    finish();
                    startActivity(main);
                }else if(pass.isEmpty() || repass.isEmpty() || email.isEmpty()){
                    Toast.makeText(SignupActivity.this, "All field must be entered", Toast.LENGTH_SHORT).show();
                }else if(pass.equalsIgnoreCase(repass) == false){
                    Toast.makeText(SignupActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
