package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Fire
{
    //Bitmap to get the button.
    private Bitmap fireButton;

    //coordinates.
    private float x;
    private float y;

    //Fire constructor.
    public Fire(Context context)
    {
        //Getting the bitmap from the drawable resource.
        fireButton = BitmapFactory.decodeResource(context.getResources(),R.drawable.fire);
        x = Mara.screenWidth() - 450;
        y = Mara.screenHeight() - 400;
    }

    //getters.
    public Bitmap getFireButton()
    {
        return fireButton;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getFireButtonWidth()
    {
        return fireButton.getWidth();
    }

    public float getFireButtonHeight()
    {
        return fireButton.getHeight();
    }
}
