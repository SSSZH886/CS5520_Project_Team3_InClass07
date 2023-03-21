package com.example.inclass_zhuohan_926923;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inclass_zhuohan_926923.InClass_01.InClass01;
import com.example.inclass_zhuohan_926923.InClass_02.InClass02;
import com.example.inclass_zhuohan_926923.InClass_03.InClass03;
import com.example.inclass_zhuohan_926923.InClass_04.InClass04;
import com.example.inclass_zhuohan_926923.InClass_05.InClass_05;
import com.example.inclass_zhuohan_926923.Practice.PracticeActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonPractice;
    private Button buttonInClass01;

    private Button buttonInClass_02;

    private Button buttonInClass_03, buttonInClass_04, buttonInClass_05, buttonInClass_06, buttonInClass_07;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonPractice = findViewById(R.id.button);
        buttonInClass01 = findViewById(R.id.button_Inclass_01);
        buttonInClass_02 = findViewById(R.id.button_InClass_02);
        buttonInClass_03 = findViewById(R.id.button_toInClass03);
        buttonInClass_04 = findViewById(R.id.button_toInClass04);
        buttonInClass_05 = findViewById(R.id.button_toInClass05);
        buttonInClass_06 = findViewById(R.id.button_toInClass06);
        buttonInClass_07 = findViewById(R.id.button_toInClass07);


        buttonPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPracticeActivity = new Intent(MainActivity.this,
                        PracticeActivity.class);

                startActivity(toPracticeActivity);
            }


        });

        buttonInClass01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass01Activity = new Intent(MainActivity.this,
                        InClass01.class);

                startActivity(toInClass01Activity);
            }
        });

        buttonInClass_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass02Activity = new Intent(MainActivity.this,
                        InClass02.class);

                startActivity(toInClass02Activity);
            }
        });

        buttonInClass_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass03Activity = new Intent(MainActivity.this,
                        InClass03.class);

                startActivity(toInClass03Activity);
            }
        });

        buttonInClass_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass04Activity = new Intent(MainActivity.this,
                        InClass04.class);

                startActivity(toInClass04Activity);
            }
        });

        buttonInClass_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass05Activity = new Intent(MainActivity.this,
                        InClass_05.class);

                startActivity(toInClass05Activity);
            }
        });



        buttonInClass_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass07Activity = new Intent(MainActivity.this,
                        InClass07_main.class);

                startActivity(toInClass07Activity);
            }
        });
    }
}