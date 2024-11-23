package com.example.hostel_magement.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Students1_UpdateActivity extends AppCompatActivity {

    Button updatebtn;
    EditText updName,updFloor,updRoom,updMobile,updInstitute,updAddress;
    String name,floor,room,mobile,institute,address;
    String key;
    DatabaseReference databaseReference;
    StorageReference storageReference;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students1_update);

        updatebtn = findViewById(R.id.updateButton);
        updName = findViewById(R.id.updateStuname);
        updFloor = findViewById(R.id.updateFloorno);
        updRoom = findViewById(R.id.updateRoomno);
        updMobile = findViewById(R.id.updateMobile);
        updInstitute = findViewById(R.id.updateInstitute);
        updAddress = findViewById(R.id.updateAddeess);



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            //Intent data = result.getData();


                        }

                    }
                }
        );

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            updName.setText(bundle.getString("Name"));
            updFloor.setText(bundle.getString("FloorNo"));
            updRoom.setText(bundle.getString("RoomNo"));
            updMobile.setText(bundle.getString("MobileNo"));
            updInstitute.setText(bundle.getString("InstituteName"));
            updAddress.setText(bundle.getString("PermanentAddress"));
            key = bundle.getString("Key");

        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Saved Students").child(key);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (savedInstanceState == null) {
                    // Create an instance of the Students fragment
                    Students studentsFragment = new Students();

                    // Get the FragmentManager
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    // Begin a transaction to replace the existing fragment (if any) with Students
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                   // transaction.replace(R.id.studentsCon, studentsFragment).commit();

                }

            }
        });




    }

    public void updateData(){
        name = updName.getText().toString().trim();
        floor = updFloor.getText().toString().trim();
        room = updRoom.getText().toString().trim();
        mobile = updMobile.getText().toString().trim();
        institute = updInstitute.getText().toString().trim();
        address = updAddress.getText().toString().trim();

        DataClass_Owner dataClass_owner = new DataClass_Owner(name,floor,room,mobile,institute,address);

        databaseReference.setValue(dataClass_owner).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Students1_UpdateActivity.this,"Udpated",Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Students1_UpdateActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}