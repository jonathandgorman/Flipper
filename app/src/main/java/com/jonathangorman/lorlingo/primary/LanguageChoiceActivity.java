package com.jonathangorman.lorlingo.primary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.action.InfoDisplayActivity;
import com.jonathangorman.lorlingo.tts.TTSManager;
import com.jonathangorman.lorlingo.adapter.LanguageChoiceAdapter;

import java.util.ArrayList;

public class LanguageChoiceActivity extends Activity implements RewardedVideoAdListener {

    private static final String TAG = LanguageChoiceActivity.class.getName();
    private static final int TTS_ENGINE_CHECK_CODE = 0;
    private static boolean TTS_AVAILABLE = false; // TTS engine available

    TTSManager ttsManager;
    private RewardedVideoAd mRewardedVideoAd;

    // TODO combine into single object
    ArrayList<Integer> languagesImagesList = new ArrayList<Integer>();
    ArrayList<String> languageDisplayTextList = new ArrayList<String>();
    ArrayList<String> languageNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "ACTIVITY CREATE: Creating LanguageChoiceActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);

        // Initialise AdMob
        MobileAds.initialize(this, "ca-app-pub-2251083820126124~2763503135");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        // initialise view lists
        initLanguageLists(); //TODO init lists based on language of the device

        ttsManager = new TTSManager(this);

        // Check that a TTS engine is installed on the device
        checkTTSEngineInstalled();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "Started LanguageChoiceActivity");
        super.onStart();

        //TODO check what languages are available, and disable those that are not

        // Initialise and create recycler view and adapter to show the language choices
        RecyclerView recyclerView = findViewById(R.id.language_recycle_view);
        LanguageChoiceAdapter adapter = new LanguageChoiceAdapter(this, this.languagesImagesList, this.languageDisplayTextList, this.languageNameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Initialise lists of data for the view
    private void initLanguageLists() {

        //TODO external configuration possible here
        languagesImagesList.add(R.drawable.united_kingdom);
        languageDisplayTextList.add(getString(R.string.english_language));
        languageNameList.add("united_kingdom");

        languagesImagesList.add(R.drawable.spain);
        languageDisplayTextList.add(getString(R.string.spanish_language));
        languageNameList.add("spain");

        languagesImagesList.add(R.drawable.france);
        languageDisplayTextList.add(getString(R.string.french_language));
        languageNameList.add("france");

        languagesImagesList.add(R.drawable.germany);
        languageDisplayTextList.add(getString(R.string.german_language));
        languageNameList.add("germany");

        languagesImagesList.add(R.drawable.italy);
        languageDisplayTextList.add(getString(R.string.italian_language));
        languageNameList.add("italy");

        languagesImagesList.add(R.drawable.portugal);
        languageDisplayTextList.add(getString(R.string.portuguese_language));
        languageNameList.add("portugal");
    }

    // Check that a TTS engine is available
    public void checkTTSEngineInstalled() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, TTS_ENGINE_CHECK_CODE);
    }

    // Handles on activity result codes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check for TTS engine
        if (requestCode == TTS_ENGINE_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Log.i(TAG, "onActivityResult: TTS engine installed and available");
                this.TTS_AVAILABLE = true;

            } else {
                //TODO prompt for TTS install, prior to install
                //TODO ensure that internet connection available for downlaod of TTS engine
                Log.i(TAG, "onActivityResult: No TTS engine available. Prompting to install.");
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                Toast.makeText(getApplicationContext(), "TTS engine has been installed and is now ready",Toast.LENGTH_LONG).show();
                this.TTS_AVAILABLE = true;
            }
        }
    }

    // ensures that menu is setup and action buttons added
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.action_buttons, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent actionInfoIntent = new Intent(this, InfoDisplayActivity.class);
        switch (item.getItemId()) {
            case R.id.about_action:
                actionInfoIntent.putExtra("INFOTYPE", "about"); // adapter position is removed from the list and added to intent
                this.startActivity(actionInfoIntent);
                return true;
            case R.id.faq_action:
                actionInfoIntent.putExtra("INFOTYPE", "faq"); // adapter position is removed from the list and added to intent
                this.startActivity(actionInfoIntent);
                return true;
            case R.id.credit_action:
                actionInfoIntent.putExtra("INFOTYPE", "credit"); // adapter position is removed from the list and added to intent
                this.startActivity(actionInfoIntent);
                return true;
            case R.id.coffee_action:
                createAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createAlertDialog()
    {
        // Create the alert dialog popup
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO AdMob integration if "ok" chosen - ca-app-pub-2251083820126124~2763503135
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        }).setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Custom popup title set here
        TextView title = new TextView(this);
        title.setText("Your support is important!");
        title.setBackgroundColor(getColor(R.color.colorPrimary));
        title.setPadding(10, 15, 15, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);

        AlertDialog dialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.coffee_popup, null);
        dialog.setMessage(getString(R.string.coffee_text));
        dialog.setCustomTitle(title);
        dialog.setView(dialogLayout);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.show();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "Thanks for your support! :)", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd(); // reload another add once finished
    }
    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "You left the app before watching the ad :(", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd(); // reload another add once finished
    }
    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "Oops... the ad isn't ready yet!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRewardedVideoAdLoaded() {
    }
    @Override
    public void onRewardedVideoAdOpened() {
    }
    @Override
    public void onRewardedVideoStarted() {
    }
    @Override
    public void onRewarded(RewardItem rewardItem) {
    }
    @Override
    public void onRewardedVideoCompleted() {
    }
}