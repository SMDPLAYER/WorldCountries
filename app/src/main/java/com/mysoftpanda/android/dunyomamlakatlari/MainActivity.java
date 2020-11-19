package com.mysoftpanda.android.dunyomamlakatlari;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final String INDEX = "index";
    public static final String QUIZ_ANSWER_INDEX = "quizanswerindex";
    public static final String QUIZ_CHOICES_INDEX = "quizchoicesindex";
    public static final String QUIZ_QUESTION_INDEX = "quizindex";
    private ArrayList<String> answers = new ArrayList();
    private HashMap<Integer, ArrayList<String>> choice = new HashMap();
    SharedPreferences mSharedPreferences;
    String marketLink;
    private ArrayList<String> questions = new ArrayList();

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.app_name));
        TextView button = (TextView) findViewById(R.id.countries_btn);
        TextView button2 = (TextView) findViewById(R.id.capitals_btn);
        TextView button3 = (TextView) findViewById(R.id.test_btn);
        ((ImageButton) findViewById(R.id.otherapps)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
//                MainActivity.this.startActivity(new Intent(MainActivity.this, WsAppListActivity.class));
            }
        });
        ((ImageButton) findViewById(R.id.rateapps)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(MainActivity.this.getPackageName());
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
            }
        });
        this.mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        this.marketLink = "https://play.google.com/store/apps/details?id=";
        ((ImageButton) findViewById(R.id.shareapps)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(MainActivity.this.getResources().getString(R.string.app_name));
                stringBuilder.append(" android ilovasi \n");
                stringBuilder.append(MainActivity.this.marketLink);
                stringBuilder.append(MainActivity.this.getPackageName());
                String stringBuilder2 = stringBuilder.toString();
                intent.putExtra("android.intent.extra.SUBJECT", "Android app");
                intent.putExtra("android.intent.extra.TEXT", stringBuilder2);
                MainActivity.this.startActivity(Intent.createChooser(intent, "Yaqinlaringizga tavsiya qiling!"));
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(MainActivity.INDEX, 1);
                MainActivity.this.startActivity(intent);
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(MainActivity.INDEX, 2);
                MainActivity.this.startActivity(intent);
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                ArrayList arrayList = new ArrayList();
                MainActivity.this.getRandomList(arrayList);
                MainActivity.this.getDatafromJson(MainActivity.this.loadJson(), arrayList);
                intent.putStringArrayListExtra(MainActivity.QUIZ_QUESTION_INDEX, MainActivity.this.questions);
                intent.putStringArrayListExtra(MainActivity.QUIZ_ANSWER_INDEX, MainActivity.this.answers);
                intent.putExtra(MainActivity.QUIZ_CHOICES_INDEX, MainActivity.this.choice);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        this.questions.clear();
        this.answers.clear();
        this.choice.clear();
    }

    private String loadJson() {
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            InputStream open = getAssets().open("quiz.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(readLine);
                stringBuilder2.append("\n");
                stringBuilder.append(stringBuilder2.toString());
            }
            bufferedReader.close();
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString().trim();
    }

    private void getDatafromJson(String str, ArrayList<Integer> arrayList) {
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("table");
            for (int i = 0; i < 20; i++) {
                this.questions.add(jSONArray.getJSONObject(((Integer) arrayList.get(i)).intValue()).get("question").toString());
                this.answers.add(jSONArray.getJSONObject(((Integer) arrayList.get(i)).intValue()).get("answer").toString());
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < 4; i2++) {
                    arrayList2.add(jSONArray.getJSONObject(((Integer) arrayList.get(i)).intValue()).getJSONArray("choice").getString(i2));
                }
                this.choice.put(Integer.valueOf(i), arrayList2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRandomList(ArrayList<Integer> arrayList) {
        arrayList.clear();
        while (arrayList.size() < 20) {
            int random = (int) (Math.random() * 181.0d);
            if (!arrayList.contains(Integer.valueOf(random))) {
                arrayList.add(Integer.valueOf(random));
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.drawable.notification_exit);
        builder.setMessage("Dasturdan chiqmoqchimisz?");
        builder.setPositiveButton("Ha", new DialogInterface.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(67108864);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Yo\'q", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog create = builder.create();
        create.show();
        Button button = create.getButton(-2);
        button.setBackgroundColor(-1);
        button.setTextColor(-16776961);
        Button button2 = create.getButton(-1);
        button2.setBackgroundColor(-1);
        button2.setTextColor(-16776961);
        return true;
    }
}

//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//
////import com.google.android.gms.ads.AdRequest.Builder;
////import com.google.android.gms.ads.AdView;
////import com.google.android.gms.ads.InterstitialAd;
//
//public class MainActivity extends AppCompatActivity {
//    public static final String INDEX = "index";
//    public static final String QUIZ_ANSWER_INDEX = "quizanswerindex";
//    public static final String QUIZ_CHOICES_INDEX = "quizchoicesindex";
//    public static final String QUIZ_QUESTION_INDEX = "quizindex";
////    InterstitialAd admobInterstitial;
//    private ArrayList<String> answers = new ArrayList();
//    private HashMap<Integer, ArrayList<String>> choice = new HashMap();
//    SharedPreferences mSharedPreferences;
//    String marketLink;
//    private ArrayList<String> questions = new ArrayList();
//
//    /* Access modifiers changed, original: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.activity_main);
//        setTitle(getResources().getString(R.string.app_name));
//        TextView button = (TextView) findViewById(R.id.countries_btn);
//        TextView button2 = (TextView) findViewById(R.id.capitals_btn);
////        Button button3 = (Button) findViewById(R.id.quiz);
////        this.admobInterstitial = new InterstitialAd(this);
////        this.admobInterstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
////        this.admobInterstitial.loadAd(new Builder().build());
////        if (this.admobInterstitial.isLoaded()) {
////            this.admobInterstitial.show();
////        }
////        ((ImageButton) findViewById(R.id.otherapps)).setOnClickListener(new OnClickListener() {
////            public void onClick(View view) {
////                MainActivity.this.startActivity(new Intent(MainActivity.this, WsAppListActivity.class));
////            }
////        });
//        ((ImageButton) findViewById(R.id.rateapps)).setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("https://play.google.com/store/apps/details?id=");
//                stringBuilder.append(MainActivity.this.getPackageName());
//                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
//            }
//        });
//        this.mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
//        this.marketLink = "https://play.google.com/store/apps/details?id=";
//        ((ImageButton) findViewById(R.id.shareapps)).setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent("android.intent.action.SEND");
//                intent.setType("text/plain");
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(MainActivity.this.getResources().getString(R.string.app_name));
//                stringBuilder.append(" android ilovasi \n");
//                stringBuilder.append(MainActivity.this.marketLink);
//                stringBuilder.append(MainActivity.this.getPackageName());
//                String stringBuilder2 = stringBuilder.toString();
//                intent.putExtra("android.intent.extra.SUBJECT", "Android app");
//                intent.putExtra("android.intent.extra.TEXT", stringBuilder2);
//                MainActivity.this.startActivity(Intent.createChooser(intent, "Yaqinlaringizga tavsiya qiling!"));
//            }
//        });
//        button.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ListActivity.class);
//                intent.putExtra(MainActivity.INDEX, 1);
//                MainActivity.this.startActivity(intent);
//            }
//        });
//        button2.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ListActivity.class);
//                intent.putExtra(MainActivity.INDEX, 2);
//                MainActivity.this.startActivity(intent);
//            }
//        });
//      /*  button3.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
//                ArrayList arrayList = new ArrayList();
//                MainActivity.this.getRandomList(arrayList);
//                MainActivity.this.getDatafromJson(MainActivity.this.loadJson(), arrayList);
//                intent.putStringArrayListExtra(MainActivity.QUIZ_QUESTION_INDEX, MainActivity.this.questions);
//                intent.putStringArrayListExtra(MainActivity.QUIZ_ANSWER_INDEX, MainActivity.this.answers);
//                intent.putExtra(MainActivity.QUIZ_CHOICES_INDEX, MainActivity.this.choice);
//                MainActivity.this.startActivity(intent);
//            }
//        });*/
////        ((AdView) findViewById(R.id.admob_adview)).loadAd(new Builder().build());
//    }
//
//    /* Access modifiers changed, original: protected */
//    public void onStop() {
//        super.onStop();
//        this.questions.clear();
//        this.answers.clear();
//        this.choice.clear();
//    }
//
//    private String loadJson() {
//        StringBuilder stringBuilder = new StringBuilder("");
//        try {
//            InputStream open = getAssets().open("quiz.json");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
//            while (true) {
//                String readLine = bufferedReader.readLine();
//                if (readLine == null) {
//                    break;
//                }
//                StringBuilder stringBuilder2 = new StringBuilder();
//                stringBuilder2.append(readLine);
//                stringBuilder2.append("\n");
//                stringBuilder.append(stringBuilder2.toString());
//            }
//            bufferedReader.close();
//            open.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return stringBuilder.toString().trim();
//    }
//
//    private void getDatafromJson(String str, ArrayList<Integer> arrayList) {
//        try {
//            JSONArray jSONArray = new JSONObject(str).getJSONArray("table");
//            for (int i = 0; i < 20; i++) {
//                this.questions.add(jSONArray.getJSONObject(((Integer) arrayList.get(i)).intValue()).get("question").toString());
//                this.answers.add(jSONArray.getJSONObject(((Integer) arrayList.get(i)).intValue()).get("answer").toString());
//                ArrayList arrayList2 = new ArrayList();
//                for (int i2 = 0; i2 < 4; i2++) {
//                    arrayList2.add(jSONArray.getJSONObject(((Integer) arrayList.get(i)).intValue()).getJSONArray("choice").getString(i2));
//                }
//                this.choice.put(Integer.valueOf(i), arrayList2);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getRandomList(ArrayList<Integer> arrayList) {
//        arrayList.clear();
//        while (arrayList.size() < 20) {
//            int random = (int) (Math.random() * 181.0d);
//            if (!arrayList.contains(Integer.valueOf(random))) {
//                arrayList.add(Integer.valueOf(random));
//            }
//        }
//    }
//
//    public boolean onKeyDown(int i, KeyEvent keyEvent) {
////        this.admobInterstitial.show();
////        if (i != 4) {
////            return super.onKeyDown(i, keyEvent);
////        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.app_name));
//        builder.setIcon(R.drawable.notification_exit);
//        builder.setMessage("Log Out");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent("android.intent.action.MAIN");
//                intent.addCategory("android.intent.category.HOME");
//                intent.setFlags(67108864);
//                MainActivity.this.startActivity(intent);
//                MainActivity.this.finish();
//                System.exit(0);
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//            }
//        });
//        AlertDialog create = builder.create();
//        create.show();
//        Button button = create.getButton(-2);
//        button.setBackgroundColor(-1);
//        button.setTextColor(-16776961);
//        Button button2 = create.getButton(-1);
//        button2.setBackgroundColor(-1);
//        button2.setTextColor(-16776961);
//        return true;
//    }
//}
