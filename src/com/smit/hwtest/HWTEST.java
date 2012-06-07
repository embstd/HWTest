package com.smit.hwtest;


import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HWTEST extends Activity {
	
	private String TAG = "HWTEST";
    private Button mTestNormal = null;
    private Button mTestFactory = null;
    private Button mTestExit =null;
    
    private Intent mIntent = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent i = new Intent( HWTEST.this, HWLog.class );
        i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        startService(i); 
        
        HWLog.GetDeviceId();
        
        initTest();      
    }
    
    private void initTest(){
        mTestNormal = (Button)findViewById(R.id.test_Normal);
        mTestFactory = (Button)findViewById(R.id.test_Factory);
        mTestExit = (Button)findViewById(R.id.test_Exit);
        
        mTestNormal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {  
            	
                mIntent = new Intent(HWTEST.this, Testing.class);
                startActivity(mIntent);
            }
        });
        
        mTestFactory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 HWLog.SetFactoryMode();
                mIntent = new Intent(HWTEST.this, Testing.class);
                startActivity(mIntent);
            }
        });
        
        mTestExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	try {
					HWLog.PrintHWLogReport();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
    @Override
    protected void onPause()
    {
        Intent i = new Intent( HWTEST.this, HWLog.class );
        stopService(i);
        super.onPause();
    }
}