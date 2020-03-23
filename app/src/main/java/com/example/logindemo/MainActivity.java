package com.example.logindemo;

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

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView register;
    FirebaseAuth firebaseAuth;
    Boolean result;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.etname);
        password = (EditText) findViewById(R.id.etepassword);
        firebaseAuth=FirebaseAuth.getInstance();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
       if(user!=null){
           finish();
           startActivity(new Intent(MainActivity.this,SecondActivity.class));
       }

        login = (Button) findViewById(R.id.btlogin);
        register=(TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Registeractivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailid=email.getText().toString().trim();
                String userpass=password.getText().toString().trim();
                if(mailid.isEmpty()||userpass.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter details",Toast.LENGTH_SHORT).show();
                }
                else{
                validate(mailid, userpass);
            }}

        });
    }


    private void validate(final String userName, final String userPassword){
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Login failed"+ userName+ userPassword,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
