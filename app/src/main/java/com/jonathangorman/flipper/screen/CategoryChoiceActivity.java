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
        if (languageChosen.equalsIgnoreCase("united_kingdom"))
        {
            recyclerTextList.add("Fruit");
            recyclerTextList.add("Vegetables");
            recyclerTextList.add("Animals");
            recyclerTextList.add("Professions");
            recyclerTextList.add("Transport");
            recyclerTextList.add("Fast food");
        }
        else if (languageChosen.equalsIgnoreCase("spain"))
        {
            recyclerTextList.add("Fruta");
            recyclerTextList.add("Verduras");
            recyclerTextList.add("Animales");
            recyclerTextList.add("Profesiones");
            recyclerTextList.add("Transporte");
            recyclerTextList.add("Comida rápida");
        }
        else if (languageChosen.equalsIgnoreCase("france"))
        {
            recyclerTextList.add("Fruits");
            recyclerTextList.add("Légumes");
            recyclerTextList.add("Animaux");
            recyclerTextList.add("Professions");
            recyclerTextList.add("Transport");
            recyclerTextList.add("Restauration Rapide");
        }
        else if (languageChosen.equalsIgnoreCase("germany"))
        {
            recyclerTextList.add("Obst");
            recyclerTextList.add("Gemüse");
            recyclerTextList.add("Tiere");
            recyclerTextList.add("Profesiones");
            recyclerTextList.add("Berufe");
            recyclerTextList.add("'Fast Food'");
        }
        else if (languageChosen.equalsIgnoreCase("italy"))
        {
            recyclerTextList.add("Frutta");
            recyclerTextList.add("Verdura");
            recyclerTextList.add("Animali");
            recyclerTextList.add("Professioni");
            recyclerTextList.add("Trasporti");
            recyclerTextList.add("'Fast Food'");
        }
        else if (languageChosen.equalsIgnoreCase("portugal"))
        {
            recyclerTextList.add("Fruit");
            recyclerTextList.add("Vegetais");
            recyclerTextList.add("Animals");
            recyclerTextList.add("Profissões");
            recyclerTextList.add("Transport");
            recyclerTextList.add("'Fast Food'");
        }
        // add values for the images and identifier
        recyclerImagesList.add(R.mipmap.watermelon);
        recyclerNameList.add("fruit");
        recyclerImagesList.add(R.mipmap.red_pepper_layer);
        recyclerNameList.add("vegetables");
        recyclerImagesList.add(R.mipmap.cat_layer);
        recyclerNameList.add("animales");
        recyclerImagesList.add(R.mipmap.firefighter_layer);
        recyclerNameList.add("professions");
        recyclerImagesList.add(R.mipmap.car_layer);
        recyclerNameList.add("transport");
        recyclerImagesList.add(R.mipmap.hamburger_layer);
        recyclerNameList.add("fastfood");
    }
}
