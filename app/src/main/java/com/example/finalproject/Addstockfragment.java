package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firestore.v1.WriteResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Addstockfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Addstockfragment extends Fragment {
    private EditText valued,descriptiond,named;
    ImageView image;
    private Button Addbt;
    private  FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Addstockfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addstockfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Addstockfragment newInstance(String param1, String param2) {
        Addstockfragment fragment = new Addstockfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private void attachComponents(){
        Addbt=getActivity().findViewById(R.id.Addbt);
        valued=getActivity().findViewById(R.id.valued);
        named=getActivity().findViewById(R.id.named);
        descriptiond=getActivity().findViewById(R.id.descriptiond);
         db = FirebaseFirestore.getInstance();

    }

    private void addtoFireBseStore() {
        String description,stockname,Value;
        Value=valued.getText().toString();
        description=descriptiond.getText().toString();
        stockname=named.getText().toString();
             if (Value.trim().isEmpty()||description.trim().isEmpty()||stockname.trim().isEmpty())
             {
                 Toast.makeText(getActivity(),"some data are missing",Toast.LENGTH_SHORT).show();
                 return;
             }
             else {
                 Stock s = new Stock(description, stockname, Value);
                 Map<String, Object> docData = new HashMap<>();
                 docData.put("description", description);
                 docData.put("Value", Arrays.asList(Value));
                 DocumentReference future = db.collection("Stockname").document(stockname);
                 future.set(docData);
                 Toast.makeText(getActivity(),"data added",Toast.LENGTH_SHORT).show();
             }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_addstockfragment, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        attachComponents();
        Addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoFireBseStore();

            }
        });
    }

}
