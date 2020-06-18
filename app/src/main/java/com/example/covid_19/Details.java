package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Details extends AppCompatActivity {

    AppCompatTextView countryName,AllCases,todayCasestxt,allDeaths,todayDeath,recoveredtxt;
    AppCompatImageView image,go_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();


    }


    void init(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String imageURL = intent.getStringExtra("flag");
        String allCases = intent.getStringExtra("allcases");
        String todayCases = intent.getStringExtra("todaycases");
        String deaths = intent.getStringExtra("deaths");
        String recovered = intent.getStringExtra("recovered");
        String todaydeath = intent.getStringExtra("todaydeath");

        image = findViewById(R.id.country_image);
        countryName = findViewById(R.id.name);
        AllCases = findViewById(R.id.allcases);
        todayCasestxt = findViewById(R.id.todaycases);
        allDeaths = findViewById(R.id.deathes);
        todayDeath = findViewById(R.id.todaydeath);
        recoveredtxt = findViewById(R.id.recovered);
        go_back = findViewById(R.id.go_back);
        countryName.setText(name);
        AllCases.setText(allCases);
        todayCasestxt.setText(todayCases);
        allDeaths.setText(deaths);
        todayDeath.setText(todaydeath);
        recoveredtxt.setText(recovered);
        Glide.with(this).load(imageURL).into(image);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
