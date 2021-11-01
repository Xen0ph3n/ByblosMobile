package com.example.byblosmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class admin_truck_rental extends Fragment {

    Fragment truck_add = new admin_add_truck_rental(); //Points to fragment responsible for adding cars in fleet
    Fragment truck_view= new admin_view_truck_rental(); //Points to fragment responsible for viewing cars in fleet
    FloatingActionButton floatingActionButton;

    public admin_truck_rental() {
        // Required empty public constructor
    }

    public static admin_truck_rental newInstance() {
        admin_truck_rental fragment = new admin_truck_rental();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On create View, recover layout assignment by inflating layout
        View rootView = inflater.inflate(R.layout.fragment_admin_truck_rental, container, false);

        //Assignments
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_truck_service);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On floating action button (+) hide button and display car adding process
                floatingActionButton.hide();
                add_truck();
            }
        });


        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set default view as displaying cars in fleet
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, truck_view).commit();
    }

    public void add_truck()
    {
        // Display view of adding car
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, truck_add).commit();

    }

    public void view_truck()
    {
        //Set floating action button (+) visible button and display car viewing process
        floatingActionButton.show();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, truck_view).commit();

    }
}