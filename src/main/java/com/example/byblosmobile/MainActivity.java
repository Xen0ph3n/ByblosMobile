package com.example.byblosmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.byblosmobile.databinding.ActivityLoginBinding;

public class MainActivity extends AppCompatActivity {

  private ActivityLoginBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Button assignments to id
    final Button LOGIN_BUTTON = findViewById(R.id.loginButton1);
    final Button REGISTER_BUTTON = findViewById(R.id.loginButton2);

    // goto log-in
    LOGIN_BUTTON.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    // goto sign-up
    REGISTER_BUTTON.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignupActivity.class)));
  }
}