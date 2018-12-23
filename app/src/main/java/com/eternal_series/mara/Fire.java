package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;

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

    //to check if the fire button is clicked.
    public boolean isFireButtonClicked(MotionEvent motionEvent)
    {
        return ((motionEvent.getX() >= getX() &&
                motionEvent.getX() <= getX() + getFireButtonWidth())
                //to wrap the whole button.
                && (motionEvent.getY() >= getY() &&
                motionEvent.getY() <= getY() + getFireButtonHeight()));
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
