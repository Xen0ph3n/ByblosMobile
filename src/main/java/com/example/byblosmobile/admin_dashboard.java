package com.example.byblosmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_dashboard extends AppCompatActivity {
    // Variable Initialization
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager = getSupportFragmentManager();
    static Context admin_context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        admin_context = getApplicationContext();
        setContentView(R.layout.activity_admin_dashboard);

        //Assignments
        drawerLayout = findViewById(R.id.admin_dashboard);

        // Display Welcome Fragment
        fragmentManager.beginTransaction()
                .replace(R.id.functionalityFragment, welcome_fragment.class, null)
                .setReorderingAllowed(true)
                .commit();
    }
    
    public void clickNavigationDrawer(View view)
    {
        //Open drawer
        openDrawer(drawerLayout);
    }

    public void manageServices(View view)
    {
        //Open Fragment for managing Services
        fragmentManager.beginTransaction()
                .replace(R.id.functionalityFragment, admin_manage_services_fragment.class, null)
                .setReorderingAllowed(true)
                .commit();
        closeDrawer(drawerLayout);
    }

    public void manageUsers(View view)
    {
        // Open Activity for managing Users

        fragmentManager.beginTransaction()
                .replace(R.id.functionalityFragment, admin_manage_users_fragment.class, null)
                .setReorderingAllowed(true)
                .commit();
        closeDrawer(drawerLayout);
    }

    public void accountSettings(View view)
    {
        // Open Activity for managing account settings

        fragmentManager.beginTransaction()
                .replace(R.id.functionalityFragment, admin_account_settings_fragment.class, null)
                .setReorderingAllowed(true)
                .commit();
        closeDrawer(drawerLayout);
    }

    public void logOut(View view)
    {
        // Execute commands for logging out
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        //Close drawer layout
        drawerLayout.closeDrawer(GravityCompat.START);
    }





}