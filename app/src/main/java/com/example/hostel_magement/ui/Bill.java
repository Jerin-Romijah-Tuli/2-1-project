package com.example.hostel_magement.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostel_magement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Bill extends Fragment {

    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }
}