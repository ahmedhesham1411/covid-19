package com.example.covid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.covid_19.Model.Example;
import com.example.covid_19.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<Example> items;
    Context context;
    private final OnClickHander onClickHander;

    public interface OnClickHander {
        void onClick(int position);
    }

    public CartAdapter(List<Example> items, Context context,OnClickHander onClickHander) {
        this.items = items;
        this.context = context;
        this.onClickHander = onClickHander;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        return new CartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(items.get(position).getCountry());
        //holder.price.setText(String.valueOf(items.get(position).getCases()));
        Glide.with(context).load(items.get(position).getCountryInfo().getFlag()).into(holder.flag);
        //holder.txt_details.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView flag;
        TextView name, price;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.countryname);
            //price = itemView.findViewById(R.id.countrytotal);
            flag = itemView.findViewById(R.id.flag);
            cardView = itemView.findViewById(R.id.card);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card:
                    int position = getAdapterPosition();
                    onClickHander.onClick(position);
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}