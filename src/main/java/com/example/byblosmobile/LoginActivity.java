package com.example.byblosmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
  private FirebaseAuth mAuth; // Fire authentication object
  private DatabaseReference mDatabase; // Database object
//  private FirebaseUser mUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mDatabase = FirebaseDatabase.getInstance().getReference(); // Point to database

    mAuth = FirebaseAuth.getInstance(); // initialize authentication object
//    FirebaseAuth.AuthStateListener mAuthListener;
//    mUser = FirebaseAuth.getInstance().getCurrentUser();

    //References to inputs
    EditText email = findViewById(R.id.emailInput_login);
    EditText password = findViewById(R.id.passwordInput_login);

    //Reference to Login Button
    final Button LOGIN_BUTTON = findViewById(R.id.loginButton2);

    LOGIN_BUTTON.setOnClickListener(v -> signIn(email, password));

//    LOGIN_BUTTON.setOnClickListener(v -> signIn2());

  }

  //Sign in method
  private void signIn(EditText email, EditText password) {
//  private void signIn2() {

    String emailStr = email.getText().toString().trim();
    String passwordStr = password.getText().toString().trim();

    if (Patterns.EMAIL_ADDRESS.matcher(emailStr).matches())
    {
      login(emailStr, passwordStr); // If the detected input is an email
    }
    else
      {
        /*
          Retrieve key or parent based on the username inputted
          After key is found, find email associated with the username and login
        */
        Query query = mDatabase.child("Users").orderByChild("userName").equalTo(emailStr);
        ValueEventListener valueEventListener = new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot ds: snapshot.getChildren())
            {
              String key = ds.getKey();
              Log.d("Key we got", key);

              DatabaseReference email = mDatabase.child("Users").child(key).child("email");

              email.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                  String emailFound = snapshot.getValue(String.class);
                  Log.d("Email Found", emailFound);

                  login(emailFound, passwordStr);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
              });


            }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
      }

  }
  public void login(String emailStr, String passwordStr)
  {
    mAuth.signInWithEmailAndPassword(emailStr, PasswordManager.encrypt(passwordStr)).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {

        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

        // @kurtis: redirect to the Welcome page
        startActivity(new Intent(LoginActivity.this, WelcomePageActivity.class));

      } else {
        Toast.makeText(LoginActivity.this, "Login not Successful", Toast.LENGTH_SHORT).show();
      }
    });
  }
}