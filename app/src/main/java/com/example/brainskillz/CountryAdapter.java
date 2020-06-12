package com.example.brainskillz;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryItem> {
    public CountryAdapter(@NonNull Context context, ArrayList<CountryItem> countryList) {
        super( context, 0,countryList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    private View InitView(int position,View convertView,ViewGroup parent){
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.spinner_row,parent,false);
        }
        ImageView flagImageView=convertView.findViewById(R.id.countryflag);
        TextView CountryNameTextView=convertView.findViewById(R.id.countryname);
        TextView CountryCodeTextView=convertView.findViewById(R.id.countrycode);

        CountryItem currentItem=getItem(position);

        if (currentItem!=null) {
            flagImageView.setImageResource(currentItem.getCountryFlag());
            CountryNameTextView.setText(currentItem.getCountryName());
            CountryCodeTextView.setText(currentItem.getCountryCode());
        }
        return convertView;
    }
}






