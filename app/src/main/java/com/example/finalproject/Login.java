package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    private Button loginbt,tsignupbt;
    private FirebaseAuth mAuth;
    private EditText usernamelogin,passwordlogin;
    private TextView txtloginfrgt;
    private void attachComponents() {
        usernamelogin=getActivity().findViewById(R.id.usernamelogin);
        passwordlogin=getActivity().findViewById(R.id.passwordlogin);
        tsignupbt = getActivity().findViewById(R.id.tsignupbt);
        loginbt = getActivity().findViewById(R.id.loginbt);
        mAuth = FirebaseAuth.getInstance();
        txtloginfrgt=getActivity().findViewById(R.id.txtloginfrgt);
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
    public void check(){
        String email,password;
        email=usernamelogin.getText().toString().trim();
        password=passwordlogin.getText().toString().trim();
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(getContext(), "some are empty", Toast.LENGTH_SHORT).show();
            usernamelogin.requestFocus();
            passwordlogin.requestFocus();
            return;
        }
        if(!emailValidator(email)){
            Toast.makeText(getContext(), "wrong email pattern!", Toast.LENGTH_SHORT).show();
            usernamelogin.requestFocus();
            return;
        }
        if (isValidPassword(password)){
            Toast.makeText(getContext(), "wrong password pattern!", Toast.LENGTH_SHORT).show();
            passwordlogin.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Logged in", Toast.LENGTH_SHORT).show();
                   Intent i=new Intent(getActivity(), ActivityMStock.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getContext(), "failed to login ", Toast.LENGTH_SHORT).show();
                }
            }
        });
            }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public Login() {
    }
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_login,container,false);
    }
    @Override
    public void onStart() {
        super.onStart();
        attachComponents();
        txtloginfrgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new ForgetPasswordFragment());
                ft.commit();
            }
        });
        tsignupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new Signup());
                ft.commit();
            }
        });
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }
    }