package com.esq.workoutfragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

public class WorkoutListFragment extends ListFragment {


    public WorkoutListFragment() {
        // Required empty public constructor
    }

    //Adding a listener to the fragment
    public static interface WorkoutListListener{
        void itemClicked(long id);
    };

    private WorkoutListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Create a String array of workout names
        String [] names = new String[Workout.workouts.length];
        for (int i = 0; i<names.length; i++){
            names[i] = Workout.workouts[i].getName();
        }
        //Create an array adapter
        //inflater.getContext() -> gets the context from the layout inflater
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        //Bind the array adapter to the list view
        setListAdapter(adapter);
        //Calls the superclass onCreateView() method gives the default layout for the ListFragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context  context) {
        super.onAttach(context);
        //this.listener = (WorkoutListListener) context;
        if (context instanceof WorkoutListListener) {
            this.listener = (WorkoutListListener) context;
        } else {
            Toast.makeText(context,context.toString() + " must implemenet MyListFragment.OnItemSelectedListener", Toast.LENGTH_SHORT).show();
            throw new ClassCastException(context.toString() + " must implemenet MyListFragment.OnItemSelectedListener");

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        if (listener != null){
            //Tell the listener when an item in the ListView is clicked
            listener.itemClicked(id);
        }
    }

}
