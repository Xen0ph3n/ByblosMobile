package com.example.byblosmobile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class admin_view_car_rental extends Fragment {
    LinearLayout layoutToDraw;
    private DatabaseReference mDatabase;
    private MyDatabase myDatabase = new MyDatabase();
    ViewGroup.LayoutParams layoutParams;
    Context thiscontext;

    public admin_view_car_rental() {
        // Required empty public constructor
    }


    public static admin_view_car_rental newInstance() {
        admin_view_car_rental fragment = new admin_view_car_rental();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Point to database location
        mDatabase = FirebaseDatabase.getInstance().getReference("Services");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On create View, recover layout assignment by inflating layout
        View rootView = inflater.inflate(R.layout.fragment_admin_view_car_rental, container, false);

        // Assign Layout in XML that will be populated programmatically
        layoutToDraw = (LinearLayout) rootView.findViewById(R.id.populate);


        generateItems(layoutToDraw); // Call method responsible for displaying cars in fleet
        // Inflate the layout for this fragment
        thiscontext = container.getContext(); // Set context of this view
        layoutParams = rootView.getLayoutParams(); //Get layout parameters and restriction for the parent XML file
        return rootView;
    }

    public void generateItems (LinearLayout layoutToDraw)
    {
        // Get UID of Car services and store in array
        mDatabase.child("Cars").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Create array storing all the car service types retired
                ArrayList<carService> cars = new ArrayList<>();

                for(DataSnapshot postSnapshot: snapshot.getChildren())
                {
                    // Get value from node
                    carService val = postSnapshot.getValue(carService.class);
                    cars.add(val); // Add to list
                }
                // Call method responsible for drawing objects
                createItem(cars, layoutToDraw);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void createItem(ArrayList<carService> cars, LinearLayout layoutToDraw)
    {
        // Iterate through list of carService type
        for(int i = 0; i < cars.size(); i++)
        {
            // Create linear layout to store generated elements
            LinearLayout linearContainer = new LinearLayout(thiscontext);
            linearContainer.setPadding(10, 10,10,10);
            linearContainer.setOrientation(LinearLayout.VERTICAL);

            // Add various elements from carService type to layout using constructor method
            linearContainer.addView(createTextView(cars.get(i).getMake()));
            linearContainer.addView(createTextView(cars.get(i).getModel()));
            linearContainer.addView(createTextView("Type: " + cars.get(i).getType()));
            linearContainer.addView(createTextView("Number Plate: " + cars.get(i).getNumberPlate()));
            linearContainer.addView(createTextView("$"+ cars.get(i).getPrice() + " per hour"));

            linearContainer.addView(createTextView("Unique Identification: "+ cars.get(i).getIdentifier()));

            // Create button to delete object
            Button delete = new Button(thiscontext);
            delete.setText("DELETE");

            // Get identification of current carService in list
            String identification = cars.get(i).getIdentifier();

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                // On delete click, call method responsible for deleting object
                public void onClick(View v) {
                    deleteCarService(identification);
                }
            });
            linearContainer.addView(delete);

            // Add container layout to the main layout
            layoutToDraw.addView(linearContainer);
        }

    }
    // Method to create TextViews with set parameters
    public TextView createTextView(String value)
    {

        // Create a text view with set construction
        TextView text = new TextView(thiscontext);
        text.setText(value);
        text.setTextSize(15);
        text.setWidth(layoutParams.MATCH_PARENT);
        return text;
    }

    public void deleteCarService(String identifier)
    {

        // Remove carService from database
        mDatabase.child("Cars/"+identifier).removeValue();


        // Refresh layout
        layoutToDraw.removeAllViews();
        generateItems(layoutToDraw);
    }
}