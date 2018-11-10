package com.eternal_series.mara;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Mara
{
    //coordinates.
    private float x;
    private float y;

    //speed of mara.
    private float speed;

    //Bitmap to get the character.
    private Bitmap mara;

    //screen width.
    public static int screenWidth()
    {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    //screen height.
    public static int screenHeight()
    {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    //Mara constructor.
    public Mara(Context context)
    {
        //getting the bitmap from the drawable resources.
        mara = BitmapFactory.decodeResource(context.getResources(),R.drawable.hero);

        //setting the variables.
        x = screenWidth() / 2;
        y = screenHeight() / 2;
        speed = 1;
    }

    //getters.
    public Bitmap getMara()
    {
        return mara;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getMaraWidth()
    {
        return mara.getWidth();
    }

    public float getMaraHeight()
    {
        return mara.getHeight();
    }
}
