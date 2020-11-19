package com.mysoftpanda.android.dunyomamlakatlari;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizActivity extends AppCompatActivity implements OnClickListener {
    public static final String SCORE_TAG = "score_tag";
    private int answerId = 0;
    private ArrayList<String> answers = new ArrayList();
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    public HashMap<Integer, ArrayList<String>> choice;
    private int count = 0;


    private int numberQuestions = 1;
    private ArrayList<String> questions = new ArrayList();
    private int score = 0;
    private TextView tv1;
    private TextView tv2;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_quiz);
        setTitle("Test savollari");
        this.questions.addAll(getIntent().getStringArrayListExtra(MainActivity.QUIZ_QUESTION_INDEX));
        this.answers.addAll(getIntent().getStringArrayListExtra(MainActivity.QUIZ_ANSWER_INDEX));
        this.choice = (HashMap) getIntent().getSerializableExtra(MainActivity.QUIZ_CHOICES_INDEX);
        this.tv1 = (TextView) findViewById(R.id.testquizText);
        this.tv2 = (TextView) findViewById(R.id.qText2);
        this.bt1 = (Button) findViewById(R.id.buttonQuiz1);
        this.bt2 = (Button) findViewById(R.id.buttonQuiz2);
        this.bt3 = (Button) findViewById(R.id.buttonQuiz3);
        this.bt4 = (Button) findViewById(R.id.buttonQuiz4);
        this.tv1.setText((CharSequence) this.questions.get(this.count));
        this.tv2.setText(String.valueOf(this.numberQuestions));
        this.bt1.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(0));
        this.bt2.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(1));
        this.bt3.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(2));
        this.bt4.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(3));
        this.bt1.setOnClickListener(this);
        this.bt2.setOnClickListener(this);
        this.bt3.setOnClickListener(this);
        this.bt4.setOnClickListener(this);


    }

    public void onClick(View view) {
        if (((Button) view).getText().toString().equals(this.answers.get(this.answerId))) {
            this.score++;
        }
        this.answerId++;
        this.count++;
        this.numberQuestions++;
        if (this.count < 20) {
            this.tv1.setText((CharSequence) this.questions.get(this.count));
            this.tv2.setText(String.valueOf(this.numberQuestions));
            this.bt1.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(0));
            this.bt2.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(1));
            this.bt3.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(2));
            this.bt4.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(3));
            return;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(SCORE_TAG, this.score);
        startActivity(intent);
        finish();
    }

    @SuppressLint("ResourceType")
    public void onBackPressed() {

        new AlertDialog.Builder(this).setTitle("Tasdiqlash").setMessage("Testni tugatmoqchimisiz?").setPositiveButton(17039379, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                QuizActivity.this.finish();
            }
        }).setNegativeButton(17039369, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).show();
    }
}
//
//import android.annotation.SuppressLint;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class QuizActivity extends AppCompatActivity implements OnClickListener {
//    public static final String SCORE_TAG = "score_tag";
//    private int answerId = 0;
//    private ArrayList<String> answers = new ArrayList();
//    private Button bt1;
//    private Button bt2;
//    private Button bt3;
//    private Button bt4;
//    public HashMap<Integer, ArrayList<String>> choice;
//    private int count = 0;
////    private InterstitialAd interstitial;
////    private InterstitialAd mInterstitialAd;
//    private int numberQuestions = 1;
//    private ArrayList<String> questions = new ArrayList();
//    private int score = 0;
//    private TextView tv1;
//    private TextView tv2;
//
//    /* Access modifiers changed, original: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//       /* setContentView(R.layout.activity_quiz);
//        setTitle("Test savollari");
//        this.questions.addAll(getIntent().getStringArrayListExtra(MainActivity.QUIZ_QUESTION_INDEX));
//        this.answers.addAll(getIntent().getStringArrayListExtra(MainActivity.QUIZ_ANSWER_INDEX));
//        this.choice = (HashMap) getIntent().getSerializableExtra(MainActivity.QUIZ_CHOICES_INDEX);
//        this.tv1 = (TextView) findViewById(R.id.testquizText);
//        this.tv2 = (TextView) findViewById(R.id.qText2);
//        this.bt1 = (Button) findViewById(R.id.buttonQuiz1);
//        this.bt2 = (Button) findViewById(R.id.buttonQuiz2);
//        this.bt3 = (Button) findViewById(R.id.buttonQuiz3);
//        this.bt4 = (Button) findViewById(R.id.buttonQuiz4);
//        this.tv1.setText((CharSequence) this.questions.get(this.count));
//        this.tv2.setText(String.valueOf(this.numberQuestions));
//        this.bt1.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(0));
//        this.bt2.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(1));
//        this.bt3.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(2));
//        this.bt4.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(3));
//        this.bt1.setOnClickListener(this);
//        this.bt2.setOnClickListener(this);
//        this.bt3.setOnClickListener(this);
//        this.bt4.setOnClickListener(this);*/
////        AdView adView = (AdView) findViewById(R.id.admob_adview2);
////        AdRequest build = new Builder().build();
////        adView.loadAd(build);
////        this.interstitial = new InterstitialAd(this);
////        this.interstitial.setAdUnitId(getString(R.string.admob_interstitial));
////        this.interstitial.loadAd(build);
//    }
//
//    public void onClick(View view) {
//        if (((Button) view).getText().toString().equals(this.answers.get(this.answerId))) {
//            this.score++;
//        }
//        this.answerId++;
//        this.count++;
//        this.numberQuestions++;
//        if (this.count < 20) {
//            this.tv1.setText((CharSequence) this.questions.get(this.count));
//            this.tv2.setText(String.valueOf(this.numberQuestions));
//            this.bt1.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(0));
//            this.bt2.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(1));
//            this.bt3.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(2));
//            this.bt4.setText((CharSequence) ((ArrayList) this.choice.get(Integer.valueOf(this.count))).get(3));
//            return;
//        }
////        this.interstitial.show();
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra(SCORE_TAG, this.score);
//        startActivity(intent);
//        finish();
//    }
//
//    @SuppressLint("ResourceType")
//    public void onBackPressed() {
////        this.interstitial.show();
//        new AlertDialog.Builder(this).setTitle("Tasdiqlash").setMessage("Testni tugatmoqchimisiz?").setPositiveButton(17039379, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                QuizActivity.this.finish();
//            }
//        }).setNegativeButton(17039369, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        }).show();
//    }
//}
