package com.example.finalproject;

import static android.app.PendingIntent.getActivity;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                            transaction.replace(R.id.FrameLayout, new Settingsfra());
                            transaction.commit();
                            return b;
                        case R.id.accountm:
                            transaction.replace(R.id.FrameLayout, new Adduser());
                            transaction.commit();
                            return b;
                        case R.id.home:
                            transaction.replace(R.id.FrameLayout, new Mainfra());
                            transaction.commit();
                            return b;
                        case R.id.addda:
                            transaction.replace(R.id.FrameLayout, new Addstockfragment());
                            transaction.commit();
                            return true;
                    }
                return true; // return true;
            }
        });



        }
    }

