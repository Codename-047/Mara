package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Arrow
{
    //Bitmap to get the arrow.
    private Bitmap arrow;

    //coordinates.
    private float x;
    private float y;

    //speed of arrow.
    private float speed;

    //boolean to track if the arrow is shot or not.
    public boolean arrowIsShot;

    //Adding mara to this class to get his coordinates.
    Mara mara;

    //Arrow count.
    public int arrowCount = 0;

    //Arrow constructor.
    public Arrow(Context context)
    {
        //Getting the bitmap from the drawable resource.
        arrow = BitmapFactory.decodeResource(context.getResources(),R.drawable.arrow);

        //initializing the mara object.
        mara = new Mara(context);

        //setting the speed of the arrow.
        speed = 10;
    }


    public void update()
    {
        //Checks if the arrow is out of the screen bounds.
        if(y <= 0)
        {
            //resets the number of arrows on the screen to zero.
            arrowCount = 0;
        }

        //makes the arrow move forward.
        y = y - speed;
    }

    public void shoot()
    {

        //arrow is shot.
        arrowIsShot = true;

        //setting the variables.
        x = mara.getX() + mara.getMaraWidth() / 2;  // to make the arrow shoot from the center of image.
        y = mara.getY();

        //since the arrow is shot increase the count.
        arrowCount = arrowCount + 1;
    }

    //getters.
    public Bitmap getArrow()
    {
        return arrow;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public int getArrowCount()
    {
        return arrowCount;
    }
}
