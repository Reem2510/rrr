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
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private ArrayList<Stock> StocksModals;
    private List<DocumentSnapshot> mData;
    private Context context;
    private AdapterView.OnItemClickListener mListener;

    public Cadapter(ArrayList<Stock> StocksModals, Context context) {
        this.StocksModals = StocksModals;
        this.context = context;
    }


    public Cadapter(List<DocumentSnapshot> data, AdapterView.OnItemClickListener listener) {
        mData = data;
        mListener = listener;
    }
    public void updateData(List<DocumentSnapshot> documents) {
        mData = documents;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DocumentSnapshot document = mData.get(position);
        String name = document.getString("name");
        String description = document.getString("description");
        holder.mNameTextView.setText(name);
        holder.mDescriptionTextView.setText(description);
    }

    // below is the method to filter our list.
    public void filterList(ArrayList<Stock> filterlist) {
        // adding filtered list to our
        // array list and notifying data set changed
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
        // on below line we are setting data to our item of
        // recycler view and all its views.
       Stock modal = StocksModals.get(position);
        holder.nameTV.setText(modal.getStockName());
        holder.rateTV.setText(modal.getValue());
        holder.symbolTV.setText(modal.getSymbol());
    }

    @Override
    public int getItemCount() {
        // on below line we are returning
        // the size of our array list.
        return StocksModals.size();
    }

    // on below line we are creating our view holder class
    // which will be used to initialize each view of our layout file.
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