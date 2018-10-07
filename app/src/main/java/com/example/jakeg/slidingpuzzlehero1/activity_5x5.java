package com.example.jakeg.slidingpuzzlehero1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;

public class activity_5x5 extends AppCompatActivity {

    public int counter = 0;
    public int masterPos = 24;
    GridView gridview;
    ImageAdapter imageAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setNumColumns(5);
        imageAdapter= new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);


        randomizeView(imageAdapter);



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                counter = counter + 1;


                boolean isRight = position+1 == masterPos;
                boolean isLeft = position-1 == masterPos;
                boolean isDown = position+5 == masterPos;
                boolean isUp = position-5 == masterPos;

                if (position % 5 == 1 || position % 5 == 2 || position % 5 == 3 )
                {
                    if(position == 1 || position == 2 || position == 3)
                    {
                        if (isRight || isLeft || isDown)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                    }
                    else if (position == 21 || position == 22 || position == 23)
                    {
                        if (isRight || isLeft || isUp)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                    }
                    else
                        if (isRight || isLeft || isDown || isUp)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();

                }
                if(position % 5 == 0)
                {
                    if (position == 0)
                    {
                        if (isRight || isDown)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                    }
                    else if (position == 20)
                    {
                        if (isUp || isRight)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();

                    }
                    else
                    {
                        if(isRight || isDown || isUp)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                    }
                }

                if(position % 5 == 4)
                {
                    if(position == 4)
                    {
                        if (isLeft || isDown)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                    }
                    else if(position == 24)
                    {
                        if (isLeft || isUp)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                    }
                    else
                        if(isLeft || isUp || isDown)
                        {
                            swap(position, imageAdapter);
                        }
                        else
                            invalidMove();
                }












            }
        });
    }

    public boolean checkWin(ImageAdapter curAdapter)
    {
        Integer[] mThumbIds2test = {

                R.drawable.h1,
                R.drawable.h2,
                R.drawable.h3,
                R.drawable.h4,
                R.drawable.h5,
                R.drawable.h6,
                R.drawable.h7,
                R.drawable.h8,
                R.drawable.h9,
                R.drawable.h10,
                R.drawable.h11,
                R.drawable.h12,
                R.drawable.h13,
                R.drawable.h14,
                R.drawable.h15,
                R.drawable.h16,
                R.drawable.h17,
                R.drawable.h18,
                R.drawable.h19,
                R.drawable.h20,
                R.drawable.h21,
                R.drawable.h22,
                R.drawable.h23,
                R.drawable.h24,
                R.color.colorPrimaryDark
        };
        boolean solved = false;


        for(int i = 0; i < curAdapter.mThumbIds2.length; i++)
        {
            if(curAdapter.mThumbIds2[i].equals(mThumbIds2test[i]))
                solved = true;
            else
            {
                solved = false;
                break;
            }
        }
        return solved;
    }


    public void swap(int pos, ImageAdapter xAdapter)
    {



        int val = xAdapter.mThumbIds2[masterPos];
        xAdapter.mThumbIds2[masterPos] = xAdapter.mThumbIds2[pos];
        xAdapter.mThumbIds2[pos] = val;

        if(checkWin(xAdapter))
        {
            Toast.makeText(activity_5x5.this, "You won",
                    Toast.LENGTH_SHORT).show();

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();

            if(pref.getInt("scoreKey5", 0) > counter)
            {
                editor.putInt("scoreKey5", counter);
                editor.commit();
            }
        }
        xAdapter.notifyDataSetChanged();
        gridview.invalidateViews();
        masterPos = pos;



    }

    public void invalidMove()
    {
        Toast.makeText(activity_5x5.this, "Invalid Move",
                Toast.LENGTH_SHORT).show();
    }

    public void randomizeView(ImageAdapter xAdapter)
    {
        int val = xAdapter.mThumbIds2[masterPos];
        randomizeArray(xAdapter.mThumbIds2);





        for(int i = 0; i < xAdapter.mThumbIds2.length; i++)
        {
            if (xAdapter.mThumbIds2[i] == val)
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
