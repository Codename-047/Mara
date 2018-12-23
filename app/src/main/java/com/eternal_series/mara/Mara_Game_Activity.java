package com.eternal_series.mara;

import android.app.Activity;
import android.os.Bundle;

public class Mara_Game_Activity extends Activity
{

    //declaring the maraGameView.
    private Mara_Game_View maraGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //initializing the maraGameView object.
        maraGameView = new Mara_Game_View(this);

        //adding it to the content view.
        setContentView(maraGameView);
    }

    //Pausing the game thread when back button is pressed
    @Override
    protected void onPause()
    {
        super.onPause();
        maraGameView.pause();
    }

    //Running the game thread when the activity is resumed.
    @Override
    protected void onResume()
    {
        super.onResume();
        maraGameView.resume();
    }
}
