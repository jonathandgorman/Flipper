package com.jonathangorman.flipper.screen;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.adapters.CardChoiceAdapter;
import com.jonathangorman.flipper.adapters.CategoryChoiceAdapter;

import java.util.ArrayList;

import static com.jonathangorman.flipper.utils.Constants.CARDS_PER_ROW;

public class CardScreenActivity extends Activity {

    private static final String TAG = "CardScreenActivity";
    ArrayList<String> recyclerImagesList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Initialise lists once for the recyclerView
        initLists();
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
    void initLists()
    {
        recyclerImagesList.add(String.valueOf(R.mipmap.apple_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.banana_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.mango_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.watermelon_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.kiwi_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.blueberry_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.coconut_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.apricot_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.lemon_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.grapes_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.pineapple_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.cherry_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.orange_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.carambola_layer));

        recyclerImagesList.add(String.valueOf(R.mipmap.chestnut_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.dates_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.dragon_fruit_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.fig_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.goji_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.gooseberry_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.grapefruit_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.guava_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.kumquat_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.lime_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.mangosteen_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.melon_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.papaya_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.passion_fruit_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.persimmion_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.plum_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.quince_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.rambutan_fruit_layer));
        recyclerImagesList.add(String.valueOf(R.mipmap.rasberry_layer));
    }
}
