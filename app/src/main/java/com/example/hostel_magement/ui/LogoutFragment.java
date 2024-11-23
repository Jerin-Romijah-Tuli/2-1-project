package com.example.hostel_magement.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {


    private FirebaseAuth authProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth authProfile = FirebaseAuth.getInstance();

        if (authProfile != null) {
            authProfile.signOut();
            Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();

        }
        return inflater.inflate(R.layout.fragment_logout, container, false);


    }
}