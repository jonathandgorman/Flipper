package com.jonathangorman.lorlingo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.adapter.CardChoiceAdapter;
import com.jonathangorman.lorlingo.domain.CardItem;
import com.jonathangorman.lorlingo.domain.CardParser;

import java.util.ArrayList;
import java.util.Locale;

public class CardScreenActivity extends BaseActivity {

    private static final String TAG = "CardScreenActivity";
    private static final int CARDS_PER_ROW = 3;

    private String languageChosen = "";
    private String categoryChosen = "";

    private ArrayList<CardItem> cardItemList = new ArrayList<CardItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_screen);

        // receive intent containing language and category choice
        Intent intentLanguageChoice = getIntent();
        languageChosen = intentLanguageChoice.getStringExtra("LANGUAGE");
        categoryChosen = intentLanguageChoice.getStringExtra("CATEGORY");
        //Log.d(TAG, "Intent received from categoryChoiceActivity: " + languageChosen + " and " + categoryChosen);

        // Initialise lists once for the recyclerView
        initLists();
    }

    protected void onStart() {
        super.onStart();

        // Initialise and create recycler view and adapter to show the category choices
        RecyclerView recyclerView = findViewById(R.id.recycler_view_card_screen);
        CardChoiceAdapter adapter = new CardChoiceAdapter(this, cardItemList);

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
        // Log.d(TAG, "Locale set: " + adapter.getCurrLocale());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, CARDS_PER_ROW));
    }

    // Initialises the lists required by the recyclerView
    void initLists()
    {
        CardParser parser = new CardParser(this, this.languageChosen, this.categoryChosen);
        parser.start();
        this.cardItemList = parser.getCardList();
    }
}
