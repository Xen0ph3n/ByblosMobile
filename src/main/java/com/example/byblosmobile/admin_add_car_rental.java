package com.example.byblosmobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


// Class for the fragment responsible for adding cars to fleet
public class admin_add_car_rental extends Fragment{

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

    EditText vehicleMake;
    EditText vehicleModel;
    EditText numberPlate;
    EditText hourlySetRate;
    Spinner vehicleType;
    Button confirmAndAdd;


    public admin_add_car_rental() {
        // Required empty public constructor
    }

    public static admin_add_car_rental newInstance() {
        admin_add_car_rental fragment = new admin_add_car_rental();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Point to firebase path
        mDatabase = FirebaseDatabase.getInstance().getReference("Services/Cars");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // On create View, recover layout assignment by inflating layout
        View rootView = inflater.inflate(R.layout.fragment_admin_add_car_rental, container, false);



        // Get Views
        needCustomerName = (RadioButton) rootView.findViewById(R.id.needCustomerName);
        needDateOfBirth = (RadioButton) rootView.findViewById(R.id.needDateOfBirth);
        needCurrentAddress = (RadioButton) rootView.findViewById(R.id.needCurrentAddress);
        needEmail = (RadioButton) rootView.findViewById(R.id.needEmail);
        needLicense = (RadioButton) rootView.findViewById(R.id.needLicense);
        needTimeDuration = (RadioButton) rootView.findViewById(R.id.needTimeDuration);

        vehicleMake = (EditText) rootView.findViewById(R.id.vehicleMake);
        vehicleModel = (EditText) rootView.findViewById(R.id.vehicleModel);
        numberPlate = (EditText) rootView.findViewById(R.id.numberPlate);
        hourlySetRate = (EditText) rootView.findViewById(R.id.hourlySetRate);

        vehicleType = (Spinner) rootView.findViewById(R.id.vehicleType);

        confirmAndAdd = (Button) rootView.findViewById(R.id.confirmAndAdd);

        confirmAndAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On click of confirm and add, add data to database
                addData();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void addData()
    {
        DatabaseReference ref = mDatabase.push(); //Start database write process
        String identifier= ref.getKey(); //Get unique identifier key associated with node

        // Create carService object
        carService generatedCarService = new carService(hourlySetRate.getText().toString(),vehicleMake.getText().toString(),
                vehicleModel.getText().toString(),
                vehicleType.getSelectedItem().toString(),
                numberPlate.getText().toString(),
                needCustomerName.isChecked(),
                needDateOfBirth.isChecked(),
                needCurrentAddress.isChecked(),
                needEmail.isChecked(),
                needLicense.isChecked(),
                needTimeDuration.isChecked());

        generatedCarService.setIdentifier(identifier); // Set the unique identifier of that node in carService object

        mDatabase.child(identifier).setValue(generatedCarService); // Push the value to database node using the unique identifier key

        // Determine the activity responsible for this activities creation
        admin_car_rental parentFrag = ((admin_car_rental) admin_add_car_rental.this.getParentFragment());
        parentFrag.view_car(); // Access method from parents activity to show cars in fleet display

    }
}