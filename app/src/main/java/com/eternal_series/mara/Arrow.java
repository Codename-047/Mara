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

    //boolean to know if the arrow is shot.
    static boolean isArrowShot = false;

    //to limit the number of arrows that can be shot to 1.
    static int arrowCount = 0;

    //Adding mara to this class to get his coordinates.
    Mara mara;

    //matrix to rotate arrow in the direction of mara.
    Matrix arrowDirectionMatrix;

    //temporary variable so as not to change the angle when the update method is called.
    int arrowRotationAngle = 0;

    //to make the arrow move in the angle it was shot.
    float angleX;
    float angleY;

    //Arrow constructor.
    public Arrow(Context context)
    {
        //Getting the bitmap from the drawable resource.
        arrow = BitmapFactory.decodeResource(context.getResources(),R.drawable.arrow);

        //initializing the mara object.
        mara = new Mara(context);

        //setting the variables centre to mara.
        x = mara.getX();
        y = mara.getY();
        speed = 20;

        //initializing the arrow matrix and setting its default value.
        arrowDirectionMatrix = new Matrix();
        arrowDirectionMatrix.setTranslate(x,y);
    }

    public void update()
    {
        //to check if the arrow is out of the screen.
        if((x > Mara.screenWidth() + arrow.getWidth() || x <= 0)||(y <= 0 || y > Mara.screenHeight() + arrow.getHeight()) )
        {
            //if so, set the number of arrows on the screen to zero.
            arrowCount = 0;
            isArrowShot = false;
        }

        arrowDirectionMatrix.setRotate(arrowRotationAngle, mara.getMaraWidth() / 2, mara.getMaraHeight() / 2);
        arrowDirectionMatrix.postTranslate(x = x + angleX * speed,y = y + angleY * speed);
    }

    //to calculate the arrow angle.
    public void computeArrowAngle()
    {
        if(arrowCount == 1) {
            //setting the variable to the angle it was, at the time of clicking the fire button.
            arrowRotationAngle = Mara.maraRotatingAngle;

            //setting the rotation for matrix and its default values.
            arrowDirectionMatrix.setRotate(arrowRotationAngle, mara.getMaraWidth() / 2, mara.getMaraHeight() / 2);
            arrowDirectionMatrix.postTranslate(mara.getX(), mara.getY());

            //setting the default values of x and y.
            x = mara.getX();
            y = mara.getY();

            //TODO:Document the angle of arrow.
            angleX = (float) Math.sin(Math.toRadians(Mara.maraRotatingAngle));
            angleY = -(float) Math.cos(Math.toRadians(Mara.maraRotatingAngle));
            //minus because the y axis increases in the downward direction.
        }

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

    public Matrix getArrowDirectionMatrix()
    {
        return arrowDirectionMatrix;
    }
}
