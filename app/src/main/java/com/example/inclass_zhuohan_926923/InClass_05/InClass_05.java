package com.example.inclass_zhuohan_926923.InClass_05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inclass_zhuohan_926923.InClass_02.InClass02;
import com.example.inclass_zhuohan_926923.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InClass_05 extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ImageView imageViewShowBox, imageViewNext, imageViewPrevious;
    private ProgressBar progressBar;

    private TextView textView;

    private final OkHttpClient client = new OkHttpClient();

    private String keyword;

    private int index = 0;

    private String[] retrievedURL;

    private List<String> validSearch = Arrays.asList("boston", "san_francisco", "northeastern", "spring", "summer", "wonders", "surprise");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);

        setTitle("Image Search");

        editText = findViewById(R.id.InClass05_editText);
        button = findViewById(R.id.InClass05_button);
        imageViewNext = findViewById(R.id.InClass05_imageViewNext);
        imageViewPrevious = findViewById(R.id.InClass05_imageView_previous);
        imageViewShowBox = findViewById(R.id.InClass05_imageView_ShowBox);
        progressBar = findViewById(R.id.InClass05_progressBar);
        textView = findViewById(R.id.InClass05_textView);

        imageViewPrevious.setEnabled(false);
        imageViewNext.setEnabled(false);
        textView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 keyword = editText.getText().toString().toLowerCase();
                 textView.setVisibility(View.VISIBLE);
                 progressBar.setVisibility(View.VISIBLE);
                 if (!validSearch.contains(keyword)){
                     Toast.makeText(InClass_05.this,"Not a valid input!", Toast.LENGTH_LONG).show();
                 } else{
                     getImage(keyword, index);
                     imageViewPrevious.setEnabled(true);
                     imageViewNext.setEnabled(true);
//                     textView.setVisibility(View.INVISIBLE);
//                     progressBar.setVisibility(View.INVISIBLE);
                 }


            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Loading next...");
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                index += 1;
                keyword = editText.getText().toString().toLowerCase();
                if (index == retrievedURL.length){
                    index = 0;
                }
                getImage(keyword, index);
//                textView.setVisibility(View.INVISIBLE);
//                progressBar.setVisibility(View.INVISIBLE);


            }
        });

        imageViewPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Loading previous...");
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                index -= 1;
                keyword = editText.getText().toString().toLowerCase();
                if (index < 0){
                    index = retrievedURL.length - 1;
                }
                getImage(keyword, index);

            }
        });



    }

    public void getImage(String keyword, int index){
        HttpUrl url = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com/apis/images/retrieve")
                .newBuilder()
                .addQueryParameter("keyword", keyword)
                .build();


        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(InClass_05.this,"No Internet", Toast.LENGTH_LONG).show();
                   }
               });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    ResponseBody responseBody = response.body();
                    retrievedURL = responseBody.string().split("\\s+");

                    if (!retrievedURL[0].contains("https")){


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InClass_05.this,"No image found", Toast.LENGTH_LONG).show();
                                imageViewShowBox.setImageResource(R.drawable.angry);
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(InClass_05.this)
                                        .load(retrievedURL[index])
                                        .into(imageViewShowBox);
                                textView.setVisibility(View.INVISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }

                }
            }
        });
    }
}