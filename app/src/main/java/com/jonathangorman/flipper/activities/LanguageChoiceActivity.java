package com.jonathangorman.flipper.activities;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.adapters.LanguageChoiceAdapter;

import java.util.ArrayList;

public class LanguageChoiceActivity extends Activity {

    private static final String TAG = "LanguageChoiceActivity";

    ArrayList<Integer> languagesImagesList = new ArrayList<Integer>();
    ArrayList<String> languageDisplayTextList = new ArrayList<String>();
    ArrayList<String> languageNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ACTIVITY CREATE: Creating LanguageChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);
        initViewLists(); // initialise view lists
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "Starting LanguageChoiceActivity");
        super.onStart();

        // Initialise and create recycler view and adapter to show the language choices
        RecyclerView recyclerView = findViewById(R.id.language_recycle_view);
        LanguageChoiceAdapter adapter = new LanguageChoiceAdapter(this, this.languagesImagesList, this.languageDisplayTextList, this.languageNameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialise lists of data for the view
    private void initViewLists()
    {
        languagesImagesList.add(R.mipmap.united_kingdom);
        languageDisplayTextList.add("ENGLISH");
        languageNameList.add("united_kingdom");

        languagesImagesList.add(R.mipmap.spain);
        languageDisplayTextList.add("ESPAÑOL");
        languageNameList.add("spain");

        languagesImagesList.add(R.mipmap.france);
        languageDisplayTextList.add("FRANÇAIS");
        languageNameList.add("france");

        languagesImagesList.add(R.mipmap.germany);
        languageDisplayTextList.add("DEUTSCH");
        languageNameList.add("germany");

        languagesImagesList.add(R.mipmap.italy);
        languageDisplayTextList.add("ITALIANO");
        languageNameList.add("italy");

        languagesImagesList.add(R.mipmap.portugal);
        languageDisplayTextList.add("PORTUGUÊS");
        languageNameList.add("portugal");
    }
}
