package com.example.jakeg.slidingpuzzlehero1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class activity_3x3 extends AppCompatActivity {

        public int counter = 0;
        public int masterPos = 8;
        GridView gridview;
        ImageAdapter imageAdapter;




    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3x3);



            gridview = (GridView) findViewById(R.id.gridview);
            imageAdapter= new ImageAdapter(this);
            gridview.setAdapter(imageAdapter);





            Random r = new Random();
            int randomNumber = r.nextInt(5);
            if (randomNumber > 1)
            {
                imageAdapter.mThumbIds[0] = R.drawable.b1;
                imageAdapter.mThumbIds[1] = R.drawable.b2;
                imageAdapter.mThumbIds[2] = R.drawable.b3;
                imageAdapter.mThumbIds[3] = R.drawable.b4;
                imageAdapter.mThumbIds[4] = R.drawable.b5;
                imageAdapter.mThumbIds[5] = R.drawable.b6;
                imageAdapter.mThumbIds[6] = R.drawable.b7;
                imageAdapter.mThumbIds[7] = R.drawable.b8;
                imageAdapter.mThumbIds[8] = R.color.colorPrimaryDark;




            }

            randomizeView(imageAdapter);

            final TextView cdTimer = findViewById(R.id.countDown);


        new CountDownTimer(6000000, 1000) {

            public void onTick(long millisUntilFinished) {


                cdTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                cdTimer.setText("Oops, you lose!");
            }
        }.start();



            final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonsound);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    counter++;






                    int right = position+1;
                    int left = position-1;
                    int down = position+3;
                    int up = position-3;

                    if(position == 0)
                    {
                        if(right == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();

                    }
                    if(position == 1)
                    {
                        if(right == masterPos || left == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();

                    }
                    if(position == 2)
                    {
                        if(left == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }

                    if(position == 3)
                    {
                        if(right == masterPos || up == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 4)
                    {
                        if(right == masterPos || up == masterPos || down == masterPos || left == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 5)
                    {
                        if(left == masterPos || up == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }

                    if(position == 6)
                    {
                        if(right == masterPos || up == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 7)
                    {
                        if(right == masterPos || left == masterPos || up == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 8)
                    {
                        if(left == masterPos || up  == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(activity_3x3.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }






                }
            });
        }

        public boolean checkWin(ImageAdapter curAdapter)
        {
            Integer[] trueArray = {


                    R.drawable.donkeypart1,
                    R.drawable.donkeypart2,
                    R. drawable.donkeypart3,
                    R.drawable.donkeypart4,
                    R.drawable.donkeypart5,
                    R.drawable.donkeypartt6,
                    R.drawable.donkeypart7,
                    R.drawable.donkeypart8,
                    R.color.colorPrimaryDark
            };

            boolean solved = false;

            for (int i = 0; i < curAdapter.mThumbIds.length; i++) {
                if (curAdapter.mThumbIds[i].equals(trueArray[i])) {
                    solved = true;
                } else {
                    solved = false;
                    break;
                }
            }

            return solved;
        }




        public void swap(int pos, ImageAdapter xAdapter)
        {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonsound2);
            mediaPlayer.start();

            int val = xAdapter.mThumbIds[masterPos];
            xAdapter.mThumbIds[masterPos] = xAdapter.mThumbIds[pos];
            xAdapter.mThumbIds[pos] = val;

            if(checkWin(xAdapter))
            {
                Toast.makeText(activity_3x3.this, "You won",
                        Toast.LENGTH_SHORT).show();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                if(pref.getInt("scoreKey3", 0) > counter)
                {
                    editor.putInt("scoreKey3", counter);
                    editor.commit();
                }

            }

            xAdapter.notifyDataSetChanged();
            gridview.invalidateViews();
            masterPos = pos;



        }

        public void randomizeView(ImageAdapter xAdapter)
        {
            int val = xAdapter.mThumbIds[masterPos];
            randomizeArray(xAdapter.mThumbIds);





            for(int i = 0; i < xAdapter.mThumbIds.length; i++)
            {
                if (xAdapter.mThumbIds[i] == val)
                {
                    masterPos = i;
                }
            }

            xAdapter.notifyDataSetChanged();
            gridview.invalidateViews();



        }

        public static Integer[] randomizeArray(Integer [] array)
        {
            Random rgen = new Random();  // Random number generator


            for (int i=0; i<array.length; i++) {

                int randomPosition = rgen.nextInt(array.length);
                int temp = array[i];
                array[i] = array[randomPosition];
                array[randomPosition] = temp;
            }

            return array;
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



