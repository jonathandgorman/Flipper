package com.jonathangorman.flipper.screen;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jonathangorman.flipper.R;

public class CardScreenActivity extends AppCompatActivity {

    private static final String TAG = "CardScreenActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
    }

    protected void onStart() {
        super.onStart();

        // Set the button images according to the selected category
        ImageButton img1 = (ImageButton) findViewById(R.id.languageImageButton1);
        img1.setImageResource(R.mipmap.apple);
        ImageButton img2 = (ImageButton) findViewById(R.id.languageImageButton2);
        img2.setImageResource(R.mipmap.apricot);
        ImageButton img3 = (ImageButton) findViewById(R.id.imageButton3);
        img3.setImageResource(R.mipmap.banana);
        ImageButton img4 = (ImageButton) findViewById(R.id.imageButton4);
        img4.setImageResource(R.mipmap.blueberry);
        ImageButton img5 = (ImageButton) findViewById(R.id.imageButton5);
        img5.setImageResource(R.mipmap.cherry);
        ImageButton img6 = (ImageButton) findViewById(R.id.imageButton6);
        img6.setImageResource(R.mipmap.coconut);
        ImageButton img7 = (ImageButton) findViewById(R.id.imageButton7);
        img7.setImageResource(R.mipmap.grapes);
        ImageButton img8 = (ImageButton) findViewById(R.id.imageButton8);
        img8.setImageResource(R.mipmap.kiwi);
        ImageButton img9 = (ImageButton) findViewById(R.id.imageButton9);
        img9.setImageResource(R.mipmap.lemon);
        ImageButton img10 = (ImageButton) findViewById(R.id.imageButton10);
        img10.setImageResource(R.mipmap.orange);
        ImageButton img11 = (ImageButton) findViewById(R.id.imageButton11);
        img11.setImageResource(R.mipmap.pineapple);
        ImageButton img12 = (ImageButton) findViewById(R.id.imageButton12);
        img12.setImageResource(R.mipmap.watermelon);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                mp.start();
            }
        });
    }
}
