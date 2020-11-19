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

import com.mysoftpanda.android.dunyomamlakatlari.Adapters.CapitalAdapter;
import com.mysoftpanda.android.dunyomamlakatlari.Adapters.Loader;
import com.mysoftpanda.android.dunyomamlakatlari.MyApplication;
import com.mysoftpanda.android.dunyomamlakatlari.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListFragmentCapitals extends Fragment implements SearchView.OnQueryTextListener {
    private CapitalAdapter adapter;
    private List<String> capitalList;

    public boolean onQueryTextSubmit(String str) {
        return false;
    }


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,  Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_list, viewGroup, false);
    }

    public void onViewCreated(View view,  Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHasOptionsMenu(true);
        this.capitalList = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.capitals)));
        Collections.sort(this.capitalList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new CapitalAdapter((Loader) getActivity(), MyApplication.getContext());
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
                ListFragmentCapitals.this.adapter.setFilter(ListFragmentCapitals.this.capitalList);
                return true;
            }
        });
    }

    public boolean onQueryTextChange(String str) {
        this.adapter.setFilter(filterList(this.capitalList, str));
        return true;
    }

    private List<String> filterList(List<String> list, String str) {
        str = str.toLowerCase();
        ArrayList arrayList = new ArrayList();
        for (String str2 : list) {
            if (str2.toLowerCase().contains(str)) {
                arrayList.add(str2);
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
//import com.mysoftpanda.android.dunyomamlakatlari.Adapters.CapitalAdapter;
//import com.mysoftpanda.android.dunyomamlakatlari.Adapters.Loader;
//import com.mysoftpanda.android.dunyomamlakatlari.R;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
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
////import com.webspektr.dunyo.mamlakatlari.Adapters.CapitalAdapter;
////import com.webspektr.dunyo.mamlakatlari.Adapters.Loader;
////import com.webspektr.dunyo.mamlakatlari.R;
//
//public class ListFragmentCapitals extends Fragment implements SearchView.OnQueryTextListener {
//    private CapitalAdapter adapter;
//    private List<String> capitalList;
//
//    public boolean onQueryTextSubmit(String str) {
//        return false;
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
//        this.capitalList = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.capitals)));
//        Collections.sort(this.capitalList);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        this.adapter = new CapitalAdapter((Loader) getActivity(), getContext());
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
//                ListFragmentCapitals.this.adapter.setFilter(ListFragmentCapitals.this.capitalList);
//                return true;
//            }
//        });
//    }
//
//    public boolean onQueryTextChange(String str) {
//        this.adapter.setFilter(filterList(this.capitalList, str));
//        return true;
//    }
//
//    private List<String> filterList(List<String> list, String str) {
//        str = str.toLowerCase();
//        ArrayList arrayList = new ArrayList();
//        for (String str2 : list) {
//            if (str2.toLowerCase().contains(str)) {
//                arrayList.add(str2);
//            }
//        }
//        return arrayList;
//    }
//}
