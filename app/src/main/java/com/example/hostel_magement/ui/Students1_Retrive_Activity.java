package com.example.hostel_magement.ui;

import static java.nio.file.Files.delete;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.internal.StorageReferenceUri;

public class Students1_Retrive_Activity extends AppCompatActivity {

    TextView detailName, detailFloor,detailRoom, detailMobile, detailInstitute, detailAddress;
    FloatingActionButton deleteButton,editBtn;
    String key = "";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students1_retrive);

        detailName = findViewById(R.id.retName);
        detailFloor = findViewById(R.id.retFloor);
        detailRoom = findViewById(R.id.retRoom);
        detailMobile = findViewById(R.id.retMobileNo);
        detailInstitute = findViewById(R.id.retInstitute);
        detailAddress = findViewById(R.id.retAddress);
        deleteButton = findViewById(R.id.deletebutton);
       editBtn = findViewById(R.id.editbutton);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailName.setText(bundle.getString("Name"));
            detailFloor.setText(bundle.getString("FloorNo"));
            detailRoom.setText(bundle.getString("RoomNo"));
            detailMobile.setText(bundle.getString("MobileNo"));
            detailInstitute.setText(bundle.getString("InstituteName"));
            detailAddress.setText(bundle.getString("PermanentAddress"));
            key = bundle.getString("Key");


        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Saved Students");


                databaseReference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Students1_Retrive_Activity.this, "Student data deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Students.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Students1_Retrive_Activity.this, "Failed to delete student record from database", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Students1_Retrive_Activity.this,Students1_UpdateActivity.class);
                        intent.putExtra("Name",detailName.getText().toString());
                       intent.putExtra("FloorNo",detailFloor.getText().toString());
                       intent.putExtra("RoomNo",detailRoom.getText().toString());
                intent.putExtra("MobileNo",detailMobile.getText().toString());
                intent.putExtra("InstituteName",detailInstitute.getText().toString());
                intent.putExtra("PermanentAddress",detailAddress.getText().toString());
                intent.putExtra("Key",key);
                     startActivity(intent);

            }
        });


    }
}