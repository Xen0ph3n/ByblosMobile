package com.example.byblosmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_add_moving_assistance extends Fragment {

    // Database Reference
    private DatabaseReference mDatabase;
    private MyDatabase myDatabase = new MyDatabase();

    //References
    RadioButton needCustomerName;
    RadioButton needDateOfBirth;
    RadioButton needCurrentAddress;
    RadioButton needEmail;
    RadioButton needTimeDuration;
    RadioButton needStartAndEndLocation;
    RadioButton needNumberOfBoxes;

    EditText numberOfMovers;
    EditText hourlySetRate;
    Button confirmAndAdd;

    public admin_add_moving_assistance() {
        // Required empty public constructor
    }

    public static admin_add_moving_assistance newInstance() {
        admin_add_moving_assistance fragment = new admin_add_moving_assistance();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Point to firebase path
        mDatabase = FirebaseDatabase.getInstance().getReference("Services/Moving Assistance");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_add_moving_assistance, container, false);

        //Get views
        needCustomerName = (RadioButton) rootView.findViewById(R.id.needCustomerName);
        needDateOfBirth = (RadioButton) rootView.findViewById(R.id.needDateOfBirth);
        needCurrentAddress = (RadioButton) rootView.findViewById(R.id.needCurrentAddress);
        needEmail = (RadioButton) rootView.findViewById(R.id.needEmail);
        needTimeDuration = (RadioButton) rootView.findViewById(R.id.needTimeDuration);
        needStartAndEndLocation = (RadioButton) rootView.findViewById(R.id.needTimeDuration);
        needNumberOfBoxes = (RadioButton) rootView.findViewById(R.id.needExpectedNumberOfBoxes);

        numberOfMovers = (EditText) rootView.findViewById(R.id.numberOfMovers);
        hourlySetRate = (EditText) rootView.findViewById(R.id.hourlySetRate);


        confirmAndAdd = (Button) rootView.findViewById(R.id.confirmAndAdd);

        confirmAndAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On click of confirm and add, add data to database
                addData();
            }
        });
        return rootView;
    }

    public void addData()
    {
        DatabaseReference ref = mDatabase.push(); //Start database write process
        String identifier= ref.getKey(); //Get unique identifier key associated with node

        // Create carService object
       movingServices generatedMovingService = new movingServices(hourlySetRate.getText().toString(),
                numberOfMovers.getText().toString(),
                needCustomerName.isChecked(),
                needDateOfBirth.isChecked(),
                needCurrentAddress.isChecked(),
                needEmail.isChecked(),
                needTimeDuration.isChecked(),
                needStartAndEndLocation.isChecked(),
                needNumberOfBoxes.isChecked());

        generatedMovingService.setIdentifier(identifier); // Set the unique identifier of that node in carService object

        mDatabase.child(identifier).setValue(generatedMovingService); // Push the value to database node using the unique identifier key

        // Determine the activity responsible for this activities creation
        admin_moving_assistance parentFrag = ((admin_moving_assistance) admin_add_moving_assistance.this.getParentFragment());
        parentFrag.view_team(); // Access method from parents activity to show moving teams

    }

}