package com.jonathangorman.flipper.screen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.utils.CardParser;
import com.jonathangorman.flipper.cards.CardList;

import static com.jonathangorman.flipper.utils.Constants.LOG;

public class CategoryChoiceActivity extends Activity {

    private static final String FRUIT = "fruit";
    protected String categoryChosen = "";
    protected String languageChosen = "";
    protected CardList cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG, "ACTIVITY CREATE: Creating CategoryChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choice);

        // Get language choice from previous activity
        Intent intentLanguageChoice = getIntent();
        languageChosen = intentLanguageChoice.getStringExtra("LANGUAGE");
        Log.d(LOG, "Intent received from LanguageChoiceActivity: " + languageChosen);
    }

    @Override
    protected void onStart() {
        Log.d(LOG, "Starting categoryChoiceActivity");
        super.onStart();

        // Listen for category choice, create card list, and then change activity
        ImageView fruitCategoryButton = (ImageView) findViewById(R.id.categoryImageButton1);
        fruitCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryChosen = FRUIT;
                Log.d(LOG, "Category chosen: " + categoryChosen);

                // using the language and category choice, parse the config to return a card list corresponding to the choices
                cardList = populateCardList(languageChosen,categoryChosen);
                if (cardList == null)
                {
                    // In the case that the card list is null, the previous activity should be accessed
                    Log.e(LOG, "Error, The cardList is empty. Unable to continue to next activity.");
                    Toast.makeText(getApplicationContext(), "Oops, this looks bad. A fatal error has occurred.", Toast.LENGTH_LONG).show();
                    Intent returnToLanguageChoice = new Intent(getApplicationContext(), LanguageChoiceActivity.class);
                    startActivity(returnToLanguageChoice);
                }
                Log.d(LOG, "Card list has been retrieved, accessing next activity.");
                Intent goToCardScreen = new Intent(getApplicationContext(), CardScreenActivity.class);
                goToCardScreen.putExtra("CARDLIST",cardList);
                startActivity(goToCardScreen);
            }
        });
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
