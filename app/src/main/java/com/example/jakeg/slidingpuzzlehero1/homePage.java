package com.example.jakeg.slidingpuzzlehero1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button begin3x3 = (Button) findViewById(R.id.button3x3);
        Button begin5x5 = (Button) findViewById(R.id.button5x5);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        int counter = pref.getInt("scoreKey3", 0);
        TextView counterScore = (TextView) findViewById(R.id.score3x3);
        int counter5x5 =  pref.getInt("scoreKey5", 0);
        TextView counterScore5x5 = (TextView) findViewById(R.id.score5x5);

        counterScore.setText(String.valueOf(counter));
        counterScore5x5.setText(String.valueOf(counter5x5));

        begin3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity(view);

            }
        });

        begin5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity(view);

            }
        });
    }

    public void switchActivity(View v)
    {
        if (v == findViewById(R.id.button3x3)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (v == findViewById(R.id.button5x5))
        {
            Intent intent = new Intent(this, activity_5x5.class);
            startActivity(intent);
        }
    }

}

