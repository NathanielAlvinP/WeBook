package com.example.webook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private EditText currentPassword;
    private EditText newPassword;
    private EditText newPasswordConfirmation;
    Button passwordConfirmation;

    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private String userEmail = "";
    private String userPassword = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.password1);
        newPasswordConfirmation = findViewById(R.id.password2);
        passwordConfirmation = findViewById(R.id.confirmation);

        changePassword();

    }

    private void changePassword() {
        final String currentPwd = currentPassword.getText().toString();
        final String newPwd = newPassword.getText().toString();
        final String confPwd = newPasswordConfirmation.getText().toString();

        passwordConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentPwd.isEmpty() && !newPwd.isEmpty() && !confPwd.isEmpty()){
                    if (newPassword.getText().toString().equals(newPasswordConfirmation.getText().toString())){
                        user = mAuth.getCurrentUser();
                    }
                }
            }
        });
    }
}
