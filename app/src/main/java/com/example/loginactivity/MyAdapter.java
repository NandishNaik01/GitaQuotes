package com.example.loginactivity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<User> userArrayList;



    public MyAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.t1.setText(userArrayList.get(position).getVerseindex());
        holder.t2.setText(userArrayList.get(position).getShloka());
        holder.t3.setText(userArrayList.get(position).getTranslation());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView t1,t2,t3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.verseindex);
            t2=itemView.findViewById(R.id.shloka);
            t3=itemView.findViewById(R.id.translation);

        }
    }
}