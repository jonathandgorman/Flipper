package com.jonathangorman.flipper.screen;

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
import com.jonathangorman.flipper.utils.CardParser;

import java.util.ArrayList;

import static com.jonathangorman.flipper.utils.Constants.CARDS_PER_ROW;

public class CardScreenActivity extends Activity {

    private static final String TAG = "CardScreenActivity";
    public String languageChosen = "";
    public String categoryChosen = "";
    ArrayList<String> recyclerImagesList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

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
        CardChoiceAdapter adapter = new CardChoiceAdapter(recyclerImagesList,this);
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
        }
    }
}
