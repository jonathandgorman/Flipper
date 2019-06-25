package com.jonathangorman.lorlingo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.adapter.CategoryChoiceAdapter;
import com.jonathangorman.lorlingo.domain.CategoryItem;

import java.util.ArrayList;
public class CategoryChoiceActivity extends BaseActivity {

    private static final String TAG = "CategoryChoiceActivity";
    private String languageChosen = "";
    private ArrayList<CategoryItem> categoryItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.i(TAG, "ACTIVITY CREATE: Creating CategoryChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choice);

        // Get language choice from previous activity
        Intent intentLanguageChoice = getIntent();
        languageChosen = intentLanguageChoice.getStringExtra("LANGUAGE");
        //Log.d(TAG, "Intent received from LanguageChoiceActivity: " + languageChosen);

        // Initialise lists once for the recyclerView
        initLists();
    }

    @Override
    protected void onStart() {
        //Log.d(TAG, "Starting categoryChoiceActivity");
        super.onStart();

        // Initialise and create recycler view and adapter to show the category choices
        RecyclerView recyclerView = findViewById(R.id.categoryRecyclerView);
        CategoryChoiceAdapter adapter = new CategoryChoiceAdapter(this, this.languageChosen, this.categoryItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialises the lists required by the recyclerView
    void initLists()
    {
        CategoryItem item = new CategoryItem();
        item.setDisplayText(getString(R.string.numbers_category));
        item.setImageId(R.drawable.one);
        item.setNameId("numbers");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.alphabet_category));
        item.setImageId(R.drawable.letter_a);
        item.setNameId("alphabet");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.fruit_category));
        item.setImageId(R.drawable.watermelon);
        item.setNameId("fruit");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.vegetables_category));
        item.setImageId(R.drawable.red_pepper);
        item.setNameId("vegetables");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.animals_category));
        item.setImageId(R.drawable.cat);
        item.setNameId("animals");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.jobs_category));
        item.setImageId(R.drawable.firefighter);
        item.setNameId("professions");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.transport_category));
        item.setImageId(R.drawable.car);
        item.setNameId("transport");
        this.categoryItemList.add(item);

        item = new CategoryItem();
        item.setDisplayText(getString(R.string.household_category));
        item.setImageId(R.drawable.couch);
        item.setNameId("household");
        this.categoryItemList.add(item);
    }
}
