package com.mysoftpanda.android.dunyomamlakatlari;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    public ImageView emotion;
    public TextView tv1;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_result);
        setTitle("Test natijasi");
        int intExtra = getIntent().getIntExtra(QuizActivity.SCORE_TAG, 0);
        this.tv1 = (TextView) findViewById(R.id.quizResult21);
        this.tv1.setText(String.valueOf(intExtra));
        this.emotion = (ImageView) findViewById(R.id.quizResult3);
        if (intExtra < 6) {
            this.emotion.setImageResource(R.drawable.emotion0);
        } else if (intExtra > 5 && intExtra < 10) {
            this.emotion.setImageResource(R.drawable.emotion1);
        } else if (intExtra > 9 && intExtra < 15) {
            this.emotion.setImageResource(R.drawable.emotion2);
        } else if (intExtra <= 14 || intExtra >= 19) {
            this.emotion.setImageResource(R.drawable.emotion4);
        } else {
            this.emotion.setImageResource(R.drawable.emotion3);
        }
    }
}

//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
////import com.google.android.gms.ads.AdRequest.Builder;
////import com.google.android.gms.ads.AdView;
//
//public class ResultActivity extends AppCompatActivity {
//    public ImageView emotion;
//    public TextView tv1;
//
//    /* Access modifiers changed, original: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//      /*  setContentView(R.layout.activity_result);
//        setTitle("Test natijasi");
//        int intExtra = getIntent().getIntExtra(QuizActivity.SCORE_TAG, 0);
//        this.tv1 = (TextView) findViewById(R.id.quizResult21);
//        this.tv1.setText(String.valueOf(intExtra));
//        this.emotion = (ImageView) findViewById(R.id.quizResult3);
//        if (intExtra < 6) {
//            this.emotion.setImageResource(R.drawable.emotion0);
//        } else if (intExtra > 5 && intExtra < 10) {
//            this.emotion.setImageResource(R.drawable.emotion1);
//        } else if (intExtra > 9 && intExtra < 15) {
//            this.emotion.setImageResource(R.drawable.emotion2);
//        } else if (intExtra <= 14 || intExtra >= 19) {
//            this.emotion.setImageResource(R.drawable.emotion4);
//        } else {
//            this.emotion.setImageResource(R.drawable.emotion3);
//        }*/
////        ((AdView) findViewById(R.id.admob_adview3)).loadAd(new Builder().build());
//    }
//}
