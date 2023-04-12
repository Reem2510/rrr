package com.example.finalproject.Activites;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalproject.Fragments.Aboutusfra;
import com.example.finalproject.Data.AddFragment;
import com.example.finalproject.Fragments.HomeFragmentt;
import com.example.finalproject.R;
import com.example.finalproject.Fragments.Stokstrader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityMStock extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mstock);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    boolean b = true;
                    switch (item.getItemId()) {
                        case R.id.settings:
                            transaction.replace(R.id.FrameLayout, new Stokstrader());
                            transaction.commit();
                            return b;
                        case R.id.accountm:
                            transaction.replace(R.id.FrameLayout, new Aboutusfra());
                            transaction.commit();
                            return b;
                        case R.id.home:
                            transaction.replace(R.id.FrameLayout, new HomeFragmentt());
                            transaction.commit();
                            return b;
                        case R.id.addda:
                            transaction.replace(R.id.FrameLayout, new AddFragment());
                            transaction.commit();
                            return true;
                    }
                return true;
            }
        });



        }
    }

