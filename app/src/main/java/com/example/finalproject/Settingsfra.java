package com.example.finalproject;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.FirestoreClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.grpc.okhttp.internal.proxy.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settingsfra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settingsfra extends Fragment {
    private RecyclerView currencyRV;
    private EditText searchEdt;
    private ArrayList<Stock> stocksModalArrayList;
    private Cadapter currencyRVAdapter;
    private ProgressBar loadingPB;
    private Context context;
  private FirebaseFirestore db ;
  private   CollectionReference stocksRef;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Settingsfra() {
        // Required empty public constructor
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
    public static Settingsfra newInstance(String param1, String param2) {
        Settingsfra fragment = new Settingsfra();
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
        searchEdt =getActivity().findViewById(R.id.idEdtCurrency);
        loadingPB = getActivity().findViewById(R.id.idPBLoading);
        currencyRV =getActivity().findViewById(R.id.idRVcurrency);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        currencyRV.setLayoutManager(layoutManager);
        currencyRV.setAdapter(currencyRVAdapter);
        stocksModalArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        CollectionReference stocksRef = db.collection("stocks");
        currencyRVAdapter = new Cadapter(stocksModalArrayList,this)
        getData();
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }
    private void filter(String toString) {
        ArrayList<Stock> filterlist = new ArrayList<>();
        for (Stock item : stocksModalArrayList) {
            // on below line we are getting the item which are
            // filtered and adding it to filtered list.
            if (item.getStockName().toLowerCase().contains(filter.toLowerCase())) {
                filterlist.add(item);
            }
        }
        if (filterlist.isEmpty()) {
            Toast.makeText(getContext(), "No Stocks found..", Toast.LENGTH_SHORT).show();
        } else {
            // on below line we are calling a filter
            // list method to filter our list.
            currencyRVAdapter.filterList(filterlist);
        }
    }

    private void getData() {

        db.collection("Stocks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                               // mAdapter.updateData(documents);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
       // currencyRV.setAdapter(mAdapter);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settingsfra, container, false);
    }



   }
