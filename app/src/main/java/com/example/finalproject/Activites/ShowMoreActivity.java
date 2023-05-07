package com.example.finalproject.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Fragments.HomeFragmentt;
import com.example.finalproject.R;

public class ShowMoreActivity extends AppCompatActivity {

    private TextView des;
    Button  urlbt,buttonba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more);


        des = findViewById(R.id.des_tv2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String desc = extras.getString("des");
            des.setText(desc);
        }
        urlbt = findViewById(R.id.urlbt);
        buttonba = findViewById(R.id.buttonba);
        urlbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ig.com/en/learn-to-trade/ig-academy/courses";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        buttonba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentt  homeFragmentt=new HomeFragmentt();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.framela,homeFragmentt);
                fragmentTransaction.commit();
            }
        });

    }
}