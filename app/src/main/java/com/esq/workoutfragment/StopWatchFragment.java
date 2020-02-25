package com.esq.workoutfragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchFragment extends Fragment implements View.OnClickListener{
    //Number of seconds displayed on the stopwatch.
    private int seconds = 0;
    //Is the stopwatch working?
    private boolean running;
    private boolean wasRunning;

    public StopWatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);
        Button startButton = layout.findViewById(R.id.start);
        startButton.setOnClickListener(this);
        Button stopButton = layout.findViewById(R.id.stop);
        stopButton.setOnClickListener(this);
        Button resetButton = layout.findViewById(R.id.reset);
        resetButton.setOnClickListener(this);
        return layout ;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (wasRunning)
            running = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        //wasRunning was given a value b4 the activity stops
        wasRunning = running;
        running = false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", this.seconds);
        savedInstanceState.putBoolean("running", this.running);
        //The value of wasRunning was set in the onStop method ->
        // after the activity was recreated
        savedInstanceState.putBoolean("wasRunning", this.wasRunning);
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(View view){
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d : %02d : %02d", hours, minutes, secs);
                timeView.setText(time);
                //The value of running is set to the in the onCLickStart method
                if (running)
                    seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.start:
                onClickStart(v);
                break;
            case R.id.stop:
                onClickStop(v);
                break;
            case R.id.reset:
                onClickReset(v);
                break;
        }
    }
}
