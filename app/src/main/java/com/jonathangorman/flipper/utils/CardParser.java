package com.jonathangorman.flipper.utils;

import android.util.Log;

import com.jonathangorman.flipper.cards.Card;
import com.jonathangorman.flipper.cards.CardList;
import com.jonathangorman.flipper.cards.FoodCard;
import static com.jonathangorman.flipper.utils.Constants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CardParser {
    private static final int RESOURCE_POS = 2;
    private Card currCard = null;
    private String parseLang = "";
    private String parseCat = "";
    private CardList cardList = new CardList();

    // Returns the cardList
    public CardList getCardList()
    {
        if (cardList.isEmpty())
        {
            Log.i(LOG, "The card list is empty. Must call parse() first.");
            return null;
        }
        return cardList;
    }

    // starts the parsing process
    public int start()
    {
        // Check that the config file exists
        String currLine;
        if (!new File(CONFIG_FILE).exists())
        {
            Log.e(LOG, "Error, unable to find configuration file @" + CONFIG_FILE);
            return ERROR;
        }

        //
        try (BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE))) {
            while ((currLine = br.readLine()) != null) {
                // Take a line and convert it to a card, then add it to a cardList
                currCard = convertLineToConfig(currLine);
                addCardToCardList(currCard);
            }
        } catch (IOException e) {
            Log.e(LOG, "Error, IO exception has occurred: " + e );
            return ERROR;
        }
        return TRUE;
    }

    // Converts a line of the config file to a Card
    private Card convertLineToConfig(String input)
    {
        Log.i(LOG, "Converting config line to card: " + input);
        Card newCard = null;
        String[] lineSplit;

        // parse the input line to keywords
        lineSplit = input.split(";");
        String resourceName = lineSplit[RESOURCE_POS];

        // choose the type of cards according to the category
        switch (parseCat) {
            case "fruit":
                newCard = new FoodCard();
                break;
            case "vegetables":
                break;
            default:
                Log.e(LOG, "Error, unable to determine card type from line: " + input);
                return null;
        }

        // Set the new card details
        newCard.setName(resourceName);
        newCard.setAudio(resourceName);
        newCard.setImage(resourceName);
        newCard.setCategory(parseCat);
        newCard.setLanguage(parseLang);

        return newCard;
    }

    // Adds a card to the list
    ArrayList<Card> addCardToCardList(Card card)
    {
        cardList.add(card);
        return cardList;
    }

    public void setParserLang(String inputLang)
    {
        this.parseLang = inputLang;
    }

    public void setParserCategory(String inputCat)
    {
        this.parseCat = inputCat;
    }
}
