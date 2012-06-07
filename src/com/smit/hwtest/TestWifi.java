package com.smit.hwtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class TestWifi extends Activity {
	private static final String TAG = "TestWifi";
		
	private Button mReturn = null;
	private Button mOK = null;
	private Button mFail = null;
	private Button mConnect = null;
	private Intent mIntent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_wifi);
		
		HWLog.writeHWLog(getString(R.string.t_test_start_title)+
				 HWLog.ITEM_WIFI_NAME
				 + getString(R.string.t_test_start_title));
		initView();
	}
	
	private void initView(){
		//setTitle(R.string.test_color_mess);
		mReturn = (Button)findViewById(R.id.but_return);
		mConnect = (Button)findViewById(R.id.but_connect);
	      mOK = (Button)findViewById(R.id.but_ok);
	        mFail = (Button)findViewById(R.id.but_fail);
	        
	        mReturn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					mIntent = new Intent(TestWifi.this, Testing.class);
					startActivity(mIntent);
				}
			});
			
	        mConnect.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {				
					mIntent = new Intent();
			        final ComponentName component = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
			        mIntent.setComponent(component);
			        startActivity(mIntent);	
				}
			});
			
		      mOK.setOnClickListener(new View.OnClickListener() {
		            private boolean hwLogTestReport;

					public void onClick(View v) {
		                Toast.makeText(TestWifi.this, "TEST Success", 
		                        Toast.LENGTH_SHORT).show();
		                mOK.setBackgroundColor(DEFAULT_KEYS_DISABLE);
		                mOK.setClickable(false);
		                mFail.setBackgroundColor(DEFAULT_KEYS_DISABLE);
		                mFail.setClickable(false);
		                
		                //HWLog.HWLogTestReport(R.string.t_lcd_title, true);

		                HWLog.HWLogTestReport(HWLog.ITEM_WIFI_NAME, true);
		            }
		        });
		      
		       mFail.setOnClickListener(new View.OnClickListener() {
	               public void onClick(View v) {
	                   Toast.makeText(TestWifi.this, "TEST Fail", 
	                           Toast.LENGTH_SHORT).show();
	                   
	                   mOK.setBackgroundColor(DEFAULT_KEYS_DISABLE);
	                   mOK.setClickable(false);
	                   mFail.setBackgroundColor(DEFAULT_KEYS_DISABLE);
	                   mFail.setClickable(false);
	                   
	                   HWLog.HWLogTestReport(HWLog.ITEM_WIFI_NAME, false);
	               }
	           });
	}
	
	
}
