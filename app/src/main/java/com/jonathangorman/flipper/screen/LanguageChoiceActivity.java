package com.jonathangorman.flipper.screen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jonathangorman.flipper.R;

public class LanguageChoiceActivity extends Activity {

    private static final String TAG = "LanguageChoiceActivity";
    private static final String SPANISH_ES = "spanish_es";
    private static final String ENGLISH_UK = "english_uk";
    protected String languageChoice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ACTIVITY CREATE: Creating LanguageChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "Starting LanguageChoiceActivity");
        super.onStart();

        //Allow the user to select a language, then changing to the category choice activity
        ImageView englishImageView = (ImageView) findViewById(R.id.languageImageButton1);
        ImageView spanishLanguageView = (ImageView) findViewById(R.id.languageImageButton2);

        // English language chosen
        englishImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Language chosen: " + languageChoice);
                languageChoice = ENGLISH_UK;
                Intent goToCategoryChoice = new Intent(getApplicationContext(), CategoryChoiceActivity.class);
                goToCategoryChoice.putExtra("LANGUAGE", languageChoice);
                startActivity(goToCategoryChoice);
            }
        });

        // Spanish language chosen
        spanishLanguageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Language chosen: " + languageChoice);
                languageChoice = SPANISH_ES;
                Intent goToCategoryChoice = new Intent(getApplicationContext(), CategoryChoiceActivity.class);
                goToCategoryChoice.putExtra("LANGUAGE", languageChoice);
                startActivity(goToCategoryChoice);
            }
        });
    }
}
