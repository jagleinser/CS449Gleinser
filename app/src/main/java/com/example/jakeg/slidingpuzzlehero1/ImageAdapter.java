package com.example.jakeg.slidingpuzzlehero1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;



    public ImageAdapter(Context c) {

        mContext = c;


    }

    public int getCount() {
        if (mContext.getClass() == activity_5x5.class)
        {
            return mThumbIds2.length;
        }
        else {
            return mThumbIds.length;
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }

        if (mContext.getClass() == activity_5x5.class)
        {
            imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
            imageView.setImageResource(mThumbIds2[position]);

            return imageView;
        }
        else {
            imageView.setImageResource(mThumbIds[position]);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            return imageView;
        }
    }

    // references to our images



    public Integer[] mThumbIds = {
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


    public Integer[] mThumbIds2 = {

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
}

