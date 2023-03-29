package com.example.finalproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends Fragment {
 private  EditText cpassworded,usernamesign,passwordsign,Address,Phone,Fullname;
 private Button tsignupbtp,loginbtp;
 private FirebaseAuth objectfirebaseauth;
    private FirebaseFirestore db;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public Signup() {
    }
    private String mParam1;
    private String mParam2;

    public void createUser(){
        String fullname,userPhone,userAddress;
        fullname=Fullname.getText().toString();
        userPhone=Phone.getText().toString();
        userAddress=Address.getText().toString();
        try{
            if(!usernamesign.getText().toString().isEmpty()&&!passwordsign.getText().toString().isEmpty()){
                if(!Fullname.getText().toString().isEmpty()&&!Phone.getText().toString().isEmpty()&&!Address.getText().toString().isEmpty()) {
                    UserClass u = new UserClass(fullname, userAddress, userPhone);

                    db.collection("users")
                            .add(u).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
                    objectfirebaseauth.createUserWithEmailAndPassword(usernamesign.getText().toString(), passwordsign.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    Toast.makeText(getContext(), "Account created.", Toast.LENGTH_SHORT).show();
                                    if (objectfirebaseauth.getCurrentUser() != null) {
                                        objectfirebaseauth.signOut();
                                        Intent i = new Intent(getActivity(),ActivityMStock.class);
                                        startActivity(i);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    Toast.makeText(getContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getContext(), "Missing fields identified.", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void attachComponents(){
        passwordsign=getActivity().findViewById(R.id.passwordsign);
        Fullname=getActivity().findViewById(R.id.Fullname);
        Address=getActivity().findViewById(R.id.Address);
        Phone=getActivity().findViewById(R.id.Phone);
    usernamesign=getActivity().findViewById(R.id.usernamesign);
        loginbtp=getActivity().findViewById(R.id.loginbtp);
        tsignupbtp=getView().findViewById(R.id.tsignupbtp);
            objectfirebaseauth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        tsignupbtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_signup,container,false);
    }
    @Override
    public void onStart() {
        super.onStart();
        attachComponents();
        loginbtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new Login());
                ft.commit();
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

    }

    }

    }
