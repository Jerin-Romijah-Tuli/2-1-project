package com.example.hostel_magement.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.util.Calendar;

public class Students1Activity extends AppCompatActivity {


    Button button;
    EditText enterRoom, enterName, enterFloor,enterInstitute,enterAddress,enterMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students1);

        enterName = findViewById(R.id.Stuname);
        enterFloor = findViewById(R.id.Floorno);
        enterRoom = findViewById(R.id.Roomno);
        enterInstitute = findViewById(R.id.Institute);
        enterAddress = findViewById(R.id.Addeess);
        enterMobile = findViewById(R.id.Mobile);
        button = findViewById(R.id.Save);


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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = enterName.getText().toString();
                String floorno = enterFloor.getText().toString();
                String roomno =enterRoom.getText().toString();
                String institute = enterInstitute.getText().toString();
                String address = enterAddress.getText().toString();
                String mobileno = enterMobile.getText().toString();

                if(name.isEmpty())
                {
                    enterName.setError("Type name");
                    enterName.requestFocus();
                }

                else if(roomno.isEmpty())
                {
                    enterRoom.setError("Type roomno");
                    enterRoom.requestFocus();
                }
                else if(floorno.isEmpty())
                {
                    enterFloor.setError("Type floorno");
                    enterFloor.requestFocus();
                }
                else if(institute.isEmpty())
                {
                    enterInstitute.setError("Type institute");
                    enterInstitute.requestFocus();
                }
                else if(address.isEmpty())
                {
                    enterAddress.setError("Type address");
                    enterAddress.requestFocus();
                }
                else if(mobileno.isEmpty())
                {
                    enterMobile.setError("Type mobile no");
                    enterMobile.requestFocus();
                }

                else{
                    UploadData();
                }

            }
        });
    }

    public void  UploadData(){
        String name = enterName.getText().toString();
        String floorno = enterFloor.getText().toString();
        String roomno =enterRoom.getText().toString();
        String institute = enterInstitute.getText().toString();
        String address = enterAddress.getText().toString();
        String mobileno = enterMobile.getText().toString();

        DataClass_Owner dataClass_owner= new DataClass_Owner(name,floorno,roomno,institute,address,mobileno);

        String currentData = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());



        FirebaseDatabase.getInstance().getReference("Saved Students").child(currentData)
                .setValue(dataClass_owner).addOnCompleteListener(new OnCompleteListener<Void>(){

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Students1Activity.this,"Saved",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       Toast.makeText(Students1Activity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });


    }
}