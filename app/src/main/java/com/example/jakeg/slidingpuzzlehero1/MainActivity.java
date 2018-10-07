package com.example.jakeg.slidingpuzzlehero1;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

        public int counter = 0;
        public int masterPos = 8;
        GridView gridview;
        ImageAdapter imageAdapter;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            gridview = (GridView) findViewById(R.id.gridview);
            imageAdapter= new ImageAdapter(this);
            gridview.setAdapter(imageAdapter);

            final Integer [] originalSet = imageAdapter.mThumbIds;


            randomizeView(imageAdapter);


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
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();

                    }
                    if(position == 1)
                    {
                        if(right == masterPos || left == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();

                    }
                    if(position == 2)
                    {
                        if(left == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }

                    if(position == 3)
                    {
                        if(right == masterPos || up == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 4)
                    {
                        if(right == masterPos || up == masterPos || down == masterPos || left == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 5)
                    {
                        if(left == masterPos || up == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }

                    if(position == 6)
                    {
                        if(right == masterPos || up == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 7)
                    {
                        if(right == masterPos || left == masterPos || up == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
                                    Toast.LENGTH_SHORT).show();
                    }
                    if(position == 8)
                    {
                        if(left == masterPos || up  == masterPos)
                            swap(position, imageAdapter);
                        else
                            Toast.makeText(MainActivity.this, "Invalid Move",
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


            int val = xAdapter.mThumbIds[masterPos];
            xAdapter.mThumbIds[masterPos] = xAdapter.mThumbIds[pos];
            xAdapter.mThumbIds[pos] = val;

            if(checkWin(xAdapter))
            {
                Toast.makeText(MainActivity.this, "You won",
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



    }



