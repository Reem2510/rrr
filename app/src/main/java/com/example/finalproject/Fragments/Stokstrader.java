package com.example.finalproject.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import com.example.finalproject.Adapters.Sadapter;
import com.example.finalproject.Classes.Stock;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stokstrader#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stokstrader extends Fragment {
    private RecyclerView currencyRV;
    private EditText searchEdt;
    private ArrayList<Stock> stocksModalArrayList;
    private Sadapter RVAdapter;


    private Context context;
    private SearchView searchView;

  private FirebaseFirestore db ;
  private   CollectionReference stocksRef;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Stokstrader() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settingsfra.
     */
    // TODO: Rename and change types and number of parameters
    public static Stokstrader newInstance(String param1, String param2) {
        Stokstrader fragment = new Stokstrader();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }



    private void attachComponents() {
        currencyRV =getActivity().findViewById(R.id.idRVSS);
        stocksModalArrayList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        currencyRV.setLayoutManager(manager);
        db = FirebaseFirestore.getInstance();

        searchView=getActivity().findViewById(R.id.simpleSearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

    }

    private void filter(String filter) {
        ArrayList<Stock> filterlist = new ArrayList<>();
        for (Stock item : stocksModalArrayList) {
            if (item.getStockName().toLowerCase().contains(filter.toLowerCase())) {
                filterlist.add(item);
            }
        }
        if (filterlist.isEmpty()) {
            Toast.makeText(getContext(), "No Stocks found..", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: currencyRVAdapter.filterList(filterlist);
            //Todo:set adapter after changing the list;
            RVAdapter.filterList(filterlist);
        }

    }

    private void getData() {
        stocksModalArrayList.clear();
        db.collection("stockkkk").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {

                                Stock s = d.toObject(Stock.class);
                                stocksModalArrayList.add(s);
                            }
                            RVAdapter = new Sadapter(getActivity(),stocksModalArrayList);
                            currencyRV.setAdapter(RVAdapter);
                            RVAdapter.notifyDataSetChanged();


                        } else {
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settingsfra, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        attachComponents();
        getData();

    }
}
