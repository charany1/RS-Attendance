package digimpact.org.rsattendance.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import digimpact.org.rsattendance.R;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_activity);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String id = telephonyManager.getDeviceId();
        Toast.makeText(AttendanceActivity.this, id, Toast.LENGTH_LONG).show();
        Log.d("Attendance",id);
    }
}
