package com.example.inclass_zhuohan_926923.InClass_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inclass_zhuohan_926923.R;

public class Display extends AppCompatActivity {

    private TextView textView_name;
    private TextView textView_email;
    private TextView textView_phone;
    private TextView textView_mood;
    private ImageView imageView_avatar;
    private ImageView imageView_mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        setTitle("Display Activity");

        textView_name = findViewById(R.id.textView_name_display);
        textView_email = findViewById(R.id.textView_display_email);
        textView_phone = findViewById(R.id.textView_phone);
        textView_mood = findViewById(R.id.textView_moooood);
        imageView_avatar = findViewById(R.id.imageView_display);
        imageView_mood = findViewById(R.id.imageView_display_mood);


        if (getIntent() != null && getIntent().getExtras() != null){
            User input = getIntent().getParcelableExtra(InClass02.userKey);
            textView_name.setText(new StringBuilder().append("Name: ").append(input.name).toString());
            textView_email.setText(new StringBuilder().append("Email: ").append(input.email).toString());
            textView_phone.setText(new StringBuilder().append("I use ").append(input.device).toString());
            textView_mood.setText(new StringBuilder().append("I am ").append(input.mood).toString());

            if (input.avatar_image.equals("f1")){
                imageView_avatar.setImageResource(R.drawable.avatar_f_1);
            }else if (input.avatar_image.equals("f3")){
                imageView_avatar.setImageResource(R.drawable.avatar_f_2);
            }else if (input.avatar_image.equals("f2")){
                imageView_avatar.setImageResource(R.drawable.avatar_f_3);
            }else if (input.avatar_image.equals("m1")){
                imageView_avatar.setImageResource(R.drawable.avatar_m_1);
            }else if (input.avatar_image.equals("m2")){
                imageView_avatar.setImageResource(R.drawable.avatar_m_2);
            }else if (input.avatar_image.equals("m3")){
                imageView_avatar.setImageResource(R.drawable.avatar_m_3);
            }

            if (input.mood.equals("Sad")){
                imageView_mood.setImageResource(R.drawable.sad);
            }else if (input.mood.equals("Awesome")){
                imageView_mood.setImageResource(R.drawable.awesome);
            }else if (input.mood.equals("Angry")){
                imageView_mood.setImageResource(R.drawable.angry);
            }else if (input.mood.equals("Happy")){
                imageView_mood.setImageResource(R.drawable.happy);
            }

        }
    }
}