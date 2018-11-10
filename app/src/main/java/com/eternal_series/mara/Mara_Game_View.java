package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static java.lang.Thread.sleep;

public class Mara_Game_View extends SurfaceView implements Runnable
{
    //boolean variable to track if the game is playing or not.
    volatile boolean isPlaying;

    //the game thread.
    private Thread maraGameThread = null;

    //adding mara to this class.
    Mara mara;

    //adding the fire button to this class.
    Fire fireButton;

    //adding battlefield to this class.
    Battlefield battlefield;

    //adding arrow to this class.
    Arrow arrow;

    //These objects are used for drawing.
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

    //matrix to rotate mara in the direction of touch.
    Matrix maraFacingAngle;
    public int maraRotation = 0;

    //Class constructor.
    public Mara_Game_View(Context context)
    {
        super(context);

        //initializing the mara object.
        mara = new Mara(context);

        //initializing the battlefield object.
        battlefield = new Battlefield(context);

        //initializing the fire object.
        fireButton = new Fire(context);

        //initializing the arrow object.
        arrow = new Arrow(context);

        //initializing the matrix object and setting its default position.
        maraFacingAngle = new Matrix();
        maraFacingAngle.postTranslate(mara.getX(),mara.getY());


        //initializing the drawing object.
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    private void update()
    {
        //if the arrow is shot, updates its coordinates.
        if(arrow.arrowIsShot)
        {
            arrow.update();
        }

    }

    private void draw()
    {
        //checking if the surface is valid.
        if(surfaceHolder.getSurface().isValid())
        {
            //locking the canvas.
            canvas = surfaceHolder.lockCanvas();

            //Drawing the battlefield.
            canvas.drawBitmap(battlefield.getBattlefield(),0,0,paint);

            //Drawing the fire button.
            canvas.drawBitmap(fireButton.getFireButton(),fireButton.getX(),fireButton.getY(),paint);

            //Drawing the player.
            canvas.drawBitmap(mara.getMara(),maraFacingAngle,paint);

            //Drawing the arrow if fire button is clicked.
            if(arrow.arrowIsShot)
            {
                canvas.drawBitmap(arrow.getArrow(), arrow.getX(), arrow.getY(), paint);
            }

            //unlocking the canvas.
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control()
    {
        try
        {
            //the sleep method is called to maintain the frame rate at 60fps.
            sleep(17);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {

        while (isPlaying)
        {

            //to update the frame.
            update();

            //to draw the frame.
            draw();

            //to control.
            control();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        switch(motionEvent.getAction())
        {
            //When the screen is touched.
            case MotionEvent.ACTION_DOWN:

                //calculates the mara angle.
                computeAngle(motionEvent);

                //When the fire button is touched.
                if(isFireButtonClicked(motionEvent))
                {
                    //allows another arrow, if the number of arrows on the screen is less than one.
                    if(arrow.getArrowCount() < 1)
                    arrow.shoot();
                }
        }
        return false;
    }

    //to check if the fire button is clicked.
    public boolean isFireButtonClicked(MotionEvent motionEvent)
    {
        return ((motionEvent.getX() >= fireButton.getX() &&
                motionEvent.getX() <= fireButton.getX() + fireButton.getFireButtonWidth())
                //to wrap the whole button.
                && (motionEvent.getY() >= fireButton.getY() &&
                motionEvent.getY() <= fireButton.getY() + fireButton.getFireButtonHeight()));
    }

    //to calculate the direction of touch
    public void computeAngle(MotionEvent motionEvent)
    {
        //calculates the direction only if the fire button is not clicked.
        if(!isFireButtonClicked(motionEvent))
        {
            //calculating the direction relative to mara.
            maraRotation = - (int) (Math.toDegrees(Math.atan2(
                    mara.getX() + mara.getMaraWidth() / 2 - motionEvent.getX()
                    , mara.getY() + mara.getMaraHeight() / 2 - motionEvent.getY())));
            //minus to calculate the degrees in clock wise direction instead of anti-clock wise.

            //creating a matrix for angle of direction.
            Matrix angle = new Matrix();
            angle.setRotate(maraRotation, mara.getMaraWidth() / 2, mara.getMaraHeight() / 2);
            angle.postTranslate(mara.getX(), mara.getY());

            //rotating the mara matrix to a certain angle.
            maraFacingAngle.set(angle);
        }
    }

    public void pause()
    {
        //when the game is paused.
        //setting the variable to false.
        isPlaying = false;

        //stopping the thread.
        try
        {
            maraGameThread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void resume()
    {
        //when the game is resumed.
        //starting the game thread again.
        isPlaying = true;
        maraGameThread = new Thread(this);
        maraGameThread.start();
    }
}