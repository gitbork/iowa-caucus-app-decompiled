package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.facebook.react.C0575R;

public class DevSettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getApplication().getResources().getString(C0575R.string.catalyst_settings_title));
        addPreferencesFromResource(C0575R.C0578xml.rn_dev_preferences);
    }
}
