package com.example.finalproject;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCurrencyfra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCurrencyfra extends Fragment {
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

    public AddCurrencyfra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Adduser.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCurrencyfra newInstance(String param1, String param2) {
        AddCurrencyfra fragment = new AddCurrencyfra();
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
    private void attachComponents(){
            backbt=getActivity().findViewById(R.id.backbt);
            textView=getActivity().findViewById(R.id.textView);
            Addbt=getActivity().findViewById(R.id.Addbt);
            valued=getActivity().findViewById(R.id.valued);
            named=getActivity().findViewById(R.id.named);
            Symbold=getActivity().findViewById(R.id.Symbold);
            db = FirebaseFirestore.getInstance();


    }
    private void addtoFireBseStore() {
        String Currencyname,Price,Symbol;
        Price=valued.getText().toString();
        Symbol=Symbold.getText().toString();
        Currencyname=named.getText().toString();
        if(!valued.getText().toString().isEmpty()&&!Symbold.getText().toString().isEmpty()&&!named.getText().toString().isEmpty())
        {
            CurrencyModal c=new CurrencyModal(Currencyname,Symbol,Price);

            db.collection("currency")
                    .add(c).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_adduser, container, false);
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