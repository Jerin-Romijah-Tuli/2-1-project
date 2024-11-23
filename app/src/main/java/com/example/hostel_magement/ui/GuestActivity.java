package com.example.hostel_magement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hostel_magement.MainActivity;
import com.example.hostel_magement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GuestActivity extends AppCompatActivity {

    private EditText editTextRegisterfullName, editTextRegisteremail, editTextRegisterMobile, editTextRegisterPwd, editRegisterConfirmPwd;
    private ProgressBar progressBar;
    private static final String TAG= "GuestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        getSupportActionBar().setTitle("Register");

        Toast.makeText(GuestActivity.this,"You can Register now", Toast.LENGTH_LONG).show();

        progressBar = findViewById(R.id.ProgressBar);

        editTextRegisterfullName = findViewById(R.id.editText_register_full_name);
        editTextRegisteremail =  findViewById(R.id.editText_register_email);
        editTextRegisterMobile = findViewById(R.id.editText_register_mobile);
        editTextRegisterPwd = findViewById(R.id.editText_register_password);
        editRegisterConfirmPwd = findViewById(R.id.editText_register_confirm_password);

        Button  buttonRegister = findViewById(R.id.RegisterBtn);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textFullName = editTextRegisterfullName.getText().toString();
                String textEmail = editTextRegisteremail.getText().toString();
                String textMobile = editTextRegisterMobile.getText().toString();
                String textPwd = editTextRegisterPwd.getText().toString();
                String textConfirmPwd = editRegisterConfirmPwd.getText().toString();


                if(TextUtils.isEmpty(textFullName)){
                    Toast.makeText(GuestActivity.this,"Please enter your full name", Toast.LENGTH_LONG).show();
                    editTextRegisterfullName.setError("Full Name is required");
                    editTextRegisterfullName.requestFocus();

                }else if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(GuestActivity.this,"Please enter your email", Toast.LENGTH_LONG).show();
                    editTextRegisteremail.setError(" Email is required");
                    editTextRegisteremail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(GuestActivity.this,"Please re-enter your email", Toast.LENGTH_LONG).show();
                    editTextRegisteremail.setError("Valid Email is required");
                    editTextRegisteremail.requestFocus();
                }else if(TextUtils.isEmpty(textMobile)){

                    Toast.makeText(GuestActivity.this,"Please enter your mobile no", Toast.LENGTH_LONG).show();
                    editTextRegisterMobile.setError("Mobile no is required");
                    editTextRegisterMobile.requestFocus();

                }else if(textMobile.length() !=11){
                    Toast.makeText(GuestActivity.this,"Please re-enter your mobile no", Toast.LENGTH_LONG).show();
                    editTextRegisterMobile.setError("Mobile no should be 11 digits");
                    editTextRegisterMobile.requestFocus();
                }else if(TextUtils.isEmpty(textPwd)){
                    Toast.makeText(GuestActivity.this,"Please enter your password", Toast.LENGTH_LONG).show();
                    editTextRegisterPwd.setError("Password is required");
                    editTextRegisterPwd.requestFocus();
                }else if(textPwd.length() <6){
                    Toast.makeText(GuestActivity.this,"Please re-enter your password", Toast.LENGTH_LONG).show();
                    editTextRegisterPwd.setError("Password is too weak");
                    editTextRegisterPwd.requestFocus();
                }else if(TextUtils.isEmpty(textConfirmPwd)){
                    Toast.makeText(GuestActivity.this,"Please confirm your password", Toast.LENGTH_LONG).show();
                    editRegisterConfirmPwd.setError("Password confirmation is required");
                    editRegisterConfirmPwd.requestFocus();

                }else if(!textPwd.equals(textConfirmPwd)){
                    Toast.makeText(GuestActivity.this,"Please enter same password", Toast.LENGTH_LONG).show();
                    editRegisterConfirmPwd.setError("Password confirmation is required");
                    editRegisterConfirmPwd.clearComposingText(); //clean the entered pasword

                }else{
                    progressBar.setVisibility(view.VISIBLE);
                    registeruser(textFullName,textEmail,textMobile,textPwd,textConfirmPwd);
                    Intent intent = new Intent(GuestActivity.this, Student_cardviewActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
    private void registeruser(String textFullName, String textEmail, String textMobile, String textPwd, String textConfirmPwd) {
        FirebaseAuth auth =FirebaseAuth.getInstance();


        auth.createUserWithEmailAndPassword(textEmail,textPwd).addOnCompleteListener(GuestActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(GuestActivity.this,"Registered as guest successfully",Toast.LENGTH_LONG).show();

                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    //uploading data on realtime database
                    ReadwriteGuestDetails writeGuestDetails = new ReadwriteGuestDetails(textFullName,textMobile);
                    DatabaseReference referenceprofile = FirebaseDatabase.getInstance().getReference("Registered Guests");

                    referenceprofile.child(firebaseUser.getUid()).setValue(writeGuestDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                firebaseUser.sendEmailVerification();
                                Toast.makeText(GuestActivity.this,"Registered as guest successfully.Please verify your email.",Toast.LENGTH_LONG).show();
                                /* Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);

                                intent .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();*/
                            }else{
                                Toast.makeText(GuestActivity.this,"Guest registration failed.Please try again.",Toast.LENGTH_LONG).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }else{
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        editTextRegisterPwd.setError("Your password is too weak kindly use a mix of alphabets,numbers and other attributes");
                        editTextRegisterPwd .requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        editTextRegisterPwd.setError("Your email is invalid or already in use.Kindly re-enter. ");
                        editTextRegisterPwd .requestFocus();
                    }catch (FirebaseAuthUserCollisionException e){
                        editTextRegisterPwd.setError("Your email is invalid or already in use.Kindly re-enter. ");
                        editTextRegisterPwd .requestFocus();
                    }catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(GuestActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                    progressBar .setVisibility(View.GONE);
                }
            }
        });
    }


}