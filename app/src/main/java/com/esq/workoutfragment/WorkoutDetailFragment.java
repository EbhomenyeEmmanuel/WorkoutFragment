package com.esq.workoutfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
   //This is the id of the workout the user chooses
    private  long workoutId;
    private TextView title;;
    private TextView description;
    public WorkoutDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId");
        }

        StopWatchFragment stopWatchFragment = new StopWatchFragment();
        //Start the fragment transaction
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        //Replace the fragment and add it to the back stack
        ft.replace(R.id.stopwatch_container, stopWatchFragment);
        ft.addToBackStack(null);
        //Get the new and old fragments to fade in and out
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //Commit the transaction i.e save the changes to the activity
        ft.commit();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view!= null){
            title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            description = (TextView) view.findViewById(R.id.textDescription);;
            description.setText(workout.getDescription());
        }
    }

    @Override
    public String toString() {
        return "Title: "+ title + "Description: " + description;
    }

    public void setWorkout(long id){
        this.workoutId = id;
    }

    //Save the value of the workoutId in the savedInstance Bundle b4 the fragment gets destroyed
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutId", workoutId);
    }
}
