package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

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

    //matrix to rotate arrow in the direction of mara.
    Matrix arrowDirectionMatrix;

    //Arrow constructor.
    public Arrow(Context context)
    {
        //Getting the bitmap from the drawable resource.
        arrow = BitmapFactory.decodeResource(context.getResources(),R.drawable.arrow);

        //initializing the mara object.
        mara = new Mara(context);

        //setting the variables.
        x = mara.getX();
        y = mara.getY();
        speed = 10;

        //initializing the arrow matrix and setting its default value.
        arrowDirectionMatrix = new Matrix();
        arrowDirectionMatrix.postTranslate(getX(),getY());
    }


    public void update()
    {
        //Checks if the arrow is out of the screen bounds.
        if((y <= 0 || y >= Mara.screenHeight()) || (x <= 0 || x >= Mara.screenWidth()))
        {
            //resets the number of arrows on the screen to zero.
            arrowCount = 0;
        }

        //TODO make the arrow move forward.
        y = y - speed;
    }

    public void shoot()
    {
        //arrow is shot.
        arrowIsShot = true;

        //since the arrow is shot increase the count.
        arrowCount = arrowCount + 1;
    }

    //to calculate the arrow angle.
    public void computeArrowAngle()
    {
        //matrix to calculate the angle.
        Matrix angle = new Matrix();

        //setting the rotation for matrix.
        angle.setRotate(Mara.maraRotatingAngle, mara.getMaraWidth() / 2, mara.getMaraHeight() / 2);
        angle.postTranslate(mara.getX(),mara.getY());

        //rotating the mara matrix to certain angle.
        arrowDirectionMatrix.set(angle);
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

    public Matrix getArrowDirectionMatrix()
    {
        return arrowDirectionMatrix;
    }
}
