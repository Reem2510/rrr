package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cadapter extends RecyclerView.Adapter<Cadapter.CurrencyViewholder> {
    private ArrayList<Stock> StocksModals;
    private List<DocumentSnapshot> mData;
    private Context context;
    private AdapterView.OnItemClickListener mListener;

    public Cadapter( Context context, ArrayList<Stock> StocksModals) {
        this.StocksModals = StocksModals;
        this.context = context;
    }


    /*public Cadapter(List<DocumentSnapshot> data, AdapterView.OnItemClickListener listener) {
        mData = data;
        mListener = listener;
    } */
    public void updateData(List<DocumentSnapshot> documents) {
        mData = documents;
        notifyDataSetChanged();
    }
    /*@Override
    public void onBindViewHolder( int position) {
        DocumentSnapshot document = mData.get(position);
        String name = document.getString("name");
        String description = document.getString("description");

    } */

    public void filterList(ArrayList<Stock> filterlist) {

        StocksModals = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Cadapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.currencyitem, parent, false);
        return new Cadapter.CurrencyViewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Cadapter.CurrencyViewholder holder, int position) {
       Stock modal = StocksModals.get(position);
        holder.nameTV.setText(modal.getStockName());
        holder.rateTV.setText(modal.getValue());
        holder.symbolTV.setText(modal.getSymbol());
    }

    @Override
    public int getItemCount() {

        return StocksModals.size();
    }

    public class CurrencyViewholder extends RecyclerView.ViewHolder {
        private TextView symbolTV, rateTV, nameTV,descriptionTV;

        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVRate);
            nameTV = itemView.findViewById(R.id.idTVName);
        }
    }
}