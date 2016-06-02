package digimpact.org.rsattendance.activity;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import digimpact.org.rsattendance.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.settings_activity);

        getFragmentManager().beginTransaction().replace(android.R.id.content,new SettingsFragment())
                .commit();

    }

    public static class  SettingsFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            //getting and setting summary of preference on loading
            EditTextPreference serverSetting = (EditTextPreference) getPreferenceScreen()
                    .getPreference(0);

            String currentServerUrl = serverSetting.getText();
            if(currentServerUrl == null || currentServerUrl.equals("")){
                serverSetting.setSummary(getString(R.string.please_enter_server_url));
            }else{
                serverSetting.setSummary(currentServerUrl.trim());
            }


        }

        /**
         * Called when a shared preference is changed, added, or removed. This
         * may be called even if a preference is set to its existing value.
         * <p/>
         * <p>This callback will be run on your main thread.
         *
         * @param sharedPreferences The {@link SharedPreferences} that received
         *                          the change.
         * @param key               The key of the preference that was changed, added, or
         */
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            if(key.equals("server_url")){
                EditTextPreference serverSetting = (EditTextPreference) findPreference("server_url");

                serverSetting.setSummary(sharedPreferences.getString("server_url",""));
            }

        }

        @Override
        public void onResume(){
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause(){
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }


    }
}
