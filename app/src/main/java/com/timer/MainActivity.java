package com.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Chronometer stopwatch;
    private boolean isRunning = false;
    private long offset = 0;

    private Button startButton;
    private Button resetButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatch = findViewById(R.id.stopwatch_view);
        stopwatch.setFormat("%s");
        stopwatch.setBase(SystemClock.elapsedRealtime());

        stopwatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - stopwatch.getBase()) >= 600000) {
                    stopwatch.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "BING BONG CHING CHONG!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startTimer(View view)
    {
        if(!isRunning){
            stopwatch.setBase(SystemClock.elapsedRealtime() - offset);
            System.out.println("Offset during start: " + offset);
            stopwatch.start();
            isRunning = true;
            startButton = findViewById(R.id.start_button);
            startButton.setText("START");
        }
    }

    @SuppressLint("SetTextI18n")
    public void stopTimer(View view)
    {
        System.out.println(isRunning);
        if(isRunning){
            stopwatch.stop();
            offset = SystemClock.elapsedRealtime() - stopwatch.getBase();
            System.out.println("Offset during stop: "+ offset);
            isRunning = false;
            startButton = findViewById(R.id.start_button);
            startButton.setText("RESUME");
        }
    }

    public void resetTimer(View view)
    {
        stopwatch.setBase(SystemClock.elapsedRealtime());
        offset = 0;
        isRunning = false;
    }
//        startButton.findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(!isRunning){
//                    stopwatch.setBase(SystemClock.elapsedRealtime() - offset);
//                    stopwatch.start();
//                    isRunning = true;
//                }
//            }
//        });
//
//        resetButton.findViewById(R.id.reset_button).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                stopwatch.setBase(SystemClock.elapsedRealtime());
//                offset = 0;
//                isRunning = false;
//            }
//        });
//
//        stopButton.findViewById(R.id.stop_button).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(isRunning){
//                    stopwatch.stop();
//                    offset = SystemClock.elapsedRealtime() - stopwatch.getBase();
//                    isRunning = false;
//                }
//            }
//        });

}