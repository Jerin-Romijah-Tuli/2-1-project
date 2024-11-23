package com.example.hostel_magement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hostel_magement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_Hostel1_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ReadwriteOwnerDetails> readwriteOwnerDetailsList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    ViewHostelAdapter viewHostelAdapter;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hostel1);

        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(View_Hostel1_Activity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(View_Hostel1_Activity.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();


        readwriteOwnerDetailsList = new ArrayList<>();

        viewHostelAdapter = new ViewHostelAdapter(View_Hostel1_Activity.this, readwriteOwnerDetailsList);
        recyclerView.setAdapter(viewHostelAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("Registered hostels");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                readwriteOwnerDetailsList.clear();
                for (DataSnapshot itemSnaoshot : snapshot.getChildren()) {

                    ReadwriteOwnerDetails readwriteOwnerDetails = itemSnaoshot.getValue(ReadwriteOwnerDetails.class);
                    readwriteOwnerDetails.setkey(itemSnaoshot.getKey());
                    readwriteOwnerDetailsList.add(readwriteOwnerDetails);


                }
                viewHostelAdapter.notifyDataSetChanged();
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


    }

    public void searchList(String text) {
        ArrayList<ReadwriteOwnerDetails> searchList = new ArrayList<>();
        for (ReadwriteOwnerDetails readwriteOwnerDetails : readwriteOwnerDetailsList) {
            if (readwriteOwnerDetails.getHostelname().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(readwriteOwnerDetails);
            }
            viewHostelAdapter.searchReadwriteOwnerDetails(searchList);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_logout){
            FirebaseAuth authProfile = FirebaseAuth.getInstance();

            if (authProfile != null) {
                authProfile.signOut();
            }
            Toast.makeText(View_Hostel1_Activity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(View_Hostel1_Activity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(View_Hostel1_Activity.this, "Something went worng", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
}