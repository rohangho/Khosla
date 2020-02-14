package com.example.sampleweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sampleweather.R;
import com.example.sampleweather.pojos.BottomTemp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.MyViewHolder> {
    List<BottomTemp> allList;
    Context context;

    public BottomAdapter(List<BottomTemp> alltemp, Context applicationContext) {
        this.allList = alltemp;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public BottomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inside_recycler, parent, false);
        return new MyViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load("http://openweathermap.org/img/wn/" + allList.get(position).getIcon()).into(holder.icon);
        holder.temperature.setText(allList.get(position).getTemp() + " \u2103");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            String ds2 = sdf2.format(sdf1.parse(allList.get(position).getDateToShow()));
            holder.date.setText(ds2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView date;
        AppCompatTextView temperature;
        AppCompatImageView icon;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            date = itemView.findViewById(R.id.datetoShow);
            temperature = itemView.findViewById(R.id.temperature);
            icon = itemView.findViewById(R.id.image_weather);
        }
    }
}
