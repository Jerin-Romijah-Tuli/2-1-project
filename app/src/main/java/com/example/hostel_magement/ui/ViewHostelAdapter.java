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

public class ViewHostelAdapter extends RecyclerView.Adapter<MyViewHolder_v_h> {
    private Context context;
    private List<ReadwriteOwnerDetails>readwriteOwnerDetailsList;

    public ViewHostelAdapter(Context context,List<ReadwriteOwnerDetails>readwriteOwnerDetailsList){
        this.context =context;
        this.readwriteOwnerDetailsList = readwriteOwnerDetailsList;
    }

    @NonNull
    @Override
    public MyViewHolder_v_h onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_view_hostel,parent,false);
        return new MyViewHolder_v_h(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_v_h holder, int position) {
        holder.recHostelName.setText(readwriteOwnerDetailsList.get(position).getHostelname());
        holder.recLocation.setText(readwriteOwnerDetailsList.get(position).getLocation());
        holder.recMobile.setText(readwriteOwnerDetailsList.get(position).getMobile());
        holder.recFacility.setText(readwriteOwnerDetailsList.get(position).getFacility());
        holder.recCard_view_h.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Students1_Retrive_Activity.class);
                intent.putExtra("HostelName",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getHostelname());
                intent.putExtra("Location",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getLocation());
                intent.putExtra("Mobile",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getMobile());
                intent.putExtra("Facility",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getFacility());
                intent.putExtra("Safety",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getSafety());
                intent.putExtra("Food",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getMeal());
                intent.putExtra("Key",readwriteOwnerDetailsList.get(holder.getAdapterPosition()).getKey());


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return readwriteOwnerDetailsList.size();
    }

    public void searchReadwriteOwnerDetails(ArrayList<ReadwriteOwnerDetails> searchList){
        readwriteOwnerDetailsList = searchList;
        notifyDataSetChanged();
    }

}

class MyViewHolder_v_h extends RecyclerView.ViewHolder{

    TextView recHostelName, recLocation, recMobile,recFacility;
    CardView recCard_view_h;

    public MyViewHolder_v_h(@NonNull View itemView) {
        super(itemView);

        recCard_view_h = itemView.findViewById(R.id.recCard_View_Hostel);
        recHostelName = itemView.findViewById(R.id.recHostelName);
        recLocation = itemView.findViewById(R.id.recLocation);
        recMobile = itemView.findViewById(R.id.recMobile);
        recFacility = itemView.findViewById(R.id.recFacility);
    }
}
