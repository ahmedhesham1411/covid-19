package com.example.covid_19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.covid_19.Adapter.CartAdapter;
import com.example.covid_19.Model.Example;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements Home_interface, CartAdapter.OnClickHander {

    Home_presenter home_presenter;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    CircleImageView country;
    TextView name;
    List<Example> examples1;
    CardView cardView;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec);
        home_presenter = new Home_presenter(this,this);
        home_presenter.GetData();
        country = findViewById(R.id.img);
        name = findViewById(R.id.countryname);
        cardView = findViewById(R.id.carddd);


    }

    @Override
    public void getdata(List<Example> examples) {


        examples1 = new ArrayList<>();
        examples1 = examples;
        for (int i=0;i<examples.size();i++){
            if (examples.get(i).getCountry().equals("Egypt")){
                Glide.with(this).load(examples.get(i).getCountryInfo().getFlag()).into(country);
                name.setText(String.valueOf(examples.get(i).getCountry()));
                int finalI = i;
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Details.class);
                        intent.putExtra("name",examples1.get(finalI).getCountry());
                        intent.putExtra("flag",examples1.get(finalI).getCountryInfo().getFlag());
                        intent.putExtra("allcases",String.valueOf(examples1.get(finalI).getCases()));
                        intent.putExtra("todaycases",String.valueOf(examples1.get(finalI).getTodayCases()));
                        intent.putExtra("todaydeath",String.valueOf(examples1.get(finalI).getTodayDeaths()));
                        intent.putExtra("deaths",String.valueOf(examples1.get(finalI).getDeaths()));
                        intent.putExtra("recovered",String.valueOf(examples1.get(finalI).getRecovered()));
                        startActivity(intent);
                    }
                });
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext() ,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        cartAdapter = new CartAdapter(examples,this,this);
        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MainActivity.this,Details.class);
        intent.putExtra("name",examples1.get(position).getCountry());
        intent.putExtra("flag",examples1.get(position).getCountryInfo().getFlag());
        intent.putExtra("allcases",String.valueOf(examples1.get(position).getCases()));
        intent.putExtra("todaycases",String.valueOf(examples1.get(position).getTodayCases()));
        intent.putExtra("todaydeath",String.valueOf(examples1.get(position).getTodayDeaths()));
        intent.putExtra("deaths",String.valueOf(examples1.get(position).getDeaths()));
        intent.putExtra("recovered",String.valueOf(examples1.get(position).getRecovered()));
        startActivity(intent);

    }

    private void showLoadingDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

}
