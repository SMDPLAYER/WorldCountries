package com.mysoftpanda.android.dunyomamlakatlari.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mysoftpanda.android.dunyomamlakatlari.Adapters.CountryAdapter;
import com.mysoftpanda.android.dunyomamlakatlari.Adapters.Loader;
import com.mysoftpanda.android.dunyomamlakatlari.Objects.Country;
import com.mysoftpanda.android.dunyomamlakatlari.Objects.Flags;
import com.mysoftpanda.android.dunyomamlakatlari.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentCountries extends Fragment implements SearchView.OnQueryTextListener {
    private CountryAdapter adapter;
    private List<Country> countryList;

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public void onCreate( Bundle bundle) {
        super.onCreate(bundle);
    }


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,  Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_list, viewGroup, false);
    }

    public void onViewCreated(View view,  Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHasOptionsMenu(true);
        String[] stringArray = getResources().getStringArray(R.array.countries);
        String[] stringArray2 = getResources().getStringArray(R.array.countries_uz);
        this.countryList = new ArrayList();
        for (int i = 0; i < stringArray.length; i++) {
            this.countryList.add(new Country(Flags.getFlags()[i], stringArray[i], stringArray2[i]));
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new CountryAdapter((Loader) getActivity(), getContext());
        recyclerView.setAdapter(this.adapter);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.options_menu, menu);
        MenuItem findItem = menu.findItem(R.id.search);
        ((SearchView) MenuItemCompat.getActionView(findItem)).setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(findItem, new MenuItemCompat.OnActionExpandListener() {
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                ListFragmentCountries.this.adapter.setFilter(ListFragmentCountries.this.countryList);
                return true;
            }
        });
    }

    public boolean onQueryTextChange(String str) {
        this.adapter.setFilter(filterList(this.countryList, str));
        return true;
    }

    private List<Country> filterList(List<Country> list, String str) {
        str = str.toLowerCase();
        ArrayList arrayList = new ArrayList();
        for (Country country : list) {
            if (country.getCountryName().toLowerCase().contains(str)) {
                arrayList.add(country);
            }
        }
        return arrayList;
    }
}

//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.appcompat.widget.SearchView;
//import androidx.core.view.MenuItemCompat;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.mysoftpanda.android.dunyomamlakatlari.Adapters.CountryAdapter;
//import com.mysoftpanda.android.dunyomamlakatlari.Adapters.Loader;
//import com.mysoftpanda.android.dunyomamlakatlari.Objects.Country;
//import com.mysoftpanda.android.dunyomamlakatlari.Objects.Flags;
//import com.mysoftpanda.android.dunyomamlakatlari.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import android.support.annotation.Nullable;
////import android.support.v4.app.Fragment;
////import android.support.v4.view.MenuItemCompat;
////import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
////import android.support.v7.widget.LinearLayoutManager;
////import android.support.v7.widget.RecyclerView;
////import android.support.v7.widget.SearchView;
////import android.support.v7.widget.SearchView.OnQueryTextListener;
////import com.webspektr.dunyo.mamlakatlari.Adapters.CountryAdapter;
////import com.webspektr.dunyo.mamlakatlari.Adapters.Loader;
////import com.webspektr.dunyo.mamlakatlari.Objects.Country;
////import com.webspektr.dunyo.mamlakatlari.Objects.Flags;
////import com.webspektr.dunyo.mamlakatlari.R;
////import java.util.List;
//
//public class ListFragmentCountries extends Fragment implements SearchView.OnQueryTextListener {
//    private CountryAdapter adapter;
//    private List<Country> countryList;
//
//    public boolean onQueryTextSubmit(String str) {
//        return false;
//    }
//
//    public void onCreate( Bundle bundle) {
//        super.onCreate(bundle);
//    }
//
//
//    public View onCreateView(LayoutInflater layoutInflater,  ViewGroup viewGroup,  Bundle bundle) {
//        return layoutInflater.inflate(R.layout.fragment_list, viewGroup, false);
//    }
//
//    public void onViewCreated(View view,  Bundle bundle) {
//        super.onViewCreated(view, bundle);
//        setHasOptionsMenu(true);
//        String[] stringArray = getResources().getStringArray(R.array.countries);
//        String[] stringArray2 = getResources().getStringArray(R.array.countries_uz);
//        this.countryList = new ArrayList();
//        for (int i = 0; i < stringArray.length; i++) {
//            this.countryList.add(new Country(Flags.getFlags()[i], stringArray[i], stringArray2[i]));
//        }
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        this.adapter = new CountryAdapter((Loader) getActivity(), getContext());
//        recyclerView.setAdapter(this.adapter);
//    }
//
//    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        menuInflater.inflate(R.menu.options_menu, menu);
//        MenuItem findItem = menu.findItem(R.id.search);
//        ((SearchView) MenuItemCompat.getActionView(findItem)).setOnQueryTextListener(this);
//        MenuItemCompat.setOnActionExpandListener(findItem, new MenuItemCompat.OnActionExpandListener() {
//            public boolean onMenuItemActionExpand(MenuItem menuItem) {
//                return true;
//            }
//
//            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//                ListFragmentCountries.this.adapter.setFilter(ListFragmentCountries.this.countryList);
//                return true;
//            }
//        });
//    }
//
//    public boolean onQueryTextChange(String str) {
//        this.adapter.setFilter(filterList(this.countryList, str));
//        return true;
//    }
//
//    private List<Country> filterList(List<Country> list, String str) {
//        str = str.toLowerCase();
//        ArrayList arrayList = new ArrayList();
//        for (Country country : list) {
//            if (country.getCountryName().toLowerCase().contains(str)) {
//                arrayList.add(country);
//            }
//        }
//        return arrayList;
//    }
//}
