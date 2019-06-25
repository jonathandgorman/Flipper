package com.jonathangorman.lorlingo.tts;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class TTSManager implements TextToSpeech.OnInitListener {

    private static final String TAG = TTSManager.class.getName();

    private Context context;
    private TextToSpeech tts;
    private boolean ttsInitialised = false;

    public TTSManager(Context context)
    {
        this.context = context;
        this.tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        //Log.i(TAG, "TTS engine initialised");
        ttsInitialised = true;
    }

    public List<TextToSpeech.EngineInfo> getInstalledTTSEngines()
    {
        return tts.getEngines();
    }
    // check that voice data is installed for locale
    public boolean checkVoiceDataAvailable(Locale locale) {
        tts.setLanguage(locale);
        Voice voice = tts.getVoice();
        if (voice != null) {
            Set<String> features = voice.getFeatures();
            if (features != null && !features.contains(TextToSpeech.Engine.KEY_FEATURE_NOT_INSTALLED)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTtsInitialised() {
        return ttsInitialised;
    }
}
