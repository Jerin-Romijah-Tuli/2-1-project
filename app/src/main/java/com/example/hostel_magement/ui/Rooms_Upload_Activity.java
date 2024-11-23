package com.example.hostel_magement.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Rooms_Upload_Activity extends AppCompatActivity {

    Button button_r;
    EditText enterRoom_r, enterRoomType, enterFloor_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_upload);

        enterRoomType = findViewById(R.id.roomtype);
        enterFloor_r = findViewById(R.id.Floorno_r);
        enterRoom_r = findViewById(R.id.Roomno_r);
        button_r = findViewById(R.id.Save_r);

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

        button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String roomtype = enterRoomType.getText().toString();
                String floorno_r = enterFloor_r.getText().toString();
                String roomno_r =enterRoom_r.getText().toString();


                if(roomtype.isEmpty())
                {
                    enterRoomType.setError("Type roomtype");
                    enterRoomType.requestFocus();
                }

                else if(roomno_r.isEmpty())
                {
                    enterRoom_r.setError("Type roomno_r");
                    enterRoom_r.requestFocus();
                }
                else if(floorno_r.isEmpty())
                {
                    enterFloor_r.setError("Type floorno_r");
                    enterFloor_r.requestFocus();
                }

                else{
                    UploadData_r();
                }

            }
        });

    }

    public void  UploadData_r(){

        String roomtype = enterRoomType.getText().toString();
        String floorno_r = enterFloor_r.getText().toString();
        String roomno_r =enterRoom_r.getText().toString();


        DataClass_Owner_rooms dataClass_owner_rooms= new DataClass_Owner_rooms(roomtype,floorno_r,roomno_r);

        String currentData = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());



        FirebaseDatabase.getInstance().getReference("Saved Rooms").child(currentData)
                .setValue(dataClass_owner_rooms).addOnCompleteListener(new OnCompleteListener<Void>(){

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Rooms_Upload_Activity.this,"Saved",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Rooms_Upload_Activity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });


    }
}