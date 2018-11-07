package com.eternal_series.mara;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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

    //adding battlefield to this class.
    Battlefield battlefield;

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

        //initializing the drawing object.
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    private void update()
    {

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

            //Drawing the player.
            canvas.drawBitmap(mara.getMara(),mara.getX(),mara.getY(),paint);

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

        while(isPlaying)
        {

            //to update the frame.
            update();

            //to draw the frame.
            draw();

            //to control.
            control();
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