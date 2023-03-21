package com.example.inclass_zhuohan_926923.InClass_04;

import static java.util.Collections.min;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HeavyWork implements Runnable {

    private String message;
    private Handler messageQueue;
    private int complexity;

    public final static int STATUS_START = 0x001;
    public final static int STATUS_PROGRESS = 0x002;
    public final static int STATUS_END = 0x003;
    public final static String KEY_PROGRESS = "0x004";
    public final static String MINIMUM = "MINIMUM";
    public final static String MAXIMUM = "MAXIMUM";
    public final static String AVERAGE = "AVERAGE";
    static final int COUNT = 9000000;
    static ArrayList<Double> getArrayNumbers(int n){
        ArrayList<Double> returnArray = new ArrayList<>();

        for (int i=0; i<n; i++){
            returnArray.add(getNumber());
        }

        return returnArray;
    }

    static double getNumber(){
        double num = 0;
        Random ran = new Random();
        for(int i=0;i<COUNT; i++){
            num = num + (Math.random()*ran.nextDouble()*100+ran.nextInt(50))*1000;
        }
        return num / ((double) COUNT);
    }



    public HeavyWork(String message, Handler messageQueue, int complexity){
        this.message = message;
        this.messageQueue = messageQueue;
        this.complexity = complexity;
    }

    @Override
    public void run() {
        Message startMessage = new Message();
        startMessage.what = STATUS_START;
        messageQueue.sendMessage(startMessage);
        for (int i=0; i <100; i++){
            for (int j=0; j <10000000; j++){

            }
            Message progressMessage = new Message();
            progressMessage.what = STATUS_PROGRESS;
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_PROGRESS, i);

            progressMessage.setData(bundle);
            messageQueue.sendMessage(progressMessage);
        }
        Message endMessage = new Message();
        endMessage.what = STATUS_END;
        Bundle bundle2 = new Bundle();
        bundle2.putDouble(MINIMUM, Collections.min(getArrayNumbers(complexity)));
        bundle2.putDouble(MAXIMUM, Collections.max(getArrayNumbers(complexity)));
        bundle2.putDouble(AVERAGE, (getArrayNumbers(complexity).stream()
                .mapToDouble(Double::doubleValue)
                .sum())/complexity);
        endMessage.setData(bundle2);
        messageQueue.sendMessage(endMessage);
    }

    // public static void main(String[] args) {
    //     ArrayList<Double> arrayList = new ArrayList<>();
    //     arrayList = getArrayNumbers(200);
    //     for(double num: arrayList){
    //         System.out.println(num);
    //     }
    // }
}