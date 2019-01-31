package com.jonathangorman.lorlingo.primary;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.adapter.CategoryChoiceAdapter;

import java.util.ArrayList;
public class CategoryChoiceActivity extends Activity {

    private static final String TAG = "CategoryChoiceActivity";
    public String languageChosen = "";
    ArrayList<Integer> recyclerImagesList = new ArrayList<>();
    ArrayList<String> recyclerTextList = new ArrayList<>();
    ArrayList<String> recyclerNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ACTIVITY CREATE: Creating CategoryChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choice);

        // Get language choice from previous activity
        Intent intentLanguageChoice = getIntent();
        languageChosen = intentLanguageChoice.getStringExtra("LANGUAGE");
        Log.d(TAG, "Intent received from LanguageChoiceActivity: " + languageChosen);

        // Initialise lists once for the recyclerView
        initLists();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting categoryChoiceActivity");
        super.onStart();

        // Initialise and create recycler view and adapter to show the category choices
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        CategoryChoiceAdapter adapter = new CategoryChoiceAdapter(this, this.languageChosen, this.recyclerNameList, this.recyclerTextList, this.recyclerImagesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialises the lists required by the recyclerView
    void initLists()
    {
        // add category text names based on locale
        recyclerTextList.add(getString(R.string.fruit_category));
        recyclerTextList.add(getString(R.string.vegetables_category));
        recyclerTextList.add(getString(R.string.animals_category));
        recyclerTextList.add(getString(R.string.jobs_category));
        recyclerTextList.add(getString(R.string.transport_category));
        recyclerTextList.add(getString(R.string.weather_category));
        recyclerTextList.add(getString(R.string.household_category));

        // add values for the images and identifier
        recyclerImagesList.add(R.drawable.watermelon);
        recyclerNameList.add("fruit");
        recyclerImagesList.add(R.drawable.red_pepper);
        recyclerNameList.add("vegetables");
        recyclerImagesList.add(R.drawable.cat);
        recyclerNameList.add("animals");
        recyclerImagesList.add(R.drawable.firefighter);
        recyclerNameList.add("professions");
        recyclerImagesList.add(R.drawable.car);
        recyclerNameList.add("transport");
        recyclerImagesList.add(R.drawable.cloudy);
        recyclerNameList.add("weather");
        recyclerImagesList.add(R.drawable.couch);
        recyclerNameList.add("household");
    }
}
