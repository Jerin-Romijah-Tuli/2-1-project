package com.example.hostel_magement.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hostel_magement.MainActivity;
import com.example.hostel_magement.R;
import com.example.hostel_magement.databinding.ActivityLoginBinding;

public class ChooseActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private Button button1;

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        button1=(Button) findViewById(R.id.OwnBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseActivity.this, RegistrationActivity.class));
                finish();
            }
        });


        button2=(Button) findViewById(R.id.StuBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseActivity.this, GuestActivity.class));
                finish();
            }
        });
    }
}