package com.example.jakeg.slidingpuzzlehero1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


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


                    int right = position+1;
                    int left = position-1;
                    int down = position+3;
                    int up = position-3;

                    if(position == 0)
                    {
                        if(right == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                    }
                    if(position == 1)
                    {
                        if(right == masterPos || left == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                    }
                    if(position == 2)
                    {
                        if(left == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                    }

                    if(position == 3)
                    {
                        if(right == masterPos || up == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                    }
                    if(position == 4)
                    {
                        if(right == masterPos || up == masterPos || down == masterPos || left == masterPos)
                            swap(position, imageAdapter);
                    }
                    if(position == 5)
                    {
                        if(left == masterPos || up == masterPos || down == masterPos)
                            swap(position, imageAdapter);
                    }

                    if(position == 6)
                    {
                        if(right == masterPos || up == masterPos)
                            swap(position, imageAdapter);
                    }
                    if(position == 7)
                    {
                        if(right == masterPos || left == masterPos || up == masterPos)
                            swap(position, imageAdapter);
                    }
                    if(position == 8)
                    {
                        if(left == masterPos || up  == masterPos)
                            swap(position, imageAdapter);
                    }

                    if(checkWin(imageAdapter))
                    {
                        Toast.makeText(MainActivity.this, "You won",
                                Toast.LENGTH_SHORT).show();
                    }




                }
            });
        }

        public boolean checkWin(ImageAdapter curAdapter)
        {

            boolean solved = false;

            for (int i = 0; i < curAdapter.mThumbIds.length; i++) {
                if (curAdapter.mThumbIds[i].equals(String.valueOf(i))) {
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



