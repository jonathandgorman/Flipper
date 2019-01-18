package com.jonathangorman.flipper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.tts.TTSManager;
import com.jonathangorman.flipper.adapters.LanguageChoiceAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageChoiceActivity extends Activity {

    private static final String TAG = "LanguageChoiceActivity";
    private static final int TTS_ENGINE_CHECK_CODE = 0;


    ArrayList<Integer> languagesImagesList = new ArrayList<Integer>();
    ArrayList<String> languageDisplayTextList = new ArrayList<String>();
    ArrayList<String> languageNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ACTIVITY CREATE: Creating LanguageChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);
        initLanguageLists(); // initialise view lists
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() LanguageChoiceActivity");
        super.onStart();

        // Check the TTS settings of the device
        checkTTSEngineInstalled();
        TTSManager ttsManager = new TTSManager(this);

        for(Locale curr:ttsManager.getAvailableLocaleList())
        {
            Log.i(TAG,curr.getDisplayName());
        }


        // Initialise and create recycler view and adapter to show the language choices
        RecyclerView recyclerView = findViewById(R.id.language_recycle_view);
        LanguageChoiceAdapter adapter = new LanguageChoiceAdapter(this, this.languagesImagesList, this.languageDisplayTextList, this.languageNameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialise lists of data for the view
    private void initLanguageLists() {
        languagesImagesList.add(R.drawable.united_kingdom);
        languageDisplayTextList.add("ENGLISH");
        languageNameList.add("united_kingdom");

        languagesImagesList.add(R.drawable.spain);
        languageDisplayTextList.add("ESPAÑOL");
        languageNameList.add("spain");

        languagesImagesList.add(R.drawable.france);
        languageDisplayTextList.add("FRANÇAIS");
        languageNameList.add("france");

        languagesImagesList.add(R.drawable.germany);
        languageDisplayTextList.add("DEUTSCH");
        languageNameList.add("germany");

        languagesImagesList.add(R.drawable.italy);
        languageDisplayTextList.add("ITALIANO");
        languageNameList.add("italy");

        languagesImagesList.add(R.drawable.portugal);
        languageDisplayTextList.add("PORTUGUÊS");
        languageNameList.add("portugal");
    }

    void goToSettingsActivity(View view) {
        Intent toSettingsIntent = new Intent(this, SettingsActivity.class);
        this.startActivity(toSettingsIntent);
        Log.d(TAG, "Intent to Settings activity");
    }

    // Check that a TTS engine is available
    public boolean checkTTSEngineInstalled() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, TTS_ENGINE_CHECK_CODE);

        return true;
    }

    // Handles on activity result codes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TTS_ENGINE_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Toast.makeText(getApplicationContext(), "TTS engine available and ready.", Toast.LENGTH_LONG).show();
            } else {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                Toast.makeText(getApplicationContext(), "TTS engine has been installed and is now ready",Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == TTS_ENGINE_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Toast.makeText(getApplicationContext(), "TTS engine available and ready.", Toast.LENGTH_LONG).show();
            } else {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                Toast.makeText(getApplicationContext(), "TTS engine has been installed and is now ready",Toast.LENGTH_LONG).show();
            }
        }
    }
}