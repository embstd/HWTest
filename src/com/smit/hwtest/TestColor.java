package com.smit.hwtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TestColor extends Activity {
	private static final String TAG = "TestColor";
		
	private Button mReturn = null;
	private Button mOK = null;
	private Button mFail = null;
	private Button mNext = null;
	private TextView mText1 = null;
	private TextView mText2 = null;
	private TextView mText3 = null;
	private Intent mIntent = null;
	
	private int mNum = 0;
	
	//private PowerManager mPowerManager;
	//private HWLog mHWLog; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    HideStatusBar();
		setContentView(R.layout.test_color);

		 HWLog.writeHWLog(getString(R.string.t_test_start_title)+
				 HWLog.ITEM_LCD_NAME
				 + getString(R.string.t_test_start_title));
	   // mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE); 
		//mHWLog = (HWLog) getSystemService(Context.); 	    
		//HideTitle();
		initView();

	}
	
	private void initView(){
		//setTitle(R.string.test_color_mess);
		mReturn = (Button)findViewById(R.id.but_return);
		mNext = (Button)findViewById(R.id.but_next);
	      mOK = (Button)findViewById(R.id.but_ok);
	        mFail = (Button)findViewById(R.id.but_fail);
		
		mText1 = (TextView)findViewById(R.id.test_color_text1);
		mText2 = (TextView)findViewById(R.id.test_color_text2);
		mText3 = (TextView)findViewById(R.id.test_color_text3);
		
		mReturn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mIntent = new Intent(TestColor.this, Testing.class);
				startActivity(mIntent);
			}
		});
		
		mNext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mNum ++;
				changeColor(mNum);
			}
		});
		
	      mOK.setOnClickListener(new View.OnClickListener() {
	            private boolean hwLogTestReport;

				public void onClick(View v) {
	                Toast.makeText(TestColor.this, "TEST Success", 
	                        Toast.LENGTH_SHORT).show();
	                mOK.setBackgroundColor(DEFAULT_KEYS_DISABLE);
	                mOK.setClickable(false);
	                mFail.setBackgroundColor(DEFAULT_KEYS_DISABLE);
	                mFail.setClickable(false);
	                
	                //HWLog.HWLogTestReport(R.string.t_lcd_title, true);

	                HWLog.HWLogTestReport(HWLog.ITEM_LCD_NAME, true);
	            }
	        });
	      
	       mFail.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   Toast.makeText(TestColor.this, "TEST Fail", 
                           Toast.LENGTH_SHORT).show();
                   
                   mOK.setBackgroundColor(DEFAULT_KEYS_DISABLE);
                   mOK.setClickable(false);
                   mFail.setBackgroundColor(DEFAULT_KEYS_DISABLE);
                   mFail.setClickable(false);
                   
                   HWLog.HWLogTestReport(HWLog.ITEM_LCD_NAME, false);
               }
           });
	}
	
	private void changeColor(int num){
		Log.e(TAG, "num = " + (num%6));
		switch(num % 6){
		case 0:
			mText1.setBackgroundColor(Color.RED);
			mText2.setBackgroundColor(Color.GREEN);
			mText3.setBackgroundColor(Color.BLUE);
			break;
		case 1:
			mText1.setBackgroundColor(Color.BLUE);
			mText2.setBackgroundColor(Color.RED);
			mText3.setBackgroundColor(Color.GREEN);
			break;
		case 2:
			mText1.setBackgroundColor(Color.GREEN);
			mText2.setBackgroundColor(Color.BLUE);
			mText3.setBackgroundColor(Color.RED);
			break;
		case 3:
			mText1.setBackgroundColor(Color.RED);
			mText2.setBackgroundColor(Color.RED);
			mText3.setBackgroundColor(Color.RED);
			break;
		case 4:
			mText1.setBackgroundColor(Color.GREEN);
			mText2.setBackgroundColor(Color.GREEN);
			mText3.setBackgroundColor(Color.GREEN);
			break;
		case 5:
			mText1.setBackgroundColor(Color.BLUE);
			mText2.setBackgroundColor(Color.BLUE);
			mText3.setBackgroundColor(Color.BLUE);
			break;
		}
	}
	
	private void HideStatusBar() {
	 //隐藏标题
	 requestWindowFeature(Window.FEATURE_NO_TITLE);
	 //定义全屏参数
	 int flag=WindowManager.LayoutParams. FLAG_FULLSCREEN ;
	 //获得窗口对象
	 Window myWindow=this.getWindow();
	 //设置Flag标识
	 myWindow.setFlags(flag,flag);
	 }
	
    private void setBrightness(int brightness) {
/*        try {
            IPowerManager power = IPowerManager.Stub.asInterface(
                    ServiceManager.getService("power"));
            if (power != null) {
                power.setBacklightBrightness(brightness);
            }
        } catch (RemoteException doe) {
            
        }  */      
    }

}
