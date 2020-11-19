package com.mysoftpanda.android.dunyomamlakatlari.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mysoftpanda.android.dunyomamlakatlari.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CapitalAdapter extends RecyclerView.Adapter<CapitalAdapter.MyViewHolder> {
    private List<String> capitalList;
    private Loader myLoader;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView capitalName;
        private String myCapital;

        public MyViewHolder(View view) {
            super(view);
            this.capitalName = (TextView) view.findViewById(R.id.id_text_capital);
            view.setOnClickListener(this);
        }

        public void bind(String str) {
            this.myCapital = str;
            this.capitalName.setText(this.myCapital);
        }

        public void onClick(View view) {
            CapitalAdapter.this.myLoader.load(this.myCapital);
        }
    }

    public CapitalAdapter(Loader loader, Context context) {
        this.capitalList = new ArrayList(Arrays.asList(context.getResources().getStringArray(R.array.capitals)));
        Collections.sort(this.capitalList);
        this.myLoader = loader;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_capital, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.bind((String) this.capitalList.get(i));
    }

    public int getItemCount() {
        return this.capitalList.size();
    }

    public void setFilter(List<String> list) {
        this.capitalList.clear();
        this.capitalList.addAll(list);
        notifyDataSetChanged();
    }
}

//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView.Adapter;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;
//
//import com.mysoftpanda.android.dunyomamlakatlari.R;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
////import android.support.v7.widget.RecyclerView.Adapte
////import com.webspektr.dunyo.mamlakatlari.R;
//
//public class CapitalAdapter extends Adapter<CapitalAdapter.MyViewHolder> {
//    private List<String> capitalList;
//    private Loader myLoader;
//
//    public class MyViewHolder extends ViewHolder implements OnClickListener {
//        private TextView capitalName;
//        private String myCapital;
//
//        public MyViewHolder(View view) {
//            super(view);
//            this.capitalName = (TextView) view.findViewById(R.id.id_text_capital);
//            view.setOnClickListener(this);
//        }
//
//        public void bind(String str) {
//            this.myCapital = str;
//            this.capitalName.setText(this.myCapital);
//        }
//
//        public void onClick(View view) {
//            CapitalAdapter.this.myLoader.load(this.myCapital);
//        }
//    }
//
//    public CapitalAdapter(Loader loader, Context context) {
//        this.capitalList = new ArrayList(Arrays.asList(context.getResources().getStringArray(R.array.capitals)));
//        Collections.sort(this.capitalList);
//        this.myLoader = loader;
//    }
//
//    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_capital, viewGroup, false));
//    }
//
//    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
//        myViewHolder.bind((String) this.capitalList.get(i));
//    }
//
//    public int getItemCount() {
//        return this.capitalList.size();
//    }
//
//    public void setFilter(List<String> list) {
//        this.capitalList.clear();
//        this.capitalList.addAll(list);
//        notifyDataSetChanged();
//    }
//}
