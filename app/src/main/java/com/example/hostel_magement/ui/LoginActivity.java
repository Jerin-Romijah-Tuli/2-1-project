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
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.example.hostel_magement.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

   private ActivityLoginBinding binding;
   private EditText editTextLoginemail,editTextLoginPwd;
   private FirebaseAuth authProfile;
   private Button button10;

   private static final String TAG = "LoginActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Login");


        editTextLoginemail = findViewById(R.id.inputEmail);
        editTextLoginPwd = findViewById(R.id.inputPassword);
        authProfile = FirebaseAuth.getInstance();

         button10 = findViewById(R.id.LoginBtn);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = editTextLoginemail.getText().toString();
                String textPwd  = editTextLoginPwd.getText().toString();

                if(textEmail.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter your email", Toast.LENGTH_SHORT).show();
                    editTextLoginemail.setError("Email is required");
                    editTextLoginemail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(LoginActivity.this,"Please re-enter your email", Toast.LENGTH_SHORT).show();
                    editTextLoginemail.setError("Valid email is required");
                    editTextLoginemail.requestFocus();
                }else if(textPwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextLoginPwd.setError("Password is required");
                    editTextLoginPwd.requestFocus();
                }else{
                    loginUser(textEmail,textPwd);
                    startActivity(new Intent(LoginActivity.this, Choose1.class));
                    finish();
                }
            }

            private void loginUser(String email, String pwd) {
                authProfile.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"You are logged in now", Toast.LENGTH_SHORT).show();
                        }else{
                            try{
                                throw task.getException();

                            }catch (FirebaseAuthInvalidUserException e){
                                editTextLoginemail.setError("User doesn't exists or is no longer valid.Please register again.");
                                editTextLoginemail.requestFocus();
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                editTextLoginemail.setError("Invalid credentials.Kindly,check and re-enter.");
                                editTextLoginemail.requestFocus();
                            }catch (Exception e){
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }



                        }

                    }
                });
            }
        });

        binding.singBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ChooseActivity.class));
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(authProfile.getCurrentUser() != null){
            Toast.makeText(LoginActivity.this,"Already logged in ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, Choose1.class));
            finish();

        }
        else{
            Toast.makeText(LoginActivity.this,"You can login now", Toast.LENGTH_SHORT).show();
        }
    }
}