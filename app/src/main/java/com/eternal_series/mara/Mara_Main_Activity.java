package com.eternal_series.mara;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Mara_Main_Activity extends Activity implements View.OnClickListener
{

    //Image Buttons.
    private ImageButton playNow;
    private ImageButton highScore;
    private ImageButton settings;
    private ImageButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mara_main_layout);

        //getting the buttons.
        playNow = findViewById(R.id.play_now);
        highScore = findViewById(R.id.high_score);
        settings = findViewById(R.id.settings);
        exit = findViewById(R.id.exit);

        //adding click listeners to the ImageButtons.
        playNow.setOnClickListener(this);
        highScore.setOnClickListener(this);
        settings.setOnClickListener(this);
        exit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        //play now button logic.
        if(view.getId() == R.id.play_now)
        {
            //starting the Mara_Game_Activity.
            startActivity(new Intent(this,Mara_Game_Activity.class));
        }

        //high score button logic.
        else if(view.getId() == R.id.high_score)
        {
        }

        //settings button logic.
        else if(view.getId() == R.id.settings)
        {
        }

        //exit button logic.
        else if(view.getId() == R.id.exit)
        {
            //closes the app.
            this.finishAffinity();
        }
    }
}
