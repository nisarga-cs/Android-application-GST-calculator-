package com.example.gst_with_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Result_display_activity extends AppCompatActivity {
    TextView amount,igst,cgst,sgst,total_amount;
    Button copy;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    EditText itemname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        amount = findViewById(R.id.amount);
        igst = findViewById(R.id.igst);
        cgst = findViewById(R.id.cgst);
        sgst = findViewById(R.id.sgst);
        total_amount = findViewById(R.id.total_amount);
         itemname=findViewById(R.id.item);

        copy = (Button)findViewById(R.id.copy);

        String recv_amount = getIntent().getStringExtra("value_entered_amount");
        String recv_cgst = getIntent().getStringExtra("value_cgst");
        String recv_igst = getIntent().getStringExtra("value_igst");
        String recv_sgst = getIntent().getStringExtra("value_sgst");
        String recv_total_amount = getIntent().getStringExtra("value_totalAmount");
        amount.setText(" "+recv_amount);
        igst.setText(" "+recv_igst);
        cgst.setText(" "+recv_cgst);
        sgst.setText(" "+recv_sgst);


        total_amount.setText(" "+recv_total_amount);
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                String item = itemname.getText().toString();
                if(item.isEmpty()){
                    Toast.makeText(getApplicationContext(),"You havent entered name of the item puchased",Toast.LENGTH_SHORT).show();

                }else{
                    text=total_amount.getText().toString();
                    myClip= ClipData.newPlainText("text",item+": "+text);
                    myClipboard.setPrimaryClip(myClip);

                    Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}