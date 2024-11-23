package com.example.hostel_magement.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hostel_magement.MainActivity;
import com.example.hostel_magement.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              /* if(FirebaseAuth.getInstance().getCurrentUser()!=null)
               { }*/
                   startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                   finish();

            }
        }, 1000);
    }
}