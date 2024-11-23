package com.example.hostel_magement.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostel_magement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Students extends Fragment {

    RecyclerView recyclerView;
    List<DataClass_Owner> dataClass_ownerList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    OwnerAdapter ownerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        FloatingActionButton fab_button = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recyclerview);
        searchView = view.findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.id.progress_layout);

        }*/
        AlertDialog dialog = builder.create();
        dialog.show();


        dataClass_ownerList =new ArrayList<>();

         ownerAdapter = new OwnerAdapter(getActivity(),dataClass_ownerList);
        recyclerView.setAdapter(ownerAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Saved Students");
        dialog.show();

        eventListener =  databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClass_ownerList.clear();
                for(DataSnapshot itemSnaoshot: snapshot.getChildren()){
                    DataClass_Owner dataClass_owner = itemSnaoshot.getValue(DataClass_Owner.class);
                    dataClass_owner.setKey(itemSnaoshot.getKey());
                    dataClass_ownerList.add(dataClass_owner);
                }
                ownerAdapter.notifyDataSetChanged();
                dialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });


        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Students1Activity.class));

            }

        });
        return view;
    }
    public void searchList(String text){
        ArrayList<DataClass_Owner> searchList =new ArrayList<>();
        for(DataClass_Owner dataClass_owner : dataClass_ownerList){
            if(dataClass_owner.getDataname().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass_owner);
            }
        }
        ownerAdapter.searchDataClass_OwnerList(searchList);
    }
}
