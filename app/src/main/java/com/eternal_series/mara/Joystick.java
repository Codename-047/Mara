package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Joystick
{
    //Bitmap to get the joystick.
    private Bitmap joystick;

    //coordinates.
    private float x;
    private float y;

    //Joystick constructor
    public Joystick(Context context)
    {
        //Getting the Bitmap from the drawable resource.
        joystick = BitmapFactory.decodeResource(context.getResources(),R.drawable.joystick);
        x = 100;
        y = Mara.screenHeight() - 425;
    }

    //getters.
    public Bitmap getJoystick(){return joystick;}

    public float getX(){return x;}

    public float getY(){return y;}

    public float getWidth()
    {
        return joystick.getWidth();
    }

    public float getHeight()
    {
        return joystick.getHeight();
    }


    public float getCentreX()
    {
        float centreX = getX() + getWidth() / 2;
        return centreX;
    }

    public float getCentreY()
    {

        float centreY = getY() + getHeight() / 2;
        return centreY;
    }
}
