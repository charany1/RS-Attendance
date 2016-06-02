package digimpact.org.rsattendance.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import digimpact.org.rsattendance.R;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class AttendanceActivity extends AppCompatActivity {

    private TextView mImeiTextView;
    private TextView mTimeStampView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.attendance_activity);

        mImeiTextView = (TextView)findViewById(R.id.imei_text_view);
        mTimeStampView = (TextView)findViewById(R.id.time_stamp_text_view);


        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String id = telephonyManager.getDeviceId();
        mImeiTextView.setText("IMEI: "+id);

        Log.d("Attendance",id);

        Long time = System.currentTimeMillis()/1000;

        mTimeStampView.setText("Timestamp: "+time.toString());

    }


    public void markAttendanceButtonHandler(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String serverUrl = sharedPreferences.getString("server_url","");
        Log.d("serverUrl",serverUrl);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_attendance_activitiy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(AttendanceActivity.this,SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
