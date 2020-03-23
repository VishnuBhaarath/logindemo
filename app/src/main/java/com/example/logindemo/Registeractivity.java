package com.example.logindemo;

import android.content.Intent;
import android.os.Bundle;
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

public class  Registeractivity extends AppCompatActivity {
 EditText useremail,userpassword,userconfirmpassword,username;
  Button register;
  TextView uslogin;
  private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);
        SetUpViews();
        firebaseAuth=FirebaseAuth.getInstance();
        register=(Button)findViewById(R.id.btregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                  String email=useremail.getText().toString().trim();
                  String password=userpassword.getText().toString().trim();
                  firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){

                              Toast.makeText(Registeractivity.this,"Registration successfull",Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(Registeractivity.this,MainActivity.class));
                          }
                          else{
                              Toast.makeText(Registeractivity.this,"Not successfull",Toast.LENGTH_SHORT).show();
                          }
                      }
                  });

                }
            }
        });
        uslogin=findViewById(R.id.login);
        uslogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registeractivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void SetUpViews(){
        useremail=(EditText)findViewById(R.id.etemail);
        userpassword=(EditText)findViewById(R.id.Password);
        username=(EditText)findViewById(R.id.Name);
        userconfirmpassword=(EditText)findViewById(R.id.confPassword);
    }
    private Boolean validate(){
        Boolean result=false;
        String emailid=useremail.getText().toString().trim();
        String password=userpassword.getText().toString().trim();
        String confpassword=userconfirmpassword.getText().toString().trim();
        String name=username.getText().toString().trim();
        if(emailid.isEmpty()|| password.isEmpty() || confpassword.isEmpty() || name.isEmpty()){
            Toast.makeText(Registeractivity.this,"Registration failed,please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else{

            result=true;

        }
        return result;
    }
}
