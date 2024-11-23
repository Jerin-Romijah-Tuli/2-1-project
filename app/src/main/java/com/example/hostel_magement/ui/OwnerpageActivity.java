package com.example.hostel_magement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hostel_magement.R;
import com.example.hostel_magement.databinding.ActivityOwnerpageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OwnerpageActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Students students = new Students();
    Rooms rooms = new Rooms();
    LogoutFragment logoutFragment = new LogoutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ownerpage);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout, students).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                switch (item.getItemId()){
                    case R.id.students:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout, students).commit();
                        return true;
                    case R.id.rooms:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout, rooms).commit();
                        return true;

                    case R.id.logout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout, logoutFragment).commit();
                        return true;

                }
                return false;
            }
        });


    }

}







