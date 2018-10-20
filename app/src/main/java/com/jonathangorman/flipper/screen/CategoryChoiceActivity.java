package com.jonathangorman.flipper.screen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.adapters.CategoryChoiceAdapter;

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
        if (languageChosen.equalsIgnoreCase("united_kingdom"))
        {
            recyclerTextList.add("Fruit");
            recyclerTextList.add("Vegetables");
            recyclerTextList.add("Animals");
            recyclerTextList.add("Professions");
            recyclerTextList.add("Transport");
            recyclerTextList.add("Weather");
            recyclerTextList.add("Furniture");
        }
        else if (languageChosen.equalsIgnoreCase("spain"))
        {
            recyclerTextList.add("Fruta");
            recyclerTextList.add("Verduras");
            recyclerTextList.add("Animales");
            recyclerTextList.add("Profesiones");
            recyclerTextList.add("Transporte");
            recyclerTextList.add("Tiempo");
            recyclerTextList.add("Muebles");
        }
        else if (languageChosen.equalsIgnoreCase("france"))
        {
            recyclerTextList.add("Fruits");
            recyclerTextList.add("Légumes");
            recyclerTextList.add("Animaux");
            recyclerTextList.add("Professions");
            recyclerTextList.add("Transport");
            recyclerTextList.add("Météo");
            recyclerTextList.add("Meubles");
        }
        else if (languageChosen.equalsIgnoreCase("germany"))
        {
            recyclerTextList.add("Obst");
            recyclerTextList.add("Gemüse");
            recyclerTextList.add("Tiere");
            recyclerTextList.add("Profesiones");
            recyclerTextList.add("Berufe");
            recyclerTextList.add("Wetter");
            recyclerTextList.add("Möbel");
        }
        else if (languageChosen.equalsIgnoreCase("italy"))
        {
            recyclerTextList.add("Frutta");
            recyclerTextList.add("Verdura");
            recyclerTextList.add("Animali");
            recyclerTextList.add("Professioni");
            recyclerTextList.add("Trasporti");
            recyclerTextList.add("Tempo");
            recyclerTextList.add("Mobilia");
        }
        else if (languageChosen.equalsIgnoreCase("portugal"))
        {
            recyclerTextList.add("Fruit");
            recyclerTextList.add("Vegetais");
            recyclerTextList.add("Animals");
            recyclerTextList.add("Profissões");
            recyclerTextList.add("Transport");
            recyclerTextList.add("Tempo");
            recyclerTextList.add("Mobília");
        }
        // add values for the images and identifier
        recyclerImagesList.add(R.mipmap.watermelon);
        recyclerNameList.add("fruit");
        recyclerImagesList.add(R.mipmap.red_pepper);
        recyclerNameList.add("vegetables");
        recyclerImagesList.add(R.mipmap.cat);
        recyclerNameList.add("animals");
        recyclerImagesList.add(R.mipmap.firefighter);
        recyclerNameList.add("professions");
        recyclerImagesList.add(R.mipmap.car);
        recyclerNameList.add("transport");
        recyclerImagesList.add(R.mipmap.cloudy);
        recyclerNameList.add("weather");
        recyclerImagesList.add(R.mipmap.couch);
        recyclerNameList.add("furniture");
    }
}
