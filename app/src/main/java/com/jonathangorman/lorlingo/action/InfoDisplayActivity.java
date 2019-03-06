package com.jonathangorman.lorlingo.action;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.jonathangorman.lorlingo.R;

public class InfoDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_display_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // take the intent and determine the info to be shown
        Intent rxIntent = this.getIntent();
        String infoType = rxIntent.getStringExtra("INFOTYPE");
        TextView infoTextView = (TextView) findViewById(R.id.infoTextView);

        // Change the displayed info based on the chosen action
        switch (infoType)
        {
            case "about":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    infoTextView.setMovementMethod(LinkMovementMethod.getInstance());
                    infoTextView.setText(Html.fromHtml(getString(R.string.about_text), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    infoTextView.setText(Html.fromHtml(getString(R.string.about_text)));
                }
                break;
            case "faq":

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    infoTextView.setText(Html.fromHtml(getString(R.string.faq_text), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    infoTextView.setText(Html.fromHtml(getString(R.string.faq_text)));
                }
                break;
            case "credit":

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    infoTextView.setText(Html.fromHtml(getString(R.string.credit_text), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    infoTextView.setText(Html.fromHtml(getString(R.string.credit_text)));
                }
               break;
            case "coffee":
                infoTextView.setText(getString(R.string.coffee_text));
                break;
            case "default":
                infoTextView.setText(getString(R.string.error_text));
                break;
        }
    }
}
