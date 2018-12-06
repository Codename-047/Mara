package com.eternal_series.mara;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.MotionEvent;

public class Mara
{
    //coordinates.
    private float x;
    private float y;

    //speed of mara.
    private float speed;

    //Bitmap to get the character.
    private Bitmap mara;

    //matrix to rotate mara in the direction of touch.
    Matrix maraDirectionMatrix;
    static int maraRotatingAngle;

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
        x = (screenWidth() - getMaraWidth()) / 2;
        y = (screenHeight() - getMaraHeight()) / 2;
        speed = 1;

        //initializing the matrix object and setting its default position.
        maraDirectionMatrix = new Matrix();
        maraDirectionMatrix.postTranslate(getX(),getY());
    }

    //to calculate the direction of touch.
    public void computeMaraAngle(MotionEvent motionEvent)
    {
        //matrix to calculate the angle.
        Matrix angle = new Matrix();

        //calculating the direction relative to mara.
         maraRotatingAngle = - (int) (Math.toDegrees(Math.atan2(
                getX() + getMaraWidth() / 2 - motionEvent.getX()
                , getY() + getMaraHeight() / 2 - motionEvent.getY())));
        //minus to calculate the degrees in clock wise direction instead of anti-clock wise.

        //setting the rotation for matrix.
        angle.setRotate(maraRotatingAngle,getMaraWidth() / 2, getMaraHeight() / 2);
        angle.postTranslate(getX(),getY());

        //rotating the mara matrix to certain angle.
        maraDirectionMatrix.set(angle);

        //test
        //Log.i("x direction",Double.toString(Math.sin(Math.toRadians(maraRotatingAngle))));
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

    public Matrix getMaraDirectionMatrix()
    {
        return maraDirectionMatrix;
    }
}
