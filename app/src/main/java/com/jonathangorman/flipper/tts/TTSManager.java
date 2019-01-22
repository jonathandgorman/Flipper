package com.jonathangorman.flipper.tts;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TTSManager implements TextToSpeech.OnInitListener {

    private Context context;
    private TextToSpeech tts;

    private Locale[] locales = Locale.getAvailableLocales();
    private List<Locale> localeList = new ArrayList<Locale>();

    public TTSManager(Context context)
    {
        this.context = context;
        this.tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {

    }

    public boolean checkLocaleAvailable(Locale locale)
    {
        int res = tts.isLanguageAvailable(locale);
        if (res == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
            return true;
        }
        return false;
    }

    public List<Locale> getAvailableLocaleList()
    {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            int res = tts.isLanguageAvailable(locale);
            if (res >= TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                Log.i("",locale.getDisplayName());
                localeList.add(locale);
            }
        }
        return localeList;
    }
}
