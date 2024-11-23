package com.example.hostel_magement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rooms_Retrive_Activity extends AppCompatActivity {

    TextView detailRoomtype, detailFloor_r,detailRoom_r;
    FloatingActionButton deleteButton_r,editBtn_r;
    String key = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_retrive);

        detailRoomtype = findViewById(R.id.retroomType);
        detailFloor_r = findViewById(R.id.retFloor_r);
        detailRoom_r = findViewById(R.id.retRoom_r);

        deleteButton_r = findViewById(R.id.deletebutton_r);
        editBtn_r = findViewById(R.id.editbutton_r);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailRoomtype.setText(bundle.getString("EnterRoomType"));
            detailFloor_r.setText(bundle.getString("FloorNo"));
            detailRoom_r.setText(bundle.getString("RoomNo"));

            key = bundle.getString("Key");


        }


        deleteButton_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Saved Rooms");


                databaseReference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Rooms_Retrive_Activity.this, "Room data deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Rooms.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Rooms_Retrive_Activity.this, "Failed to delete room record from database", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        editBtn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rooms_Retrive_Activity.this,Rooms_Uddate_Activity.class);
                intent.putExtra("EnterRoomType",detailRoomtype.getText().toString());
                intent.putExtra("FloorNo",detailFloor_r.getText().toString());
                intent.putExtra("RoomNo",detailRoom_r.getText().toString());

                intent.putExtra("Key",key);
                startActivity(intent);

            }
        });


    }
}