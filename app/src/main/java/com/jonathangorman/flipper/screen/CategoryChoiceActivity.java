package com.jonathangorman.flipper.screen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.utils.CardParser;
import com.jonathangorman.flipper.cards.CardList;

import java.util.ArrayList;
public class CategoryChoiceActivity extends Activity {

    private static final String TAG = "CategoryChoiceActivity";
    private static final String FRUIT = "fruit";
    protected String categoryChosen = "";
    protected String languageChosen = "";
    protected CardList cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ACTIVITY CREATE: Creating CategoryChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choice);

        // Get language choice from previous activity
        Intent intentLanguageChoice = getIntent();
        languageChosen = intentLanguageChoice.getStringExtra("LANGUAGE");
        Log.d(TAG, "Intent received from LanguageChoiceActivity: " + languageChosen);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting categoryChoiceActivity");
        super.onStart();

        ArrayList<String> imagesList = new ArrayList<String>();
        ArrayList<String> namesList = new ArrayList<String>();
        imagesList.add(String.valueOf(R.mipmap.apple));
         namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");
        imagesList.add(String.valueOf(R.mipmap.apple));
        namesList.add("Name 1");


        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        CategoryChoiceRecyclerViewAdapter adapter = new CategoryChoiceRecyclerViewAdapter(imagesList,namesList,this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        // Listen for category choice, create card list, and then change activity
        ImageView fruitCategoryButton = (ImageView) findViewById(R.id.categoryImageButton1);
        fruitCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryChosen = FRUIT;
                Log.d(TAG, "Category chosen: " + categoryChosen);

                // using the language and category choice, parse the config to return a card list corresponding to the choices
                cardList = populateCardList(languageChosen,categoryChosen);
                if (cardList == null)
                {
                    // In the case that the card list is null, the previous activity should be accessed
                    Log.e(TAG, "Error, The cardList is empty. Unable to continue to next activity.");
                    Toast.makeText(getApplicationContext(), "Oops, this looks bad. A fatal error has occurred.", Toast.LENGTH_LONG).show();
                    Intent returnToLanguageChoice = new Intent(getApplicationContext(), LanguageChoiceActivity.class);
                    startActivity(returnToLanguageChoice);
                }
                Log.d(TAG, "Card list has been retrieved, accessing next activity.");
                Intent goToCardScreen = new Intent(getApplicationContext(), CardScreenActivity.class);
                goToCardScreen.putExtra("CARDLIST",cardList);
                startActivity(goToCardScreen);
            }
        });*/
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
}
