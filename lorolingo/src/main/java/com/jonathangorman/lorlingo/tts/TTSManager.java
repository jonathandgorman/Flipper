package com.jonathangorman.lorlingo.tts;

import android.content.Context;
import android.content.ContextWrapper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class TTSManager implements TextToSpeech.OnInitListener {

    private static final String TAG = TTSManager.class.getName();

    private Context context;
    private TextToSpeech tts;
    private boolean ttsInitialised = false;

    public static final float HALF_SPEECH_RATE = 0.5f;
    public static final float DOUBLE_SPEECH_RATE = 2.0f;
    public static final float NORMAL_SPEECH_RATE = 1.0f;

    public TTSManager(Context context)
    {
        this.context = context;
        this.tts = new TextToSpeech(context, this);
        this.setSpeechRate(context.getSharedPreferences("SPEECH_PREFERENCES", ContextWrapper.MODE_PRIVATE).getFloat("SPEECH_RATE", NORMAL_SPEECH_RATE));
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

    public void setSpeechRate(float rate)
    {
        this.tts.setSpeechRate(rate);
    }

    public void setHalfSpeechRate()
    {
        this.tts.setSpeechRate(HALF_SPEECH_RATE);
    }

    public void setNormalSpeechRate()
    {
        this.tts.setSpeechRate(HALF_SPEECH_RATE);
    }

    public void setDoubleSpeechRate()
    {
        this.tts.setSpeechRate(DOUBLE_SPEECH_RATE);
    }

    public TextToSpeech getTTS()
    {
        return this.tts;
    }
}
