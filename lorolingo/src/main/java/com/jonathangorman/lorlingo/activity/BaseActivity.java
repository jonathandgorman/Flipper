package com.jonathangorman.lorlingo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
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

/*
* Base activity from which all other activities extends from.
* Contains common functionality such as mobile ads, action buttons, etc.
* */

public class BaseActivity extends Activity implements RewardedVideoAdListener {

    private static final String TAG = BaseActivity.class.getName();
    private RewardedVideoAd mRewardedVideoAd;
    private final String TEST_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private final String AD_UNIT_ID = "ca-app-pub-2251083820126124/8019699240";
    private final String AD_UNIT_ACCOUNT = "ca-app-pub-2251083820126124~2763503135";
    protected TTSManager ttsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Initialise AdMob and load an ad so that it's ready for the user
        MobileAds.initialize(this, AD_UNIT_ACCOUNT);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        if (!mRewardedVideoAd.isLoaded())
        {
            loadRewardedVideoAd();
        }

        // Check TTS engine is available before starting
        this.ttsManager = new TTSManager(this);
        if(this.ttsManager.getInstalledTTSEngines().isEmpty())
        {
            Toast.makeText(this, "No TTS engine has been detected - please install a TTS to use the application correctly", Toast.LENGTH_LONG);
        }

    }

    // called when the options menu is created
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.action_buttons, menu ); // add action buttons
        return true;
    }

    // action item options definitions
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
            case R.id.adjust_speech_rate:
                SharedPreferences sharedPrefs = getSharedPreferences("SPEECH_PREFERENCES", Context.MODE_PRIVATE);
                float currSpeechRate = sharedPrefs.getFloat("SPEECH_RATE",  Context.MODE_PRIVATE);
                if (currSpeechRate == TTSManager.NORMAL_SPEECH_RATE)
                {
                    sharedPrefs.edit().putFloat("SPEECH_RATE", TTSManager.HALF_SPEECH_RATE).apply();
                    this.ttsManager = new TTSManager(this);
                    this.ttsManager.setSpeechRate(TTSManager.HALF_SPEECH_RATE);
                    Toast.makeText(this, getString(R.string.speech_rate_halved), Toast.LENGTH_LONG).show();
                }
                else
                {
                    sharedPrefs.edit().putFloat("SPEECH_RATE", TTSManager.NORMAL_SPEECH_RATE).apply();
                    this.ttsManager = new TTSManager(this);
                    this.ttsManager.setSpeechRate(TTSManager.NORMAL_SPEECH_RATE);
                    Toast.makeText(this, getString(R.string.speech_rate_doubled), Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.coffee_action:
                createAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // common alert dialog call for reward ad popup
    public void createAlertDialog()
    {
        // Create the alert dialog popup - show ad if "ok", otherwise exit...
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton( getString(R.string.ok_alert_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                else
                {
                    // show a toast
                    Toast.makeText(getApplicationContext(), getString(R.string.video_ad_not_loading),
                            Toast.LENGTH_LONG).show();
                    loadRewardedVideoAd(); // attempt to load the video
                    //Log.i(TAG,"Ad not loaded yet.");
                }
            }
        }).setNegativeButton(getString(R.string.no_alert_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {/*no action on click*/}
        });

        // Custom popup title created and inflated here
        TextView title = new TextView(this);
        title.setText(getString(R.string.coffee_text_title));
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
    // Loads the reward ad
    protected void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(AD_UNIT_ID,
                new AdRequest.Builder().build());
    }

    // Called when the ad has finished and is closed
    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, getString(R.string.video_ad_finish_message), Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd(); // reload another add once finished
    }
    // Called when the app is left before the ad has finished
    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, getString(R.string.video_ad_left_ad_message), Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd(); // reload another add once finished
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) { }
    @Override
    public void onRewardedVideoAdLoaded() {}
    @Override
    public void onRewardedVideoAdOpened() { }
    @Override
    public void onRewardedVideoStarted() { }
    @Override
    public void onRewarded(RewardItem rewardItem) { }
    @Override
    public void onRewardedVideoCompleted() {
    }

    public TTSManager getTtsManager() {
        return ttsManager;
    }
}
