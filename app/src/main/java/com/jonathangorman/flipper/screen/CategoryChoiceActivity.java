package com.jonathangorman.flipper.screen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.adapters.CategoryChoiceAdapter;
import com.jonathangorman.flipper.utils.CardParser;
import com.jonathangorman.flipper.cards.CardList;

import java.util.ArrayList;
public class CategoryChoiceActivity extends Activity {

    private static final String TAG = "CategoryChoiceActivity";
    private static final String FRUIT = "fruit";
    protected String categoryChosen = "";
    protected String languageChosen = "";
    protected CardList cardList;
    ArrayList<String> recyclerImagesList = new ArrayList<String>();
    ArrayList<String> recyclerNamesList = new ArrayList<String>();

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
        CategoryChoiceAdapter adapter = new CategoryChoiceAdapter(recyclerImagesList, recyclerNamesList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Creates a card parser and returns a car list
    protected CardList populateCardList(String lang, String cat)
    {
        CardParser parser = new CardParser();
        parser.setParserCategory(cat);
        parser.setParserLang(lang);
        parser.start();
        return parser.getCardList();
    }

    // Initialises the lists required by the recyclerView
    void initLists()
    {
        if (languageChosen.equals("english_uk"))
        {
            recyclerNamesList.add("Fruit");
            recyclerNamesList.add("Vegetables");
            recyclerNamesList.add("Animals");
            recyclerNamesList.add("Professions");
            recyclerNamesList.add("Transport");
            recyclerNamesList.add("Fast food");
        }
        else if (languageChosen.equals("spanish_es"))
        {
            recyclerNamesList.add("Fruta");
            recyclerNamesList.add("Verduras");
            recyclerNamesList.add("Animales");
            recyclerNamesList.add("Profesiones");
            recyclerNamesList.add("Transporte");
            recyclerNamesList.add("Comida rápida");

        }
        recyclerImagesList.add(String.valueOf(R.mipmap.watermelon_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.red_pepper_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.cat_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.firefighter_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.car_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.hamburger_layer));
    }
}
