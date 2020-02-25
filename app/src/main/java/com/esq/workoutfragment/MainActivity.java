package com.esq.workoutfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  WorkoutDetailFragment frag = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
      //  frag.setWorkoutId(1);
    }

    @Override
    public void itemClicked(long id) {
        //Get a reference to the frame layout that contains WorkoutDetailFragment.
        //This will exist if the app is being run on a device with a large screen
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null){
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            //Start the fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setWorkout(id);
            //Replace the fragment and add it to the back stack
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            //Get the new and old fragments to fade in and out
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //Commit the transaction i.e save the changes to the activity
            ft.commit();
        }else{
            //If the frame layout isnt there, the app must be running on a device with a small screen,
            // Start DetailActivity passing it the ID of the workout
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }

    }
}
