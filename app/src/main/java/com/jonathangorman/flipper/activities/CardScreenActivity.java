package com.jonathangorman.flipper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.adapters.CardChoiceAdapter;
import com.jonathangorman.flipper.cards.Card;
import com.jonathangorman.flipper.cards.CardList;
import com.jonathangorman.flipper.cards.CardParser;
import java.util.ArrayList;
import java.util.Locale;

public class CardScreenActivity extends Activity {

    private static final String TAG = "CardScreenActivity";
    public String languageChosen = "";
    public String categoryChosen = "";
    ArrayList<String> recyclerImagesList = new ArrayList<String>();
    ArrayList<String> recyclerAudioList = new ArrayList<String>();
    private final static int CARDS_PER_ROW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_screen);

        Intent intentLanguageChoice = getIntent();
        languageChosen = intentLanguageChoice.getStringExtra("LANGUAGE");
        categoryChosen = intentLanguageChoice.getStringExtra("CATEGORY");
        Log.d(TAG, "Intent received from categoryChoiceActivity: " + languageChosen + " and " + categoryChosen);

        // Initialise lists once for the recyclerView
        initLists(languageChosen, categoryChosen);
    }

    protected void onStart() {
        super.onStart();

        // Initialise and create recycler view and adapter to show the category choices
        RecyclerView recyclerView = findViewById(R.id.recycler_view_card_screen);
        CardChoiceAdapter adapter = new CardChoiceAdapter(this, recyclerImagesList, recyclerAudioList);

        // set locale according to language
        switch (this.languageChosen)
        {
            case ("united_kingdom"):
                adapter.setCurrLocale(new Locale("en_UK"));
                break;
            case ("spain"):
                adapter.setCurrLocale(new Locale("es_ES"));
                break;
            case ("france"):
                adapter.setCurrLocale(new Locale("fr_FR"));
                break;
            case ("germany"):
                adapter.setCurrLocale(new Locale("de_DE"));
                break;
            case ("italy"):
                adapter.setCurrLocale(new Locale("it_IT"));
                break;
            case ("portugal"):
                adapter.setCurrLocale(new Locale("pt_PT"));
                break;
            default:
                adapter.setCurrLocale(Locale.getDefault());
        }
        Log.d(TAG, "Locale set: " + adapter.getCurrLocale());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, CARDS_PER_ROW));
    }

    // Initialises the lists required by the recyclerView
    void initLists(String languageChosen, String categoryChosen)
    {
        CardParser parser = new CardParser();
        CardList cardList;
        parser.setContext(this);
        parser.setParserLang(languageChosen);
        parser.setParserCategory(categoryChosen);
        parser.start();
        cardList = parser.getCardList();

        Card currCard;
        for (int i = 0; i < cardList.size(); i++) {
            currCard = (Card) cardList.get(i);
            recyclerImagesList.add(String.valueOf(this.getResources().getIdentifier(currCard.getImageString(), "drawable", this.getPackageName())));
            recyclerAudioList.add(currCard.getAudio());
        }
    }
}
