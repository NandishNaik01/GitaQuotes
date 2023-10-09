package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextView Backtologin;
    private Button forgetbtn;
    private EditText txtEmail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Backtologin=findViewById(R.id.Backtologin);
        auth=FirebaseAuth.getInstance();
        txtEmail=findViewById(R.id.Emailforgot);
        forgetbtn=findViewById(R.id.Submitbtn);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }
        });
        Backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPasswordActivity.this,MainActivity.class));
            }
        });
    }

    private void validatedata() {
        email=txtEmail.getText().toString();
        if(email.isEmpty()){
            txtEmail.setError("Required");
        }else {
            forgetpass();
        }
    }

    private void forgetpass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this,"check your Registered email",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPasswordActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(ForgetPasswordActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}