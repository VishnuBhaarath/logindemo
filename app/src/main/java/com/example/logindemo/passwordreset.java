package com.example.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passwordreset extends AppCompatActivity {
   private EditText resetmail;
    private Button submit;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordreset);
        firebaseAuth=FirebaseAuth.getInstance();
        submit=(Button)findViewById(R.id.resetbt);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetmail=(EditText)findViewById(R.id.usermail);
                String mail=resetmail.getText().toString().trim();
                if(mail==""){
                    Toast.makeText(passwordreset.this,"Please enter email",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(passwordreset.this,"Reset mail send",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(passwordreset.this,MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }
}
