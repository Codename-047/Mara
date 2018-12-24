package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;

public class Knob
{
    //Bitmap to get Knob.
    private Bitmap knob;

    //coordinates.
    private float x;
    private float y;

    //to know if the knob is touched
    boolean isTouched = false;

    //
    static float normalizedX = 0;
    static float normalizedY = 0;

    //adding joystick to this class to get its height and width.
    Joystick joystick;

    public Knob(Context context)
    {
        //Getting the Bitmap from the drawable resource.
        knob = BitmapFactory.decodeResource(context.getResources(),R.drawable.knob);

        //initializing the joystick object.
        joystick = new Joystick(context);

        //setting the initial coordinates.
        x =  joystick.getCentreX() - knob.getWidth() / 2; // to centre the knob with joystick base
        y =  joystick.getCentreY() - knob.getHeight() / 2;
    }

    //to know if the knob is touched or not.
    public boolean isKnobTouched(MotionEvent motionEvent)
    {
        if((motionEvent.getX() >= getX() && motionEvent.getX() <= getX() + knob.getWidth())
                && (motionEvent.getY() >= getY() && motionEvent.getY() <= getY() + knob.getHeight()))
        {
            isTouched = true;
            return true;
        }

        return false;
    }

    int calculateAngle()
    {
        //to calculate the angle in which direction it was moved.
        int angle = (int)Math.toDegrees(Math.atan2(x - joystick.getCentreX(),
                y - joystick.getCentreY()));

        angle = angle - 90; // to shift the 0 degree to the first quadrant.

        //converts it into positive angles from 0 to 360 degrees.
        if(angle < 0)
            angle += 360;

        return angle;
    }

    //coordinates of the knob when it is touched
    public void touchedCoordinates(MotionEvent motionEvent)
    {
         x = motionEvent.getX();
         y = motionEvent.getY();

         //to calculate angle of touch.
        calculateAngle();

         //to bound the knob if it moves away from joystick base.
         boundingKnob(x,y);
    }

    //coordinates of the knob when it is moved called only when isTouched is set to true
    public void movedCoordinates (MotionEvent motionEvent)
    {
        x = motionEvent.getX();
        y = motionEvent.getY();

        calculateAngle();
        boundingKnob(x,y);
    }

    void boundingKnob(float x, float y)
    {
        //TODO document normalizedX and normalizedY.
        normalizedX = joystick.getWidth() / 2 * (float)(Math.cos(Math.toRadians(calculateAngle())));
        normalizedY = joystick.getHeight() / 2 * (float)(-1 * Math.sin(Math.toRadians(calculateAngle())));

        float a,b,c;
        a = joystick.getCentreX() - x;
        b = joystick.getCentreY() - y;

        c = (float)Math.sqrt(Math.pow(a,2) + Math.pow(b,2)); // pythagorean's theorem

        if(c >= joystick.getWidth() / 2) // checks if the hypotenuse is greater than radius
        {
            //if so; it is restricted by using normalized x and y variables.
            setX(joystick.getCentreX() + normalizedX);
            setY(joystick.getCentreY() + normalizedY);
        }

        //to draw the knob at the center of the finger.
        knobdrawX();
        knobdrawY();
    }

    void knobdrawX()
    {
        setX(x - knob.getWidth() / 2); // minus in order to center the knob w.r.t finger
    }

    void knobdrawY()
    {

        setY(y - knob.getHeight() / 2);
    }

    void released()
    {
        isTouched = false;
        x = joystick.getCentreX() - knob.getWidth() / 2;
        y = joystick.getCentreY() - knob.getHeight() / 2;
    }

    //getters.
    public Bitmap getKnob(){return knob;}

    public float getX(){return x;}

    public float getY(){return y;}

    public float getNormalizedX(){return normalizedX;}

    public float getNormalizedY(){return normalizedY;}

    //setters.
    public void setX(float coordinate){x = coordinate;}

    public void setY(float coordinate){y = coordinate;}
}
