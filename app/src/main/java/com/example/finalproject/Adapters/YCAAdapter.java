package com.example.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Classes.ItemData;
import com.example.finalproject.R;

import java.util.ArrayList;

public  class YCAAdapter extends RecyclerView.Adapter<YCAAdapter.MyViewHolder> {
  //  private  final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ItemData> itemData;

    public YCAAdapter(Context context, ArrayList<ItemData> itemData) {
        this.context = context;
        this.itemData= itemData;

    }
    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView TvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewcc);
            TvName=itemView.findViewById(R.id.nametx);

        }
    }


    @NonNull
    @Override
    public YCAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.itemdata,parent,false);
        return new YCAAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YCAAdapter.MyViewHolder holder, int position) {
        holder.TvName.setText(itemData.get(position).getName());
        holder.imageView.setImageResource(itemData.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }


}
