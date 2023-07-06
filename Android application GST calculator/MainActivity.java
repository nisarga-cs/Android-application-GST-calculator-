package com.example.gst_with_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username1,password1,repassword1;
    Button signup1,signin1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username1=findViewById(R.id.username1);
        password1 = findViewById(R.id.pass1);
        repassword1 = findViewById(R.id.confirm);

        signup1 = findViewById(R.id.signup1);
        signin1 = findViewById(R.id.siin1);
        DB=new DBHelper(MainActivity.this);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user1 = username1.getText().toString();
                String pass1 = password1.getText().toString();
                String repass1 = repassword1.getText().toString();

                if(TextUtils.isEmpty(user1) ||TextUtils.isEmpty(pass1)||TextUtils.isEmpty(repass1)){
                    Toast.makeText(MainActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass1.equals(repass1)){
                        Boolean checkuser=DB.checkUsername(user1);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user1,pass1);
                            if(insert == true){
                                Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,CalculationAcitivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registraion failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"User already exists",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}