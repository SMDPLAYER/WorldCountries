package com.mysoftpanda.android.dunyomamlakatlari;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mysoftpanda.android.dunyomamlakatlari.Fragments.DetailFragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DetailActivity extends AppCompatActivity {
    int MAX_PAGE = 195;
    private ImageView btnBackward;
    private ImageView btnForward;
    String[] capitals;
    int count;
    String[] countries;
    Fragment cur_fragment = new Fragment();
    int index;
    private Handler mHandler = new Handler();
    private WebView mWebView;
    private ImageView map;
    Map<String, String> map1;
    Map<String, Integer> map2;
    Map<String, String> map3;
    private MediaPlayer mediaPlayer;
    private String myUrl = "file:///android_asset";
    private ImageView next;
    private ImageButton pause;
    private ImageView play;
    private boolean playState = false;
    private ImageView prev;
    private int seekBackwardTime = 5000;
    private int seekForwardTime = 5000;
    private TextView songCurrentDurationLabel;
    private String songName;
    SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songTotalDurationLabel;
    private String[] songs;
    String str;
    private TextView text1;
    private TextView text2;
    private Handler threadHandler = new Handler();
    private Utilities utils;
    ViewPager vp;

    public class UpdateSeekBarThread implements Runnable {
        public void run() {
            int currentPosition = DetailActivity.this.mediaPlayer.getCurrentPosition();
            DetailActivity.this.mediaPlayer.getDuration();
            DetailActivity.this.songCurrentDurationLabel.setText(DetailActivity.this.millisecondsToString(currentPosition));
            DetailActivity.this.songProgressBar.setProgress(currentPosition);
            DetailActivity.this.threadHandler.postDelayed(this, 50);
        }
    }

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            DetailActivity.this.cur_fragment = new DetailFragment().newInstance(i, DetailActivity.this.index);
            return DetailActivity.this.cur_fragment;
        }

        public int getCount() {
            return DetailActivity.this.MAX_PAGE;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);
        this.vp = (ViewPager) findViewById(R.id.vp);
        this.vp.setAdapter(new adapter(getSupportFragmentManager()));

        this.vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                DetailActivity.this.count = i;
                if (DetailActivity.this.mediaPlayer != null) {
                    DetailActivity.this.mediaPlayer.reset();
                }
                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
                DetailActivity.this.playState = false;
                DetailActivity.this.songCurrentDurationLabel.setText("");
                DetailActivity.this.songTotalDurationLabel.setText("");
                DetailActivity.this.setSong(i);

            }
        });
        this.play = (ImageView) findViewById(R.id.btnPlay);
        this.next = (ImageView) findViewById(R.id.btnNext);
        this.prev = (ImageView) findViewById(R.id.btnPrevious);
        this.songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        this.songProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                DetailActivity.this.mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        this.btnForward = (ImageView) findViewById(R.id.btnForward);
        this.btnBackward = (ImageView) findViewById(R.id.btnBackward);
        this.songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        this.songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
        this.play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DetailActivity.this.playState) {
                    DetailActivity.this.play.setImageResource(R.drawable.btn_play);
                    DetailActivity.this.doPause(view);
                    DetailActivity.this.playState = false;
                    return;
                }
                DetailActivity.this.play.setImageResource(R.drawable.btn_pause);
                DetailActivity.this.doStart(view);
                DetailActivity.this.playState = true;
            }
        });
        this.btnBackward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DetailActivity.this.doRewind(view);
            }
        });
        this.btnForward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DetailActivity.this.doFastForward(view);
            }
        });
        this.next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DetailActivity.this.mediaPlayer != null) {
                    DetailActivity.this.mediaPlayer.reset();
                }
                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
                DetailActivity.this.playState = false;
                DetailActivity.this.songCurrentDurationLabel.setText("");
                DetailActivity.this.songTotalDurationLabel.setText("");
                DetailActivity detailActivity = DetailActivity.this;
                detailActivity.count++;
                if (DetailActivity.this.count == DetailActivity.this.MAX_PAGE) {
                    DetailActivity.this.count = 0;
                }
                DetailActivity.this.vp.setCurrentItem(DetailActivity.this.count);
                DetailActivity.this.vp.setCurrentItem(DetailActivity.this.count);
            }
        });
        this.prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
                DetailActivity.this.playState = false;
                DetailActivity.this.songCurrentDurationLabel.setText("");
                DetailActivity.this.songTotalDurationLabel.setText("");
                if (DetailActivity.this.mediaPlayer != null) {
                    DetailActivity.this.mediaPlayer.reset();
                }
                DetailActivity detailActivity = DetailActivity.this;
                detailActivity.count--;
                if (DetailActivity.this.count < 0) {
                    DetailActivity.this.count = DetailActivity.this.MAX_PAGE;
                }
                DetailActivity.this.vp.setCurrentItem(DetailActivity.this.count);
            }
        });
        this.countries = getResources().getStringArray(R.array.countries);
        this.capitals = getResources().getStringArray(R.array.capitals);
        this.songs = getResources().getStringArray(R.array.desctiption_sound);
        String[] strArr = this.capitals;
        Arrays.sort(strArr);
        setTitle(getResources().getString(R.string.app_name));
        int i = 0;
        this.index = getIntent().getIntExtra(ListActivity.INDEX_TAG, 0);
        this.str = getIntent().getStringExtra(ListActivity.CC_TAG);
        this.map1 = new HashMap();
        this.map3 = new HashMap();
        for (int i2 = 0; i2 < this.countries.length; i2++) {
            this.map1.put(this.countries[i2], this.capitals[i2]);
        }
        while (i < this.countries.length) {
            this.map3.put(this.countries[i], this.songs[i]);
            i++;
        }
        if (this.index == 1) {
            this.count = getIndex(this.countries, this.str);
        } else {
            this.count = getIndex(strArr, this.str);
        }
        this.vp.setCurrentItem(this.count);
        if (this.mediaPlayer == null) {
            setSong(this.count);
        }
    }

    private String millisecondsToString(int i) {
        long j = (long) (i / 60000);
        long j2 = (long) ((i % 60000) / 1000);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j);
        stringBuilder.append(":");
        stringBuilder.append(j2);
        return stringBuilder.toString();
    }

    public void doStart(View view) {
        int duration = this.mediaPlayer.getDuration();
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if (currentPosition == 0) {
            this.songProgressBar.setMax(duration);
            this.songTotalDurationLabel.setText(millisecondsToString(duration));
        } else if (currentPosition == duration) {
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.start();
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
                DetailActivity.this.playState = false;
            }
        });
        this.threadHandler.postDelayed(new UpdateSeekBarThread(), 50);
    }

    public int getIndex(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public void doPause(View view) {
        this.mediaPlayer.pause();
    }

    public void doRewind(View view) {
        int currentPosition = this.mediaPlayer.getCurrentPosition() - 5000;
        if (currentPosition > 0) {
            this.mediaPlayer.seekTo(currentPosition);
        }
    }

    public void doFastForward(View view) {
        int currentPosition = this.mediaPlayer.getCurrentPosition() + 5000;
        if (currentPosition < this.mediaPlayer.getDuration()) {
            this.mediaPlayer.seekTo(currentPosition);
        }
    }

    public int getRawResIdByName(String str) {
        return getResources().getIdentifier(str, "raw", getPackageName());
    }

    private void setSong(int i) {
        if (this.index == 1) {
            this.songName = (String) this.map3.get(this.countries[i]);
        } else {
            String[] strArr = this.capitals;
            Arrays.sort(strArr);
            this.songName = (String) this.map3.get(getKey(this.map1, strArr[i]));
        }
        i = getRawResIdByName(this.songName.toLowerCase());
        if (i == 0) {
            this.play.setClickable(false);
            this.btnForward.setClickable(false);
            this.btnBackward.setClickable(false);
            return;
        }
        this.play.setClickable(true);
        this.btnForward.setClickable(true);
        this.btnForward.setClickable(true);
        this.mediaPlayer = MediaPlayer.create(this, i);
    }

    public String getKey(Map<String, String> map, String str) {
        for (Entry entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.webkit.WebView;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.mysoftpanda.android.dunyomamlakatlari.Fragments.DetailFragment;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
////import android.support.v4.app.Fragment;
////import android.support.v4.app.FragmentManager;
////import android.support.v4.app.FragmentPagerAdapter;
////import android.support.v4.view.ViewPager;
////import android.support.v4.view.ViewPager.OnPageChangeListener;
////import android.support.v7.app.AppCompatActivity;
////import com.google.android.gms.ads.AdRequest.Builder;
////import com.google.android.gms.ads.AdView;
////import com.google.android.gms.ads.InterstitialAd;
////import com.webspektr.dunyo.mamlakatlari.Fragments.DetailFragment;
//
//public class DetailActivity extends AppCompatActivity {
//    int MAX_PAGE = 195;
////    InterstitialAd admobInterstitial;
//    private ImageView btnBackward;
//    private ImageView btnForward;
//    String[] capitals;
//    int count;
//    String[] countries;
//    Fragment cur_fragment = new Fragment();
//    int index;
//    private Handler mHandler = new Handler();
//    private WebView mWebView;
//    private ImageView map;
//    Map<String, String> map1;
//    Map<String, Integer> map2;
//    Map<String, String> map3;
////    private MediaPlayer mediaPlayer;
//    private String myUrl = "file:///android_asset";
////    private ImageView next;
//    private ImageButton pause;
////    private ImageView play;
////    private boolean playState = false;
////    private ImageView prev;
//    private int seekBackwardTime = 5000;
//    private int seekForwardTime = 5000;
//    private TextView songCurrentDurationLabel;
////    private String songName;
////    SeekBar songProgressBar;
//    private TextView songTitleLabel;
////    private TextView songTotalDurationLabel;
////    private String[] songs;
//    String str;
//    private TextView text1;
//    private TextView text2;
//    private Handler threadHandler = new Handler();
//    private Utilities utils;
//    ViewPager vp;
//
//    public class UpdateSeekBarThread implements Runnable {
//        public void run() {
////            int currentPosition = DetailActivity.this.mediaPlayer.getCurrentPosition();
////            DetailActivity.this.mediaPlayer.getDuration();
////            DetailActivity.this.songCurrentDurationLabel.setText(DetailActivity.this.millisecondsToString(currentPosition));
////            DetailActivity.this.songProgressBar.setProgress(currentPosition);
////            DetailActivity.this.threadHandler.postDelayed(this, 50);
//        }
//    }
//
//    private class adapter extends FragmentPagerAdapter {
//        public adapter(FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        public Fragment getItem(int i) {
//            DetailActivity.this.cur_fragment = new DetailFragment().newInstance(i, DetailActivity.this.index);
//            return DetailActivity.this.cur_fragment;
//        }
//
//        public int getCount() {
//            return DetailActivity.this.MAX_PAGE;
//        }
//    }
//
//    /* Access modifiers changed, original: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.activity_detail);
////        ((AdView) findViewById(R.id.admob_adview_fragment)).loadAd(new Builder().build());
//        this.vp = (ViewPager) findViewById(R.id.vp);
//        this.vp.setAdapter(new adapter(getSupportFragmentManager()));
////        this.admobInterstitial = new InterstitialAd(this);
////        this.admobInterstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
////        this.admobInterstitial.loadAd(new Builder().build());
////        if (this.admobInterstitial.isLoaded()) {
////            this.admobInterstitial.show();
////        }
//        this.vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            public void onPageScrollStateChanged(int i) {
//            }
//
//            public void onPageScrolled(int i, float f, int i2) {
//            }
//
//            public void onPageSelected(int i) {
//                DetailActivity.this.count = i;
////                if (DetailActivity.this.mediaPlayer != null) {
////                    DetailActivity.this.mediaPlayer.reset();
////                }
////                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
////                DetailActivity.this.playState = false;
////                DetailActivity.this.songCurrentDurationLabel.setText("");
////                DetailActivity.this.songTotalDurationLabel.setText("");
////                DetailActivity.this.setSong(i);
////                if (DetailActivity.this.count == 5) {
////                    DetailActivity.this.admobInterstitial.show();
////                }
////                if (DetailActivity.this.count == 10) {
////                    DetailActivity.this.admobInterstitial.show();
////                }
////                if (DetailActivity.this.count == 15) {
////                    DetailActivity.this.admobInterstitial.show();
////                }
////                if (DetailActivity.this.count == 20) {
////                    DetailActivity.this.admobInterstitial.show();
////                }
////                if (DetailActivity.this.count == 25) {
////                    DetailActivity.this.admobInterstitial.show();
////                }
//            }
//        });
////        this.play = (ImageView) findViewById(R.id.btnPlay);
////        this.next = (ImageView) findViewById(R.id.btnNext);
////        this.prev = (ImageView) findViewById(R.id.btnPrevious);
////        this.songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
////        this.songProgressBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
////            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
////            }
////
////            public void onStartTrackingTouch(SeekBar seekBar) {
////            }
////
////            public void onStopTrackingTouch(SeekBar seekBar) {
////                DetailActivity.this.mediaPlayer.seekTo(seekBar.getProgress());
////            }
////        });
////        this.btnForward = (ImageView) findViewById(R.id.btnForward);
////        this.btnBackward = (ImageView) findViewById(R.id.btnBackward);
////        this.songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
////        this.songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
////        this.play.setOnClickListener(new OnClickListener() {
////            public void onClick(View view) {
////                if (DetailActivity.this.playState) {
////                    DetailActivity.this.play.setImageResource(R.drawable.btn_play);
////                    DetailActivity.this.doPause(view);
////                    DetailActivity.this.playState = false;
////                    return;
////                }
////                DetailActivity.this.play.setImageResource(R.drawable.btn_pause);
////                DetailActivity.this.doStart(view);
////                DetailActivity.this.playState = true;
////            }
////        });
////        this.btnBackward.setOnClickListener(new OnClickListener() {
////            public void onClick(View view) {
////                DetailActivity.this.doRewind(view);
////            }
////        });
////        this.btnForward.setOnClickListener(new OnClickListener() {
////            public void onClick(View view) {
////                DetailActivity.this.doFastForward(view);
////            }
////        });
////        this.next.setOnClickListener(new OnClickListener() {
////            public void onClick(View view) {
////                if (DetailActivity.this.mediaPlayer != null) {
////                    DetailActivity.this.mediaPlayer.reset();
////                }
////                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
////                DetailActivity.this.playState = false;
////                DetailActivity.this.songCurrentDurationLabel.setText("");
////                DetailActivity.this.songTotalDurationLabel.setText("");
////                DetailActivity detailActivity = DetailActivity.this;
////                detailActivity.count++;
////                if (DetailActivity.this.count == DetailActivity.this.MAX_PAGE) {
////                    DetailActivity.this.count = 0;
////                }
////                DetailActivity.this.vp.setCurrentItem(DetailActivity.this.count);
////                DetailActivity.this.vp.setCurrentItem(DetailActivity.this.count);
////            }
////        });
////        this.prev.setOnClickListener(new OnClickListener() {
////            public void onClick(View view) {
////                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
////                DetailActivity.this.playState = false;
////                DetailActivity.this.songCurrentDurationLabel.setText("");
////                DetailActivity.this.songTotalDurationLabel.setText("");
////                if (DetailActivity.this.mediaPlayer != null) {
////                    DetailActivity.this.mediaPlayer.reset();
////                }
////                DetailActivity detailActivity = DetailActivity.this;
////                detailActivity.count--;
////                if (DetailActivity.this.count < 0) {
////                    DetailActivity.this.count = DetailActivity.this.MAX_PAGE;
////                }
////                DetailActivity.this.vp.setCurrentItem(DetailActivity.this.count);
////            }
////        });
//        this.countries = getResources().getStringArray(R.array.countries);
//        this.capitals = getResources().getStringArray(R.array.capitals);
////        this.songs = getResources().getStringArray(R.array.desctiption_sound);
//        String[] strArr = this.capitals;
//        Arrays.sort(strArr);
//        setTitle(getResources().getString(R.string.app_name));
//        int i = 0;
//        this.index = getIntent().getIntExtra(ListActivity.INDEX_TAG, 0);
//        this.str = getIntent().getStringExtra(ListActivity.CC_TAG);
//        this.map1 = new HashMap();
//        this.map3 = new HashMap();
//        for (int i2 = 0; i2 < this.countries.length; i2++) {
//            this.map1.put(this.countries[i2], this.capitals[i2]);
//        }
////        while (i < this.countries.length) {
////            this.map3.put(this.countries[i]);
////            i++;
////        }
//        if (this.index == 1) {
//            this.count = getIndex(this.countries, this.str);
//        } else {
//            this.count = getIndex(strArr, this.str);
//        }
//        this.vp.setCurrentItem(this.count);
////        if (this.mediaPlayer == null) {
////            setSong(this.count);
////        }
//    }
//
//    private String millisecondsToString(int i) {
//        long j = (long) (i / 60000);
//        long j2 = (long) ((i % 60000) / 1000);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(j);
//        stringBuilder.append(":");
//        stringBuilder.append(j2);
//        return stringBuilder.toString();
//    }
//
////    public void doStart(View view) {
////        int duration = this.mediaPlayer.getDuration();
////        int currentPosition = this.mediaPlayer.getCurrentPosition();
////        if (currentPosition == 0) {
////            this.songProgressBar.setMax(duration);
////            this.songTotalDurationLabel.setText(millisecondsToString(duration));
////        } else if (currentPosition == duration) {
////            this.mediaPlayer.reset();
////        }
////        this.mediaPlayer.start();
////        this.mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
////            public void onCompletion(MediaPlayer mediaPlayer) {
////                mediaPlayer.seekTo(0);
////                DetailActivity.this.play.setImageResource(R.drawable.btn_play);
////                DetailActivity.this.playState = false;
////            }
////        });
////        this.threadHandler.postDelayed(new UpdateSeekBarThread(), 50);
////    }
//
//    public int getIndex(String[] strArr, String str) {
//        for (int i = 0; i < strArr.length; i++) {
//            if (strArr[i].equals(str)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
////    public void doPause(View view) {
////        this.mediaPlayer.pause();
////    }
//
////    public void doRewind(View view) {
////        int currentPosition = this.mediaPlayer.getCurrentPosition() - 5000;
////        if (currentPosition > 0) {
////            this.mediaPlayer.seekTo(currentPosition);
////        }
////    }
//
//    public void doFastForward(View view) {
////        int currentPosition = this.mediaPlayer.getCurrentPosition() + 5000;
////        if (currentPosition < this.mediaPlayer.getDuration()) {
////            this.mediaPlayer.seekTo(currentPosition);
////        }
//    }
//
//    public int getRawResIdByName(String str) {
//        return getResources().getIdentifier(str, "raw", getPackageName());
//    }
//
////    private void setSong(int i) {
//////        if (this.index == 1) {
//////            this.songName = (String) this.map3.get(this.countries[i]);
//////        } else {
//////            String[] strArr = this.capitals;
//////            Arrays.sort(strArr);
//////            this.songName = (String) this.map3.get(getKey(this.map1, strArr[i]));
//////        }
////        i = getRawResIdByName(this.songName.toLowerCase());
////        if (i == 0) {
////            this.play.setClickable(false);
////            this.btnForward.setClickable(false);
////            this.btnBackward.setClickable(false);
////            return;
////        }
////        this.play.setClickable(true);
////        this.btnForward.setClickable(true);
////        this.btnForward.setClickable(true);
////        this.mediaPlayer = MediaPlayer.create(this, i);
////    }
//
//    public String getKey(Map<String, String> map, String str) {
//        for (Entry entry : map.entrySet()) {
//            if (str.equals(entry.getValue())) {
//                return (String) entry.getKey();
//            }
//        }
//        return null;
//    }
//
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//}
