package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView register;
    Boolean result;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.etname);
        password = (EditText) findViewById(R.id.etepassword);
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
                validate(email.getText().toString(), password.getText().toString());
            }

        });
    }


    private void validate(String userName, String userPassword)
        {
            if((userName.equals("Admin")) && (userPassword.equals("1234"))){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }else{
                counter--;



                if(counter == 0){
                    login.setEnabled(false);
                }
            }
        }

}
