package com.jonathangorman.lorlingo.domain;

import android.content.Context;
import android.util.Log;

import com.jonathangorman.lorlingo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardParser {
    private static final String TAG = "CardParser";
    private static final int LANGUAGE_POS = 0;
    private static final int CATEGORY_POS = 1;
    private static final int ID_POS = 2;
    private static final int AUDIO_POS = 3;
    private static final int IMAGE_POS = 4;

    private Context context;
    private CardItem currCard;
    private String categoryName;
    private String languageName;
    private ArrayList<CardItem> cardList = new ArrayList<>();

    public CardParser(Context context, String languageName, String categoryName)
    {
        this.context = context;
        this.categoryName = categoryName;
        this.languageName = languageName;
    }

    // Returns the cardList
    public ArrayList<CardItem> getCardList() {
        return cardList;
    }

    // starts the parsing process
    public int start() {
        String language;
        String category;
        String currLine;

        // Check that the config file exists
        InputStream is = getConfigStream(this.languageName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 8192);

        try {
            while ((currLine = br.readLine()) != null) {

                currCard = new CardItem();
                // parse the input line to keywords based on ";"
                String[] lineSplit;
                lineSplit = currLine.split(";");

                // If the language and category of the line do not comply, we check the next line
                language = lineSplit[LANGUAGE_POS];
                if (!language.equalsIgnoreCase(languageName)) {
                    continue;
                }
                category = lineSplit[CATEGORY_POS];
                if (!category.equalsIgnoreCase(categoryName)) {
                    continue;
                }

                // Add details to the card
                currCard.setLanguage(language);
                currCard.setCategory(category);
                currCard.setNameId(lineSplit[ID_POS]);
                currCard.setAudioString(lineSplit[AUDIO_POS]);
                currCard.setImageId(String.valueOf(context.getResources().getIdentifier(lineSplit[IMAGE_POS], "drawable", context.getPackageName())));

                this.cardList.add(currCard);
            }
        } catch (Exception e) {
            //Log.e(TAG, "Exception when parsing: " + e.getMessage());
            return -1;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    //Log.e(TAG, "Error when closing buffered reader " + e);
                    return -1;
                }
            }
        }
        return 1;
    }


    private InputStream getConfigStream(String language)
    {
        InputStream is = null;
        // set locale according to language
        switch (language)
        {
            case ("united_kingdom"):
                 is = context.getResources().openRawResource(R.raw.config_united_kingdom);
                 break;
            case ("spain"):
                is = context.getResources().openRawResource(R.raw.config_spain);
                break;
            case ("france"):
                is = context.getResources().openRawResource(R.raw.config_france);
                break;
            case ("germany"):
                is = context.getResources().openRawResource(R.raw.config_germany);
                break;
            case ("italy"):
                is = context.getResources().openRawResource(R.raw.config_italy);
                break;
            case ("portugal"):
                is = context.getResources().openRawResource(R.raw.config_portugal);
                break;
            default:
        }
        return is;
    }
}
