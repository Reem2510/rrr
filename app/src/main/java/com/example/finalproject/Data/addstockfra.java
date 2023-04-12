package com.example.finalproject.Data;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Classes.Stock;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addstockfra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addstockfra extends Fragment {
    private EditText valued,named,Symbold;
 private TextView textView;

    private Button Addbt,backbt;
    private FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addstockfra() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adddd.
     */
    // TODO: Rename and change types and number of parameters
    public static addstockfra newInstance(String param1, String param2) {
        addstockfra fragment = new addstockfra();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private void addtoFireBseStore() {
        String Stockname,Value,Symbol;
        Value=valued.getText().toString();
        Symbol=Symbold.getText().toString();
        Stockname=named.getText().toString();
        if(!valued.getText().toString().isEmpty()&&!Symbold.getText().toString().isEmpty()&&!named.getText().toString().isEmpty())
        {
            Stock s=new Stock(Stockname,Symbol,Value);

            db.collection("stockkkk")
                    .add(s).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
            Toast.makeText(getActivity(),"data ",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getActivity(),"some data are missing",Toast.LENGTH_SHORT).show();
            return;

        }
    }
    private void attachComponents(){
        backbt=getActivity().findViewById(R.id.backbt);
        textView=getActivity().findViewById(R.id.textView);
        Addbt=getActivity().findViewById(R.id.Addbt);
        valued=getActivity().findViewById(R.id.valued);
        named=getActivity().findViewById(R.id.named);
        Symbold=getActivity().findViewById(R.id.Symbold);
        db = FirebaseFirestore.getInstance();

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adddd, container, false);
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
       backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayout, new AddFragment());
                ft.commit();
            }
        });
    }
}