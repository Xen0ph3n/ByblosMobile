package com.example.byblosmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {

  private String userType = ""; // Hold string value of user type (_employee, _customer, _admin)
  private FirebaseAuth mAuth; // Fire authentication object
  private DatabaseReference mDatabase; // Database object

  private MyDatabase myDatabase = new MyDatabase();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    mAuth = FirebaseAuth.getInstance(); // initialize authentication object
    mDatabase = FirebaseDatabase.getInstance().getReference(); // Point to database


    // references to radio button(s)
    final RadioButton EMPLOYEE_BUTTON = findViewById(R.id.employeeRadioButton);
    final RadioButton CUSTOMER_BUTTON = findViewById(R.id.customerRadioButton);

    // reference to register button
    // button_2 is for the actual sign-up page, button_1 is just for redirection
    final Button REGISTER_BUTTON = findViewById(R.id.registerButton2);

    // references to data entries
    EditText firstName = findViewById(R.id.firstNameInput);
    EditText lastName = findViewById(R.id.lastNameInput);
    EditText email = findViewById(R.id.emailInput);
    EditText password = findViewById(R.id.passwordInput);
    EditText userName = findViewById(R.id.usernameInput);


    // Event listener for employee button click
    EMPLOYEE_BUTTON.setOnClickListener(v -> { // On click function
      userType = "_employee";
      CUSTOMER_BUTTON.setChecked(false);
      EMPLOYEE_BUTTON.setChecked(true);
    });

    // Event listener for customer button click
    CUSTOMER_BUTTON.setOnClickListener(v -> { // On click function
      userType = "_customer";
      EMPLOYEE_BUTTON.setChecked(false);
      CUSTOMER_BUTTON.setChecked(true);
    });

    REGISTER_BUTTON.setOnClickListener(v -> userRegistration(userName, firstName, lastName, email, password, userType));
  }

  // method that handles user registration
  private void userRegistration(EditText userName, EditText firstName, EditText lastName, EditText email, EditText password, String userType) {

    String emailStr = email.getText().toString().trim();
    String passwordStr = password.getText().toString().trim();
    String username = userName.getText().toString().trim();
    String uid = null;

    // Input validity check
    if (!Utilities.nameChecker(firstName, lastName)) {
      Toast.makeText(SignupActivity.this, "Name fields cannot contain ASCII symbols. i.e. _*()/> ", Toast.LENGTH_LONG).show();
      return;
    }

    // Creates userInfo Object with the additional data fields
    UserInfo userInfo = new UserInfo(userName.getText().toString().trim(),
            firstName.getText().toString().trim(),
            lastName.getText().toString().trim(),
            emailStr, passwordStr, userType);

    createUser(emailStr, passwordStr, userInfo);



    /* mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(task -> {

      if (task.isSuccessful()) {
        // Checks if registering user was successful and then adds other fields to database
        Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

        // Creates userInfo Object with the additional data fields
        UserInfo userInfo = new UserInfo(
                firstName.getText().toString().trim(),
                lastName.getText().toString().trim(),
                emailStr, passwordStr, userType
        );


        // Adding user info
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) return;
        myDatabase.addUser(currentUser, userInfo);

        // Jump to welcome page
        startActivity(new Intent(SignupActivity.this, WelcomePageActivity.class));

      } else {
        Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
      }
    });*/
  }

  public void createUser(String email, String password, UserInfo userInfo)
  {
    // Create user in database using m.auth
    // Password will be encrypted using PasswordManager (allows for user to input pass.len < 6 char)

    mAuth.createUserWithEmailAndPassword(email, PasswordManager.encrypt(password)).addOnCompleteListener(task -> {
      if(task.isSuccessful())
      {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null)
        {
          myDatabase.addUser(currentUser, userInfo);
        }else
          {
            return;
          }
        startActivity(new Intent(SignupActivity.this, WelcomePageActivity.class));
      }
      else
        {
          Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
        }
    });

  }




}