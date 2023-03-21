package com.example.inclass_zhuohan_926923.InClass_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_zhuohan_926923.R;

public class InClass01 extends AppCompatActivity {

    private Button button_calculate_BMI;
    private EditText input_weight;
    private EditText input_height_feet;
    private EditText input_height_inches;
    private TextView return_result;
    public static String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class01);

        setTitle("InClass 01 Activity");

        button_calculate_BMI = findViewById(R.id.button_calculate);
        input_weight = findViewById(R.id.editTextNumber_weight);
        input_height_feet = findViewById(R.id.editTextNumber_feet);
        input_height_inches = findViewById(R.id.editTextNumber_inches);
        return_result = findViewById(R.id.textView_res);


        button_calculate_BMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_w = input_weight.getText().toString();
                String input_f = input_height_feet.getText().toString();
                String input_in = input_height_inches.getText().toString();

                if(input_w.length() == 0){
                    input_weight.setError("Invalid Inputs");
                    Toast.makeText(InClass01.this,"Invalid Input!", Toast.LENGTH_LONG).show();
                }else if (input_f.length() == 0){
                    input_height_feet.setError("Invalid Inputs");
                    Toast.makeText(InClass01.this,"Invalid Input!", Toast.LENGTH_LONG).show();
                }else if (input_in.length() == 0){
                    input_height_inches.setError("Invalid Inputs");
                    Toast.makeText(InClass01.this,"Invalid Input!", Toast.LENGTH_LONG).show();
                }else{
                    Double weight_in_pounds = Double.parseDouble(input_w);
                    Double height_in_feet = Double.parseDouble(input_f);
                    Double height_in_inches = Double.parseDouble(input_in) + 12 * height_in_feet;
                    if (weight_in_pounds == 0) {
                        input_weight.setError("Invalid Inputs");
                        Toast.makeText(InClass01.this,"Invalid Input!", Toast.LENGTH_LONG).show();
                    }else{
                        Double BMI = Math.round((weight_in_pounds / (height_in_inches * height_in_inches)) * 703 * 100.00) / 100.00;

                        if (BMI >= 30){
                            return_result.setText("Your BMI : " + BMI + ".\nYour are Obese");
                        }else if(BMI >= 25 && BMI <= 29.9){
                            return_result.setText("Your BMI : " + BMI + ".\nYour are Overweight");
                        }else if(BMI >= 18.5 && BMI <= 24.9){
                            return_result.setText("Your BMI : " + BMI + ".\nYour are Normal Weight");
                        }else if(BMI < 18.5){
                            return_result.setText("Your BMI : " + BMI + ".\nYour are Underweight");
                        }
                        Toast.makeText(InClass01.this,"BMI Calculated!", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}