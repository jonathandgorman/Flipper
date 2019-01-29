package com.jonathangorman.lorlingo.primary;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.jonathangorman.lorlingo.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
