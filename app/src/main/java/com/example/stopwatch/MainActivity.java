package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;
    private Button startButton;
    private Button resetButton;

    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        isRunning = true;
        startButton.setText("Stop");
        resetButton.setEnabled(false);
        handler.postDelayed(updateTimer, 1000);
    }

    private void stopTimer() {
        isRunning = false;
        startButton.setText("Start");
        resetButton.setEnabled(true);
        handler.removeCallbacks(updateTimer);
    }

    private void resetTimer() {
        isRunning = false;
        startButton.setText("Start");
        resetButton.setEnabled(false);
        seconds = 0;
        updateDisplay();
    }

    private Runnable updateTimer = new Runnable() {
        public void run() {
            seconds++;
            updateDisplay();
            handler.postDelayed(this, 1000);
        }
    };

    private void updateDisplay() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timeTextView.setText(time);
    }
}
