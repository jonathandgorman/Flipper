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

    private static final String TAG = LanguageChoiceActivity.class.getName();
    private static final int TTS_ENGINE_CHECK_CODE = 0;

    TTSManager ttsManager;
    private static boolean TTS_AVAILABLE = false; // TTS engine available

    // TODO combine into single object
    ArrayList<Integer> languagesImagesList = new ArrayList<Integer>();
    ArrayList<String> languageDisplayTextList = new ArrayList<String>();
    ArrayList<String> languageNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ACTIVITY CREATE: Creating LanguageChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);

        // initialise view lists
        initLanguageLists(); //TODO init lists based on language of the device

        ttsManager = new TTSManager(this);

        // Check that a TTS engine is installed on the device
        checkTTSEngineInstalled();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "Started LanguageChoiceActivity");
        super.onStart();

        //TODO check what languages are available, and disable those that are not

        // Initialise and create recycler view and adapter to show the language choices
        RecyclerView recyclerView = findViewById(R.id.language_recycle_view);
        LanguageChoiceAdapter adapter = new LanguageChoiceAdapter(this, this.languagesImagesList, this.languageDisplayTextList, this.languageNameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialise lists of data for the view
    private void initLanguageLists() {

        //TODO external configuration possible here
        languagesImagesList.add(R.drawable.united_kingdom);
        languageDisplayTextList.add(getString(R.string.english_language));
        languageNameList.add("united_kingdom");

        languagesImagesList.add(R.drawable.spain);
        languageDisplayTextList.add(getString(R.string.spanish_language));
        languageNameList.add("spain");

        languagesImagesList.add(R.drawable.france);
        languageDisplayTextList.add(getString(R.string.french_language));
        languageNameList.add("france");

        languagesImagesList.add(R.drawable.germany);
        languageDisplayTextList.add(getString(R.string.german_language));
        languageNameList.add("germany");

        languagesImagesList.add(R.drawable.italy);
        languageDisplayTextList.add(getString(R.string.italian_language));
        languageNameList.add("italy");

        languagesImagesList.add(R.drawable.portugal);
        languageDisplayTextList.add(getString(R.string.portuguese_language));
        languageNameList.add("portugal");
    }

    // Go to the settings activity
    void goToSettingsActivity(View view) {
        Intent toSettingsIntent = new Intent(this, SettingsActivity.class);
        this.startActivity(toSettingsIntent);
        Log.d(TAG, "Intent to Settings activity");
    }

    // Check that a TTS engine is available
    public void checkTTSEngineInstalled() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, TTS_ENGINE_CHECK_CODE);
    }

    // Handles on activity result codes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check for TTS engine
        if (requestCode == TTS_ENGINE_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Log.i(TAG, "onActivityResult: TTS engine installed and available");
                this.TTS_AVAILABLE = true;

            } else {
                //TODO prompt for TTS install, prior to install
                //TODO ensure that internet connection available for downlaod of TTS engine
                Log.i(TAG, "onActivityResult: No TTS engine available. Prompting to install.");
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                Toast.makeText(getApplicationContext(), "TTS engine has been installed and is now ready",Toast.LENGTH_LONG).show();
                this.TTS_AVAILABLE = true;
            }
        }
    }
}