package com.example.hostel_magement.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostel_magement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Rooms extends Fragment {
    RecyclerView recyclerView;
    List<DataClass_Owner_rooms> dataClass_ownerList_rooms;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    OwnerAdapterRooms ownerAdapterRooms;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        FloatingActionButton fab_button_r = view.findViewById(R.id.fab_r);
        recyclerView = view.findViewById(R.id.recyclerview_r);
        searchView = view.findViewById(R.id.search_r);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();


        dataClass_ownerList_rooms = new ArrayList<>();

        ownerAdapterRooms = new OwnerAdapterRooms(getActivity(), dataClass_ownerList_rooms);
        recyclerView.setAdapter(ownerAdapterRooms);

        databaseReference = FirebaseDatabase.getInstance().getReference("Saved Rooms");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClass_ownerList_rooms.clear();
                for (DataSnapshot itemSnaoshot : snapshot.getChildren()) {
                    DataClass_Owner_rooms dataClass_owner_rooms = itemSnaoshot.getValue(DataClass_Owner_rooms.class);
                    dataClass_owner_rooms.setKey(itemSnaoshot.getKey());
                    dataClass_ownerList_rooms.add(dataClass_owner_rooms);
                }
                ownerAdapterRooms.notifyDataSetChanged();
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

        fab_button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Rooms_Upload_Activity.class));

            }

        });
        return view;
    }
    public void searchList(String text){
        ArrayList<DataClass_Owner_rooms> searchList =new ArrayList<>();
        for(DataClass_Owner_rooms dataClass_owner_rooms : dataClass_ownerList_rooms){
            if(dataClass_owner_rooms.getDataRoomType().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass_owner_rooms);
            }
        }
        ownerAdapterRooms.searchDataClass_OwnerList_rooms(searchList);
    }
}


