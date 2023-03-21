package com.example.inclass_zhuohan_926923.InClass_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.inclass_zhuohan_926923.R;

public class selectAvatar extends AppCompatActivity {

    private ImageView imageView_w1;
    private ImageView imageView_w2;
    private ImageView imageView_w3;
    private ImageView imageView_m1;
    private ImageView imageView_m2;
    private ImageView imageView_m3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        setTitle("Select Avatar");

        imageView_w1 = findViewById(R.id.imageView_f1);
        imageView_w2 = findViewById(R.id.imageView_f2);
        imageView_w3 = findViewById(R.id.imageView_f3);
        imageView_m1 = findViewById(R.id.imageView_m1);
        imageView_m2 = findViewById(R.id.imageView_m2);
        imageView_m3 = findViewById(R.id.imageView_m3);

        imageView_w1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToEditProfile = new Intent();
                backToEditProfile.putExtra("backToEdit", "f1");
                setResult(RESULT_OK, backToEditProfile);
                finish();
            }
        });

        imageView_w2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToEditProfile = new Intent();
                backToEditProfile.putExtra("backToEdit", "f3");
                setResult(RESULT_OK, backToEditProfile);
                finish();
            }
        });

        imageView_w3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToEditProfile = new Intent();
                backToEditProfile.putExtra("backToEdit", "f2");
                setResult(RESULT_OK, backToEditProfile);
                finish();
            }
        });

        imageView_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToEditProfile = new Intent();
                backToEditProfile.putExtra("backToEdit", "m1");
                setResult(RESULT_OK, backToEditProfile);
                finish();
            }
        });

        imageView_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToEditProfile = new Intent();
                backToEditProfile.putExtra("backToEdit", "m2");
                setResult(RESULT_OK, backToEditProfile);
                finish();
            }
        });

        imageView_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToEditProfile = new Intent();
                backToEditProfile.putExtra("backToEdit", "m3");
                setResult(RESULT_OK, backToEditProfile);
                finish();
            }
        });



    }
}