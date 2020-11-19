package com.mysoftpanda.android.dunyomamlakatlari.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mysoftpanda.android.dunyomamlakatlari.MainActivity;
import com.mysoftpanda.android.dunyomamlakatlari.Objects.Maps;
import com.mysoftpanda.android.dunyomamlakatlari.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

public class DetailFragment extends Fragment {
    private String[] capitals;
    private int count;
    private String[] countries;
    private String[] countries_desc;
    private int index;
    private WebView mWebView;
    private ImageView map;
    Map<String, String> map1;
    Map<String, Integer> map2;
    private String myUrl = "file:///android_asset";
    private TextView text1;
    private TextView text2;

    private class WebViewClientClass extends WebViewClient {
        private WebViewClientClass() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.d("check URL", str);
            webView.loadUrl(str);
            return true;
        }
    }

    public DetailFragment newInstance(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(NewHtcHomeBadger.COUNT, i);
        bundle.putInt(MainActivity.INDEX, i2);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    public void onCreate( Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.count = getArguments().getInt(NewHtcHomeBadger.COUNT);
            this.index = getArguments().getInt(MainActivity.INDEX);
        }
    }


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,  Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_detail, viewGroup, false);
    }

    public void onViewCreated(View view,  Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.text1 = (TextView) view.findViewById(R.id.firstText);
        this.text2 = (TextView) view.findViewById(R.id.secondText);
        this.map = (ImageView) view.findViewById(R.id.map_id);
        this.mWebView = (WebView) view.findViewById(R.id.webView);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.countries = getResources().getStringArray(R.array.desctiption_html);
        this.countries_desc = getResources().getStringArray(R.array.desctiption_html);
        this.capitals = getResources().getStringArray(R.array.capitals);
        this.map1 = new HashMap();
        this.map2 = new HashMap();
        for (int i = 0; i < this.countries.length; i++) {
            this.map1.put(this.countries[i], this.capitals[i]);
        }
        for (int i2 = 0; i2 < this.countries.length; i2++) {
            this.map2.put(this.countries[i2], Integer.valueOf(Maps.getMaps()[i2]));
        }
        showData(this.count);
    }

    private void showData(int i) {
        String str;
        int intValue;
        StringBuilder stringBuilder;
        if (this.index == 1) {
            Object obj = this.countries[i];
            str = (String) this.map1.get(obj);
            intValue = ((Integer) this.map2.get(obj)).intValue();
            this.text1.setText(str);
            this.text2.setText((CharSequence) obj);
            this.map.setImageResource(intValue);
            WebView webView = this.mWebView;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.myUrl);
            stringBuilder.append("/description_html/");
            stringBuilder.append(obj);
            stringBuilder.append(".html");
            webView.loadUrl(stringBuilder.toString());
        } else if (this.index == 2) {
            Arrays.sort(this.capitals);
            String str2 = this.capitals[i];
            str = getKey(this.map1, str2);
            intValue = ((Integer) this.map2.get(str)).intValue();
            this.text1.setText(str);
            this.text2.setText(str2);
            this.map.setImageResource(intValue);
            WebView webView2 = this.mWebView;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.myUrl);
            stringBuilder.append("/description_html/");
            stringBuilder.append(str);
            stringBuilder.append(".html");
            webView2.loadUrl(stringBuilder.toString());
        }
        this.mWebView.setWebViewClient(new WebViewClientClass());
    }

    public String getKey(Map<String, String> map, String str) {
        for (Entry entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public int getIndex(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public void onDestroy() {
        super.onDestroy();
        this.map.setImageDrawable(null);
    }
}

