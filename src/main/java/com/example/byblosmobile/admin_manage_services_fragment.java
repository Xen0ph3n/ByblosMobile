package com.example.byblosmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class admin_manage_services_fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public admin_manage_services_fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // On create View, recover layout assignment by inflating layout
        View view =  inflater.inflate(R.layout.fragment_admin_manage_services_fragment, container, false);

        //Assignments
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        // Setup tabs with support class
        VPAdapter vpAdapter = new VPAdapter(getChildFragmentManager());
        vpAdapter.addFragment(new admin_car_rental(), "Car Rental");
        vpAdapter.addFragment(new admin_truck_rental(), "Truck Rental");
        vpAdapter.addFragment(new admin_moving_assistance(), "Moving Assistance");

        viewPager.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // Inflate the layout for this fragment
        return view;


    }
}






















