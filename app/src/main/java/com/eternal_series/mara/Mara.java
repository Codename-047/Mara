package com.eternal_series.mara;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;

public class Mara
{
    //coordinates.
    private static float x;
    private static float y;

    //speed of mara.
    private float speed;

    //Bitmap to get the character.
    private Bitmap mara;

    //matrix to rotate mara in the direction of touch.
    Matrix maraDirectionMatrix;
    static int maraRotatingAngle;

    //adding knob to get its movement direction
    Knob knob;

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

        //initially setting the variables to the centre of the screen.
        x = (screenWidth() - getMaraWidth()) / 2;
        y = (screenHeight() - getMaraHeight()) / 2;
        speed = 0.1f;

        //initializing the matrix object and setting its default position.
        maraDirectionMatrix = new Matrix();
        maraDirectionMatrix.postTranslate(getX(),getY());

        //initializing the knob object
        knob = new Knob(context);
    }

    //to calculate the direction of touch.
    public void computeMaraAngle(MotionEvent motionEvent)
    {
        //TODO Documentation: ANGLE OF MARA.
        //calculating the direction relative to mara.
         maraRotatingAngle =  - (int)(Math.toDegrees(Math.atan2(
                getX() + getMaraWidth() / 2 - motionEvent.getX()
                , getY() + getMaraHeight() / 2 - motionEvent.getY())));
         //minus to calculate the degrees in clock wise direction instead of anti-clock wise.

        if(maraRotatingAngle < 0)
        {
            maraRotatingAngle += 360;
        }

        maraDirectionMatrix.setRotate(maraRotatingAngle,getMaraWidth() / 2, getMaraHeight() / 2);
        maraDirectionMatrix.postTranslate(getX(),getY());
    }


    void update()
    {
        x = x + knob.getNormalizedX() / 4 * speed;
        y = y + knob.getNormalizedY() / 4 * speed;

        //to bound mara if it moves out of the screen
        boundingMara(x,y);

        maraDirectionMatrix.setRotate(maraRotatingAngle,getMaraWidth() / 2, getMaraHeight() / 2);
        maraDirectionMatrix.postTranslate(x, y);
    }

    private void boundingMara(float x, float y)
    {
        if(x <= - getMaraWidth() / 4)
            setX(-getMaraWidth() / 4);
        else if(x >= screenWidth() - 150)
            setX(screenWidth() - 150);

        if(y <= - getMaraHeight() / 4)
            setY(-getMaraHeight() / 4);
        else if(y >= screenHeight() - 175)
            setY(screenHeight() - 175);
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

    //setters.
    private void setX(float coordinatesX)
    {
        x = coordinatesX;
    }

    private void setY(float coordinatesY)
    {
        y = coordinatesY;
    }
}
