package com.example.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Classes.CurrencyModal;
import com.example.finalproject.R;

import java.util.ArrayList;

public class Cadapter extends RecyclerView.Adapter<Cadapter.CurrencyViewholder> {
    private ArrayList<CurrencyModal> currencyModals;
    private Context context;
    private AdapterView.OnItemClickListener mListener;

    public Cadapter(Context context, ArrayList<CurrencyModal> currencyModals) {
        this.currencyModals = currencyModals;
        this.context = context;
    }


    public void filterList(ArrayList<CurrencyModal> filterlist) {

        this.currencyModals  = filterlist;
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
        CurrencyModal modal = currencyModals.get(position);
        holder.nameTV.setText(modal.getName());
        holder.rateTV.setText(modal.getPrice());
        holder.symbolTV.setText(modal.getSymbol());
    }

    @Override
    public int getItemCount() {

        if (currencyModals== null) {
            return 0;
        } else {
            return currencyModals.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return currencyModals.get(position);
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
