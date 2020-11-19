package com.mysoftpanda.android.dunyomamlakatlari.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mysoftpanda.android.dunyomamlakatlari.Objects.Country;
import com.mysoftpanda.android.dunyomamlakatlari.Objects.Flags;
import com.mysoftpanda.android.dunyomamlakatlari.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private List<Country> countryList = new ArrayList();
    private Loader myLoader;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView countryFlag;
        private TextView countryName;
        private TextView countryNameUz;
        private Country myCountry;

        public MyViewHolder(View view) {
            super(view);
            this.countryFlag = (ImageView) view.findViewById(R.id.id_flag_country);
            this.countryName = (TextView) view.findViewById(R.id.id_text_country);
            this.countryNameUz = (TextView) view.findViewById(R.id.id_text_country_uz);
            view.setOnClickListener(this);
        }

        public void bind(Country country) {
            this.myCountry = country;
            this.countryFlag.setImageResource(this.myCountry.getCountryFlag());
            this.countryName.setText(this.myCountry.getCountryName());
            this.countryNameUz.setText(this.myCountry.getCountryNameUz());
        }

        public void onClick(View view) {
            CountryAdapter.this.myLoader.load(this.myCountry.getCountryName());
        }
    }

    public CountryAdapter(Loader loader, Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.countries);
        String[] stringArray2 = context.getResources().getStringArray(R.array.countries_uz);
        for (int i = 0; i < stringArray.length; i++) {
            this.countryList.add(new Country(Flags.getFlags()[i], stringArray[i], stringArray2[i]));
        }
        this.myLoader = loader;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_country, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.bind((Country) this.countryList.get(i));
    }

    public int getItemCount() {
        return this.countryList.size();
    }

    public void setFilter(List<Country> list) {
        this.countryList.clear();
        this.countryList.addAll(list);
        notifyDataSetChanged();
    }
}
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.mysoftpanda.android.dunyomamlakatlari.Objects.Country;
//import com.mysoftpanda.android.dunyomamlakatlari.Objects.Flags;
//import com.mysoftpanda.android.dunyomamlakatlari.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import android.support.v7.widget.RecyclerView.Adapter;
////import android.support.v7.widget.RecyclerView.ViewHolder;
////
////import com.webspektr.dunyo.mamlakatlari.Objects.Country;
////import com.webspektr.dunyo.mamlakatlari.Objects.Flags;
////import com.webspektr.dunyo.mamlakatlari.R;
//
//public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
//    private List<Country> countryList = new ArrayList();
//    private Loader myLoader;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
//        private ImageView countryFlag;
//        private TextView countryName;
//        private TextView countryNameUz;
//        private Country myCountry;
//
//        public MyViewHolder(View view) {
//            super(view);
//            this.countryFlag = (ImageView) view.findViewById(R.id.id_flag_country);
//            this.countryName = (TextView) view.findViewById(R.id.id_text_country);
//            this.countryNameUz = (TextView) view.findViewById(R.id.id_text_country_uz);
//            view.setOnClickListener(this);
//        }
//
//        public void bind(Country country) {
//            this.myCountry = country;
//            this.countryFlag.setImageResource(this.myCountry.getCountryFlag());
//            this.countryName.setText(this.myCountry.getCountryName());
//            this.countryNameUz.setText(this.myCountry.getCountryNameUz());
//        }
//
//        public void onClick(View view) {
//            CountryAdapter.this.myLoader.load(this.myCountry.getCountryName());
//        }
//    }
//
//    public CountryAdapter(Loader loader, Context context) {
//        String[] stringArray = context.getResources().getStringArray(R.array.countries);
//        String[] stringArray2 = context.getResources().getStringArray(R.array.countries_uz);
//        for (int i = 0; i < stringArray.length; i++) {
//            this.countryList.add(new Country(Flags.getFlags()[i], stringArray[i], stringArray2[i]));
//        }
//        this.myLoader = loader;
//    }
//
//    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_country, viewGroup, false));
//    }
//
//    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
//        myViewHolder.bind((Country) this.countryList.get(i));
//    }
//
//    public int getItemCount() {
//        return this.countryList.size();
//    }
//
//    public void setFilter(List<Country> list) {
//        this.countryList.clear();
//        this.countryList.addAll(list);
//        notifyDataSetChanged();
//    }
//}
