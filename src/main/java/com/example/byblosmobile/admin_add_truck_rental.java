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

public class admin_add_truck_rental extends Fragment {

    // Database Reference
    private DatabaseReference mDatabase;
    private MyDatabase myDatabase = new MyDatabase();

    // References
    RadioButton needCustomerName;
    RadioButton needDateOfBirth;
    RadioButton needCurrentAddress;
    RadioButton needEmail;
    RadioButton needLicense;
    RadioButton needTimeDuration;
    RadioButton needAreaOfOperation;

    EditText vehicleMake;
    EditText vehicleModel;
    EditText numberPlate;
    EditText hourlySetRate;
    EditText distanceRestriction;
    Spinner truckType;
    Button confirmAndAdd;


    public admin_add_truck_rental() {
        // Required empty public constructor
    }

    public static admin_add_truck_rental newInstance() {
        admin_add_truck_rental fragment = new admin_add_truck_rental();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Point to firebase path
        mDatabase = FirebaseDatabase.getInstance().getReference("Services/Trucks");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_add_truck_rental, container, false);

        needCustomerName = (RadioButton) rootView.findViewById(R.id.needCustomerName);
        needDateOfBirth = (RadioButton) rootView.findViewById(R.id.needDateOfBirth);
        needCurrentAddress = (RadioButton) rootView.findViewById(R.id.needCurrentAddress);
        needEmail = (RadioButton) rootView.findViewById(R.id.needEmail);
        needLicense = (RadioButton) rootView.findViewById(R.id.needLicense);
        needTimeDuration = (RadioButton) rootView.findViewById(R.id.needTimeDuration);
        needAreaOfOperation = (RadioButton) rootView.findViewById(R.id.areaOfOperation);

        vehicleMake = (EditText) rootView.findViewById(R.id.vehicleMake);
        vehicleModel = (EditText) rootView.findViewById(R.id.vehicleModel);
        numberPlate = (EditText) rootView.findViewById(R.id.numberPlate);
        hourlySetRate = (EditText) rootView.findViewById(R.id.hourlySetRate);
        distanceRestriction = (EditText) rootView.findViewById(R.id.distanceRestriction);

        truckType = (Spinner) rootView.findViewById(R.id.truckType);

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
        truckService generatedTruckService = new truckService(hourlySetRate.getText().toString(),vehicleMake.getText().toString(),
                vehicleModel.getText().toString(),
                truckType.getSelectedItem().toString(),
                numberPlate.getText().toString(),
                distanceRestriction.getText().toString(),
                needCustomerName.isChecked(),
                needDateOfBirth.isChecked(),
                needCurrentAddress.isChecked(),
                needEmail.isChecked(),
                needLicense.isChecked(),
                needTimeDuration.isChecked(),
                needAreaOfOperation.isChecked());

        generatedTruckService.setIdentifier(identifier); // Set the unique identifier of that node in carService object

        mDatabase.child(identifier).setValue(generatedTruckService); // Push the value to database node using the unique identifier key

        // Determine the activity responsible for this activities creation
        admin_truck_rental parentFrag = ((admin_truck_rental) admin_add_truck_rental.this.getParentFragment());
        parentFrag.view_truck(); // Access method from parents activity to show cars in fleet display

    }
}
