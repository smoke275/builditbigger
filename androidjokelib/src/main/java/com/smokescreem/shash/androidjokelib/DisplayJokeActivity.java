package com.smokescreem.shash.androidjokelib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    public final static String INTENT_JOKE = "INTENT_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        String joke = getIntent().getStringExtra(INTENT_JOKE);
        TextView textViewJoke = (TextView) findViewById(R.id.textview_joke);
        textViewJoke.setText(joke);
    }
}
