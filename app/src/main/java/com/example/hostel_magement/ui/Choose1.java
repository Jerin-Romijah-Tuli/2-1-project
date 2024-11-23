package com.example.hostel_magement.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hostel_magement.R;
import com.example.hostel_magement.databinding.ActivityLoginBinding;

public class Choose1 extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private Button button1;

    private Button button2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose1);
        button1=(Button) findViewById(R.id.OwBtn_c);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Choose1.this, OwnerpageActivity.class));
                finish();
            }
        });


        button2=(Button) findViewById(R.id.StBtn_c);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Choose1.this, Student_cardviewActivity.class));
                finish();
            }
        });
    }
}