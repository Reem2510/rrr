package com.example.finalproject.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;

public class ShowMoreActivity extends AppCompatActivity {
    private ImageView mm_image, bb_image;
    private TextView des, Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more);
        mm_image = findViewById(R.id.mainimg);
        des = findViewById(R.id.des_tv2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String  desc=extras.getString("des");
            int img=extras.getInt("imageyy");
             des.setText(desc);

            mm_image.setImageResource(img);
        }



    }
}