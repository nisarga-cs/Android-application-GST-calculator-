package com.example.gst_with_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationAcitivity extends AppCompatActivity {
    EditText amount;
    TextView view_percent, final_result;
    Button b_per_3, b_per_5, b_per_12, b_per_28, b_per_18, calculate,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_acitivity);

        //buttons
        b_per_3 = (Button) findViewById(R.id.btnplus3);
        b_per_3.setOnClickListener(this::onClick);

        b_per_5 = (Button) findViewById(R.id.btnplus5);
        b_per_5.setOnClickListener(this::onClick);

        b_per_12 = (Button) findViewById(R.id.btnplus12);
        b_per_12.setOnClickListener(this::onClick);

        b_per_18 = (Button) findViewById(R.id.btnplus18);
        b_per_18.setOnClickListener(this::onClick);

        b_per_28 = (Button) findViewById(R.id.btnplus28);
        b_per_28.setOnClickListener(this::onClick);

        calculate = (Button) findViewById(R.id.btncalculate);
        calculate.setOnClickListener(this::onClick);

        clear = (Button) findViewById(R.id.Clear);
        clear.setOnClickListener(this::onClick);
        //percentage display
        view_percent = (TextView) findViewById(R.id.gstPercentage);

        //amount
        amount = findViewById(R.id.enter_amount);
    }
    public void onClick(View view) {
        if (view.equals(b_per_3)) {
            view_percent.setText(" " + "3%");
        }
        if (view.equals(b_per_5)) {
            view_percent.setText(" " + "5%");
        }
        if (view.equals(b_per_18)) {
            view_percent.setText(" " + "18%");
        }
        if (view.equals(b_per_12)) {
            view_percent.setText(" " + "12%");
        }
        if (view.equals(b_per_28)) {
            view_percent.setText(" " + "28%");
        }
        if (view.equals(b_per_28)) {
            view_percent.setText(" " + "28%");
        }
        if (view.equals(clear)) {
            view_percent.setText("");
        }
        if (view.equals(calculate)) {
            String amt =amount.getText().toString();
            if(TextUtils.isEmpty(amt)){
                Toast.makeText(CalculationAcitivity.this,"Enter amount in numbers",Toast.LENGTH_SHORT).show();
            }else if( !(isCheckPattern(amt))){
                Toast.makeText(CalculationAcitivity.this,"Enter amount in numbers",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(view_percent.getText().toString())) {
                Toast.makeText(CalculationAcitivity.this,"You haven't selected GST percentage",Toast.LENGTH_SHORT).show();
            }else {


                float entered_amount = Float.parseFloat(amount.getText().toString());
                String str_percent_with_sym = view_percent.getText().toString();
                int lenText = str_percent_with_sym.length();
                String str_only_percent_value = str_percent_with_sym.substring(0, lenText - 1);
                float float_Percentage = Float.parseFloat(str_only_percent_value.toString());

                //calculations
                float result_amount = entered_amount + (entered_amount * float_Percentage / 100); //data+data*inPercentage/100;
                float igst = entered_amount * (float_Percentage / 100); //igst=cgst+sgst
                float cgst = entered_amount * (float_Percentage / 200);
                float sgst = entered_amount * (float_Percentage / 200);


                String send_total_amount = " " + result_amount;
                String send_igst = " " + igst;
                String send_cgst = " " + cgst;
                String send_sgst = " " + sgst;
                String send_amount = " " + entered_amount;


                //sending values
                Intent display_result = new Intent(CalculationAcitivity.this, Result_display_activity.class);
                display_result.putExtra("value_entered_amount", send_amount);
                display_result.putExtra("value_totalAmount", send_total_amount);
                display_result.putExtra("value_igst", send_igst);
                display_result.putExtra("value_sgst", send_sgst);
                display_result.putExtra("value_cgst", send_cgst);

                startActivity(display_result);

            }
        }

    }

    private boolean isCheckPattern(String amt) {
        Pattern pattern;
        Matcher match;
        String numpattern = "[0-9]*\\.?[0-9]*";
        pattern =Pattern.compile(numpattern);
        match = pattern.matcher(amt);
        return match.matches();
    }
}