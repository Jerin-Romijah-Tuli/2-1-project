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

public class OwnerAdapterRooms extends RecyclerView.Adapter<MyViewHolderR>{

    private Context context;


    private List<DataClass_Owner_rooms> dataClass_ownerList_rooms;

    public OwnerAdapterRooms(Context context, List<DataClass_Owner_rooms> dataClass_ownerList_rooms){
        this.context =context;
        this.dataClass_ownerList_rooms =dataClass_ownerList_rooms;
    }

    @NonNull
    @Override
    public MyViewHolderR onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_owner_rooms,parent,false);
        return new MyViewHolderR(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderR holder, int position) {
        holder.recRoomType.setText(dataClass_ownerList_rooms.get(position).getDataRoomType());
        holder.recFloorno_r.setText(dataClass_ownerList_rooms.get(position).getDatafloor_r());
        holder.recRoomno_r.setText(dataClass_ownerList_rooms.get(position).getDataroom_r());

        holder.recCard_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Rooms_Retrive_Activity.class);
                intent.putExtra("EnterRoomType",dataClass_ownerList_rooms.get(holder.getAdapterPosition()).getDataRoomType());
                intent.putExtra("FloorNo",dataClass_ownerList_rooms.get(holder.getAdapterPosition()).getDatafloor_r());
                intent.putExtra("RoomNo",dataClass_ownerList_rooms.get(holder.getAdapterPosition()).getDataroom_r());

                intent.putExtra("Key",dataClass_ownerList_rooms.get(holder.getAdapterPosition()).getKey());


                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return dataClass_ownerList_rooms != null ? dataClass_ownerList_rooms.size() : 0;
    }

    public void searchDataClass_OwnerList_rooms(ArrayList<DataClass_Owner_rooms>searchList){
        dataClass_ownerList_rooms = searchList;
        notifyDataSetChanged();
    }

}


class MyViewHolderR extends RecyclerView.ViewHolder{

    TextView recRoomType, recFloorno_r, recRoomno_r;
    CardView recCard_rooms;

    public MyViewHolderR(@NonNull View itemView) {
        super(itemView);

        recCard_rooms = itemView.findViewById(R.id.recCard_rooms);
        recRoomType = itemView.findViewById(R.id.recRoomtype);
        recFloorno_r = itemView.findViewById(R.id.recFloorno_r);
        recRoomno_r = itemView.findViewById(R.id.recRoomno_r);
    }
}
