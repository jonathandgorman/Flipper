package com.jonathangorman.flipper.screen;

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
        LanguageChoiceAdapter adapter = new LanguageChoiceAdapter(this, this.languagesImagesList, this.languageNameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialise lists of data for the view
    private void initViewLists()
    {
        languagesImagesList.add(R.mipmap.united_kingdom_layer);
        languageNameList.add("ENGLISH");
        languagesImagesList.add(R.mipmap.spain_layer);
        languageNameList.add("ESPAÑOL");
        languagesImagesList.add(R.mipmap.france_layer);
        languageNameList.add("FRANÇAIS");
        languagesImagesList.add(R.mipmap.germany_layer);
        languageNameList.add("DEUTSCH");
        languagesImagesList.add(R.mipmap.italy_layer);
        languageNameList.add("ITALIANO");
        languagesImagesList.add(R.mipmap.portugal_layer);
        languageNameList.add("PORTUGUÊS");
    }
}
