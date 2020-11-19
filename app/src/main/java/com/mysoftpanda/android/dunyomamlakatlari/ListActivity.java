package com.mysoftpanda.android.dunyomamlakatlari;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.mysoftpanda.android.dunyomamlakatlari.Adapters.Loader;
import com.mysoftpanda.android.dunyomamlakatlari.Fragments.ListFragmentCapitals;
import com.mysoftpanda.android.dunyomamlakatlari.Fragments.ListFragmentCountries;

public class ListActivity extends AppCompatActivity implements Loader {
    public static final String CC_TAG = "country_capital_tag";
    public static final String INDEX_TAG = "index_tag";
    private int index;

    /* Access modifiers changed, original: protected */
    @SuppressLint("ResourceType")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_list);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.index = getIntent().getIntExtra(MainActivity.INDEX, 0);
        if (this.index == 1) {
            beginTransaction.add(16908290, new ListFragmentCountries()).commit();
            setTitle(getResources().getString(R.string.app_name));
        } else if (this.index == 2) {
            beginTransaction.add(16908290, new ListFragmentCapitals()).commit();
            setTitle("Poytaxtlar");
        }
    }

    public void load(String str) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(INDEX_TAG, this.index);
        intent.putExtra(CC_TAG, str);
        startActivity(intent);
    }
}
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.mysoftpanda.android.dunyomamlakatlari.Adapters.Loader;
//import com.mysoftpanda.android.dunyomamlakatlari.Fragments.ListFragmentCapitals;
//import com.mysoftpanda.android.dunyomamlakatlari.Fragments.ListFragmentCountries;
//
//
//public class ListActivity extends AppCompatActivity implements Loader {
//    public static final String CC_TAG = "country_capital_tag";
//    public static final String INDEX_TAG = "index_tag";
//    private int index;
//
//    /* Access modifiers changed, original: protected */
//    @SuppressLint("ResourceType")
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.activity_list);
//        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
//        this.index = getIntent().getIntExtra(MainActivity.INDEX, 0);
//        if (this.index == 1) {
//            beginTransaction.add(16908290, new ListFragmentCountries()).commit();
//            setTitle(getResources().getString(R.string.app_name));
//        } else if (this.index == 2) {
//            beginTransaction.add(16908290, new ListFragmentCapitals()).commit();
//            setTitle("Dunyo Mamlakatalri");
//        }
//    }
//
//    public void load(String str) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(INDEX_TAG, this.index);
//        intent.putExtra(CC_TAG, str);
//        startActivity(intent);
//    }
//}
