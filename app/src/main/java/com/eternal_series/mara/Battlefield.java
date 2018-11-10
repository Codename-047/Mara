package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Battlefield
{
    //Bitmap to get the battlefield.
    private Bitmap battlefield;

    //battlefield constructor.
    public Battlefield(Context context)
    {
        //Getting the bitmap from the drawable resource.
        battlefield = BitmapFactory.decodeResource(context.getResources(),R.drawable.battlefield);

        //scaling the bitmap to the screen size.
        battlefield = Bitmap.createScaledBitmap(battlefield,Mara.screenWidth(),Mara.screenHeight(),
                false);
    }

    //getters.
    public Bitmap getBattlefield()
    {
        return battlefield;
    }

}
