package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sadapter extends RecyclerView.Adapter<Sadapter.CurrencyViewholder> {
    private ArrayList<Stock> StocksModals;
    private Context context;
    private AdapterView.OnItemClickListener mListener;

    public Sadapter(Context context, ArrayList<Stock> StocksModals) {
        this.StocksModals = StocksModals;
        this.context = context;
    }


    public void filterList(ArrayList<Stock> filterlist) {

       this.StocksModals = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Sadapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.stockitem, parent, false);
        return new Sadapter.CurrencyViewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Sadapter.CurrencyViewholder holder, int position) {
       Stock modal = StocksModals.get(position);
        holder.nameTV.setText(modal.getStockName());
        holder.rateTV.setText(modal.getValue());
        holder.symbolTV.setText(modal.getSymbol());
    }

    @Override
    public int getItemCount() {

        if (StocksModals == null) {
            return 0;
        } else {
            return StocksModals.size();
        }
    }
    public void setData(ArrayList<Stock> newData) {
        this.StocksModals.clear();
        this.StocksModals.addAll(newData);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public Object getItem(int position) {
        return StocksModals.get(position);
    }

    public class CurrencyViewholder extends RecyclerView.ViewHolder {
        private TextView symbolTV, rateTV, nameTV;

        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVRate);
            nameTV = itemView.findViewById(R.id.idTVName);
        }
    }
}