package com.wjwinter.mypopularmovies.utilities;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.wjwinter.mypopularmovies.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.preferences);

    }
}
