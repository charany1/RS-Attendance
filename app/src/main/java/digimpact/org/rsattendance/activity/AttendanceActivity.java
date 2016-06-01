package digimpact.org.rsattendance.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
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







        ;
    }
}
