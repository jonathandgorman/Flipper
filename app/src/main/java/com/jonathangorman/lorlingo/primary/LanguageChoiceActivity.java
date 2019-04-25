package com.jonathangorman.lorlingo.primary;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.adapter.LanguageChoiceAdapter;
import com.jonathangorman.lorlingo.com.jonathangorman.lorlingo.domain.LanguageItem;
import com.jonathangorman.lorlingo.tts.TTSManager;

import java.util.ArrayList;

public class LanguageChoiceActivity extends BaseActivity  {

    private static final String TAG = LanguageChoiceActivity.class.getName();
    private static final int TTS_ENGINE_CHECK_CODE = 0;
    private static boolean TTS_AVAILABLE = false; // TTS engine available

    TTSManager ttsManager;
    LanguageChoiceAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<LanguageItem> languageItemList = new ArrayList<LanguageItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "ACTIVITY CREATE: Creating LanguageChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);
        ttsManager = new TTSManager(this);

        // initialise view lists
        initLanguageLists();
    }

    @Override
    protected void onStart() {

        Log.i(TAG, "Started LanguageChoiceActivity");
        super.onStart();

        //TODO check what languages are available, and disable those that are not

        // Initialise and create recycler view and adapter to show the language choices
        recyclerView = findViewById(R.id.language_recycler_view);
        adapter = new LanguageChoiceAdapter(this, this.languageItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialise lists of data for the view
    private void initLanguageLists() {

        Log.i(TAG, "Initialising language lists...");
        languageItemList.clear();

        //TODO external configuration possible here
        LanguageItem li1 = new LanguageItem();
        li1.setImageId(R.drawable.united_kingdom);
        li1.setDisplayText(getString(R.string.english_language));
        li1.setNameId("united_kingdom");
        li1.setTtsAvailable(true);
        languageItemList.add(li1);

        LanguageItem li2 = new LanguageItem();
        li2.setImageId(R.drawable.spain);
        li2.setDisplayText(getString(R.string.spanish_language));
        li2.setNameId("spain");
        li2.setTtsAvailable(true);
        languageItemList.add(li2);

        LanguageItem li3 = new LanguageItem();
        li3.setImageId(R.drawable.france);
        li3.setDisplayText(getString(R.string.french_language));
        li3.setNameId("france");
        li3.setTtsAvailable(true);
        languageItemList.add(li3);

        LanguageItem li4 = new LanguageItem();
        li4.setImageId(R.drawable.germany);
        li4.setDisplayText(getString(R.string.german_language));
        li4.setNameId("germany");
        li4.setTtsAvailable(true);
        languageItemList.add(li4);

        LanguageItem li5 = new LanguageItem();
        li5.setImageId(R.drawable.italy);
        li5.setDisplayText(getString(R.string.italian_language));
        li5.setNameId("italy");
        li5.setTtsAvailable(false);
        languageItemList.add(li5);

        LanguageItem li6 = new LanguageItem();
        li6.setImageId(R.drawable.portugal);
        li6.setDisplayText(getString(R.string.portuguese_language));
        li6.setNameId("portugal");
        li6.setTtsAvailable(true);
        languageItemList.add(li6);

        Log.i(TAG, "Initialisation finished");
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
            if (resultCode != TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Log.i(TAG, "onActivityResult: TTS engine installed and available");
                this.TTS_AVAILABLE = true;

            } else {
                Log.i(TAG, "onActivityResult: No TTS engine available. Prompting to install.");
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                Toast.makeText(getApplicationContext(), getString(R.string.tts_voice_data_prompt),Toast.LENGTH_LONG).show();
                this.TTS_AVAILABLE = true;
            }
        }
        initLanguageLists(); // reinitialise the language lists, possibly after installing a new TTS engine
    }
}