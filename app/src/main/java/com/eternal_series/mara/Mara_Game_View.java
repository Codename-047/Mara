package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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

        //initializing the drawing object.
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    private void update()
    {
        if(Arrow.isArrowShot)
            arrow.update();
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
            canvas.drawBitmap(mara.getMara(),mara.getMaraDirectionMatrix(),paint);

            //Drawing the arrow if fire button is clicked.
            if(Arrow.isArrowShot)
                canvas.drawBitmap(arrow.getArrow(),arrow.getArrowDirectionMatrix(), paint);

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

                //calculates the mara angle if the fire button is not clicked.
                if(!fireButton.isFireButtonClicked(motionEvent))
                    mara.computeMaraAngle(motionEvent);

                //When the fire button is touched.
                if(fireButton.isFireButtonClicked(motionEvent))
                {
                    //since the arrow is shot set it true and compute the angle.
                    Arrow.isArrowShot = true;
                    Arrow.arrowCount += 1;
                    arrow.computeArrowAngle();
                }
        }
        return false;
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