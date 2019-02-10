package com.blogspot.pavankreddytadi.imfeelingbored;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView activities;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activities = findViewById(R.id.activity_here);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void showMeAnActivity(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        FetchAnActivity faa = new FetchAnActivity(activities,progressBar);
        faa.execute();
    }
}
