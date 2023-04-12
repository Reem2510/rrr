package com.example.finalproject.Adapters;

import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Classes.CCClass;
import com.example.finalproject.R;
import com.example.finalproject.Interface.RecyclerViewInterface;


import java.util.ArrayList;

public class BPAdapter extends RecyclerView.Adapter<BPAdapter.MViewHolder> {
    private  final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<CCClass>ccClasses;

    public BPAdapter(Context context, ArrayList<CCClass> ccClasses,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.ccClasses = ccClasses;
        this.recyclerViewInterface=recyclerViewInterface;
    }
 public static class MViewHolder extends RecyclerView.ViewHolder {
     ImageView imageView;
     public MViewHolder(@NonNull View itemView,RecyclerViewInterface  recyclerViewInterface) {
         super(itemView);
         imageView=itemView.findViewById(R.id.ccimageView);
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (recyclerViewInterface!=null)
                 {
                     int pos=getAdapterPosition();
                     if (pos!=RecyclerView.NO_POSITION) {
                         recyclerViewInterface.onItemClick(pos);
                     }
                 }
             }
         });
     }
 }

    @NonNull
    @Override
    public BPAdapter.MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        View view=layoutInflater.inflate(R.layout.cclayout,parent,false);
        return new BPAdapter.MViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull BPAdapter.MViewHolder holder, int position) {
        holder.imageView.setImageResource(ccClasses.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return ccClasses.size();
    }



}
