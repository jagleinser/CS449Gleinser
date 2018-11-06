package com.example.jakeg.slidingpuzzlehero1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class homePage extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.app_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        Button begin3x3 = (Button) findViewById(R.id.button3x3);
        Button begin5x5 = (Button) findViewById(R.id.button5x5);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        int counter = pref.getInt("scoreKey3", 0);
        TextView counterScore = (TextView) findViewById(R.id.score3x3);
        int counter5x5 =  pref.getInt("scoreKey5", 0);
        TextView counterScore5x5 = (TextView) findViewById(R.id.score5x5);

        if(counter5x5 > 0)
        {
            counterScore5x5.setText("High Score: " + String.valueOf(counter5x5));
        }

        if(counter > 0)
        {
            counterScore.setText("High Score: " + String.valueOf(counter));
        }

        begin3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchActivity(view);
                mediaPlayer.stop();


            }
        });

        begin5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity(view);
                mediaPlayer.stop();

            }
        });
    }


    public void switchActivity(View v)
    {
        if (v == findViewById(R.id.button3x3)) {
            Intent intent = new Intent(this, activity_3x3.class);
            startActivity(intent);
        }

        if (v == findViewById(R.id.button5x5))
        {
            Intent intent = new Intent(this, activity_5x5.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reset:
                return true;
            case R.id.about:
                Intent intent = new Intent(this, about.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(this, settings.class);
                startActivity(settingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

