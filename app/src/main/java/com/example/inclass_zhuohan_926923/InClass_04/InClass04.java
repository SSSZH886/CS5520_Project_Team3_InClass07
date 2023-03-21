package com.example.inclass_zhuohan_926923.InClass_04;

import static java.lang.Math.round;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.inclass_zhuohan_926923.R;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InClass04 extends AppCompatActivity {

    private TextView textViewComplexity, textViewMinimum, textViewMaximum, textViewAverage;
    private SeekBar seekBar;
    private ProgressBar progressBar;
    private Button buttonGenerateNumber;

    private ExecutorService threadPool;

    private Handler messageQueue;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class04);

        setTitle("Number Generator");

        textViewComplexity = findViewById(R.id.InClass04_textView_Complexity);
        textViewMinimum = findViewById(R.id.InClass04_textView_Minimum);
        textViewMaximum = findViewById(R.id.InClass04_textView_Maximum);
        textViewAverage = findViewById(R.id.InClass04_textView_Average);
        seekBar = findViewById(R.id.InClass04_seekBar);
        progressBar = findViewById(R.id.InClass04_progressBar);
        buttonGenerateNumber = findViewById(R.id.InClass04_button);

        threadPool = Executors.newFixedThreadPool(1);
        messageQueue = new Handler(Looper.getMainLooper(),new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch(message.what){
                    case HeavyWork.STATUS_START:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case HeavyWork.STATUS_END:
                        Bundle data = message.getData();
                        double maximum = data.getDouble(HeavyWork.MAXIMUM);
                        double minimum = data.getDouble(HeavyWork.MINIMUM);
                        double average = data.getDouble(HeavyWork.AVERAGE);
                        textViewMinimum.setText(round(minimum, 2) + "");
                        textViewMaximum.setText(round(maximum, 2) + "");
                        textViewAverage.setText(round(average, 2) + "");
                        progressBar.setVisibility(View.GONE);
                        break;
                    case HeavyWork.STATUS_PROGRESS:
                        Bundle receivedData = message.getData();

                        int progress = receivedData.getInt(HeavyWork.KEY_PROGRESS);

                        progressBar.setProgress(progress);

                        break;
                }
                return false;
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewComplexity.setText((i+1) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonGenerateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer complexity = Integer.valueOf(String.valueOf(textViewComplexity.getText()));
                threadPool.execute(new HeavyWork("Do Heavy work",messageQueue, complexity));
            }
        });


    }
}