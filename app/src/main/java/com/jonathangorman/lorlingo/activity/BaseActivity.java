package com.jonathangorman.lorlingo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class BaseActivity extends Activity implements RewardedVideoAdListener {

    protected RewardedVideoAd mRewardedVideoAd;
    private String TEST_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private String AD_UNIT_ID = "ca-app-pub-2251083820126124~2763503135";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialise AdMob and load an ad if necessary
        MobileAds.initialize(this, "ca-app-pub-2251083820126124~2763503135");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        if (!mRewardedVideoAd.isLoaded())
        {
            loadRewardedVideoAd();
        }
    }

    // ensures that menu is setup and action buttons added
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.action_buttons, menu );
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
            case R.id.coffee_action:
                createAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // common alert dialog call for reward ad
    public void createAlertDialog()
    {
        // Create the alert dialog popup
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton( getString(R.string.ok_alert_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO AdMob integration if "ok" chosen - ca-app-pub-2251083820126124~2763503135
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                else
                {
                    // show a toast
                    Toast.makeText(getApplicationContext(), getString(R.string.video_ad_not_loading),
                            Toast.LENGTH_SHORT).show();
                    loadRewardedVideoAd(); // attempt to load the video
                }
            }
        }).setNegativeButton(getString(R.string.no_alert_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Custom popup title set here
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
        mRewardedVideoAd.loadAd(TEST_AD_UNIT_ID,
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
}
