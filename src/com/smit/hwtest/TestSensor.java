package com.smit.hwtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TestSensor  extends Activity{
	private static final String TAG = "TestSensor";
	
	private Button mReturn = null;
	private Button mOK = null;
	private Button mFail = null;
	private Button mConnect = null;
	private Intent mIntent = null;
	
    private SensorManager sensorMgr;
    private SensorEvent mSensorEvent;
   private Sensor sensor;

    private float x, y, z;

    private TextView mTextViewSensor;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.test_sensor);
        
		HWLog.writeHWLog(getString(R.string.t_test_start_title)+
				 HWLog.ITEM_SENSOR_NAME
				 + getString(R.string.t_test_start_title));
		initView();
		
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        mTextViewSensor = (TextView) findViewById(R.id.myTextView);
        
        SensorEventListener lsn = new SensorEventListener() {
            public void onSensorChanged(SensorEvent e) {
                mSensorEvent=e;
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                    //setTitle("x=" + (int) x + "," + "y=" + (int) y + "," + "z=" + (int) z);
                    String str = "SensorChanged x=" + (int) x + "," + "y=" + (int) y + "," + "z="
                        + (int) z;
                    mTextViewSensor.setText(str);
            }
            
            public void onAccuracyChanged(Sensor s, int accuracy) {
                //setTitle("onAccuracyChanged :" + accuracy + "name :" +s.getName()+ " Res:"+s.getResolution());
            }
        };
        sensorMgr.registerListener(lsn, sensor, SensorManager.SENSOR_DELAY_GAME); 
        }
    

	private void initView(){
		//setTitle(R.string.test_color_mess);
		mReturn = (Button)findViewById(R.id.but_return);
	      mOK = (Button)findViewById(R.id.but_ok);
	        mFail = (Button)findViewById(R.id.but_fail);
	        
	        mReturn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					mIntent = new Intent(TestSensor.this, Testing.class);
					startActivity(mIntent);
				}
			});
			
		      mOK.setOnClickListener(new View.OnClickListener() {
		            private boolean hwLogTestReport;

					public void onClick(View v) {
		                Toast.makeText(TestSensor.this, "TEST Success", 
		                        Toast.LENGTH_SHORT).show();
		                mOK.setBackgroundColor(DEFAULT_KEYS_DISABLE);
		                mOK.setClickable(false);
		                mFail.setBackgroundColor(DEFAULT_KEYS_DISABLE);
		                mFail.setClickable(false);
		                
		                //HWLog.HWLogTestReport(R.string.t_lcd_title, true);

		                HWLog.HWLogTestReport(HWLog.ITEM_SENSOR_NAME, true);
		            }
		        });
		      
		       mFail.setOnClickListener(new View.OnClickListener() {
	               public void onClick(View v) {
	                   Toast.makeText(TestSensor.this, "TEST Fail", 
	                           Toast.LENGTH_SHORT).show();
	                   
	                   mOK.setBackgroundColor(DEFAULT_KEYS_DISABLE);
	                   mOK.setClickable(false);
	                   mFail.setBackgroundColor(DEFAULT_KEYS_DISABLE);
	                   mFail.setClickable(false);
	                   
	                   HWLog.HWLogTestReport(HWLog.ITEM_SENSOR_NAME, false);
	               }
	           });
	}
}
