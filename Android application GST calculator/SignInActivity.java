package com.example.gst_with_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {


    EditText username2,password2;
    Button signin2;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        username2 = findViewById(R.id.username2);
        password2 = findViewById(R.id.password2);
        signin2 = findViewById(R.id.signin1);
        DB = new DBHelper(this);

        signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username2.getText().toString();
                String pass = password2.getText().toString();

                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignInActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = DB.checkUsernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(getApplicationContext(),CalculationAcitivity.class);
                        startActivity(intent2);
                    }else{
                        Toast.makeText(SignInActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}