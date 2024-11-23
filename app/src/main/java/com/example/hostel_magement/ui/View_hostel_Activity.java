package com.example.hostel_magement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class View_hostel_Activity extends AppCompatActivity {

    TextView detailHostelName, detailLocation, detailMobile_h, detailFacility, detailSafety, detailFood;

    String key = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hostel);

        detailHostelName = findViewById(R.id.retHostelname);
        detailLocation = findViewById(R.id.retLocation);
        detailMobile_h = findViewById(R.id.retMobile_h);
        detailFacility = findViewById(R.id.retFacality);
        detailSafety = findViewById(R.id.retSafety);
        detailFood = findViewById(R.id.retFood);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailHostelName.setText(bundle.getString("HostelName"));
            detailLocation.setText(bundle.getString("Location"));
            detailMobile_h.setText(bundle.getString("Mobile"));
            detailFacility.setText(bundle.getString("Facility"));
            detailSafety.setText(bundle.getString("Safety"));
            detailFood.setText(bundle.getString("Food"));
            key = bundle.getString("Key");



        }
    }
}