package com.example.hostel_magement.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostel_magement.R;

import java.util.ArrayList;
import java.util.List;

public class OwnerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;


    private List<DataClass_Owner>dataClass_ownerList;

    public OwnerAdapter(Context context, List<DataClass_Owner>dataClass_ownerList){
        this.context =context;
        this.dataClass_ownerList =dataClass_ownerList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_owner_student,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recName.setText(dataClass_ownerList.get(position).getDataname());
        holder.recFloorno.setText(dataClass_ownerList.get(position).getDatafloor());
        holder.recRoomno.setText(dataClass_ownerList.get(position).getDataroom());
        holder.recMobileno.setText(dataClass_ownerList.get(position).getDatamobile());

        holder.recCard_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Students1_Retrive_Activity.class);
                intent.putExtra("Name",dataClass_ownerList.get(holder.getAdapterPosition()).getDataname());
                intent.putExtra("FloorNo",dataClass_ownerList.get(holder.getAdapterPosition()).getDatafloor());
                intent.putExtra("RoomNo",dataClass_ownerList.get(holder.getAdapterPosition()).getDataroom());
                intent.putExtra("MobileNo",dataClass_ownerList.get(holder.getAdapterPosition()).getDatamobile());
                intent.putExtra("InstituteName",dataClass_ownerList.get(holder.getAdapterPosition()).getDatainstitute());
                intent.putExtra("PermanentAddress",dataClass_ownerList.get(holder.getAdapterPosition()).getDataaddress());
                intent.putExtra("Key",dataClass_ownerList.get(holder.getAdapterPosition()).getKey());


                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {

        return dataClass_ownerList.size();
    }
    public void searchDataClass_OwnerList(ArrayList<DataClass_Owner>searchList){
        dataClass_ownerList = searchList;
        notifyDataSetChanged();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView recName, recFloorno, recMobileno,recRoomno;
    CardView recCard_students;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recCard_students = itemView.findViewById(R.id.recCard_students);
        recName = itemView.findViewById(R.id.recName);
        recFloorno = itemView.findViewById(R.id.recFloorno);
        recRoomno = itemView.findViewById(R.id.recRoomno);
        recMobileno = itemView.findViewById(R.id.recMobileno);


    }
}
