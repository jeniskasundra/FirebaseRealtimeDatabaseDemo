package com.jenisk.firebase_realtime_db.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jenisk.firebase_realtime_db.MainActivity;
import com.jenisk.firebase_realtime_db.R;
import com.jenisk.firebase_realtime_db.ShowOnMapActivity;
import com.jenisk.firebase_realtime_db.model.UserDetail;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>  {
    private ArrayList<UserDetail> userList;
    private  Context context;

    public UserListAdapter(Context context, ArrayList<UserDetail> userList) {

        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        final UserDetail employDetail = userList.get(position);
        holder.tvName.setText(employDetail.getName());
        holder.tvEmail.setText(employDetail.getEmail());
        holder.tvNumber.setText(employDetail.getNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isAllShowOnMap=false;
                Intent intent=new Intent(context, ShowOnMapActivity.class);
                intent.putExtra("username",employDetail.getName());
                intent.putExtra("latitude",employDetail.getLatitude());
                intent.putExtra("longitude",employDetail.getLongitude());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvName, tvNumber,tvEmail;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvNameRaw);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumberRaw);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmailRaw);
        }
    }
}
