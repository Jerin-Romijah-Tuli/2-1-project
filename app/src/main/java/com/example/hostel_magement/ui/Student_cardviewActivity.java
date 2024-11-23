package com.example.hostel_magement.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hostel_magement.R;

public class Student_cardviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_cardview);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        CardView viewHostelcardView = findViewById(R.id.cardView_view_hostel);
        viewHostelcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(Student_cardviewActivity.this, View_Hostel1_Activity.class);
                startActivity(intent);
            }
        });


    }
}