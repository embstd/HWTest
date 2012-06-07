package com.smit.hwtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class HWLog extends Service
{

	private static String TAG = "HWLog";
	
  //private Handler objHandler = new Handler();
  //public static int intCounter=0;
  

  public int intUpdateSecond = 5000;
  static FileOutputStream fos_log;
  static FileOutputStream fos_rep;

  static boolean is_factory=false;
  private static String mDeviceId="0";
  //private static String strHWTestLog = "/sdcard/HWTest/HWTest.log";
  private static String LogfileEx="/sdcard/HWTest/";
  private static String strHWTestLog = "HWTest.log";
  private static String strHWTestReport = "HWTestReport.log";

  public static String ITEM_LCD_NAME="Test LCD";
  public static String ITEM_KEY_NAME="Test Keyboard";
  public static String ITEM_BT_NAME="Test Bluetooth";
  public static String ITEM_WIFI_NAME="Test WIFI";
  public static String ITEM_SENSOR_NAME="Test Sensor";
  
  private static String ITEM_TABLES[] = { ITEM_LCD_NAME,  ITEM_KEY_NAME, ITEM_BT_NAME, ITEM_WIFI_NAME, ITEM_SENSOR_NAME};
  
  private static List<String> ITEM_LIST;
  private static List<String> ITEM_R_LIST;
  
  public static String ITEM_R_OK="Ok";
  public static String ITEM_R_FAIL="Fail";
  public static String ITEM_R="Not Test Yet";
  
  public String string;
  
  public static String Check_HWLogTestReport(final String item)
  {
	  
	  //string = getResources().getText(R.string.t_lcd_title).toString();
	  int i=0;
	  for (i=0; i<ITEM_LIST.size(); i++)
	  {
		  if(item.equals(ITEM_LIST.get(i)))
		  {
			  return ITEM_R_LIST.get(i);
		  }
	  }
	  return ITEM_R;
  }
  
  public static boolean HWLogTestReport(final String item, boolean is_ok)
  {
/*	
	  String tables[] = { "t_restaurant" ,  ITEM_LCD_NAME,  "t_restaurant"};
	  Log.i(TAG,"tables.length= "+tables.length+ tables[1]);
	  */
	  int i=0;
	  for (i=0; i<ITEM_LIST.size(); i++)
	  {
		  if(is_ok)
		  {
			  if(item.equals(ITEM_LIST.get(i)))
			  {
				  ITEM_R_LIST.set(i, ITEM_R_OK);
				  break;
			  }
		  }
		  else
		  {
			  if(item.equals(ITEM_LIST.get(i)))
			  {
				  ITEM_R_LIST.set(i, ITEM_R_FAIL);
				  break;
			  }
			  
		  }
		 
	  }
	  
	 writeHWLog("HWLogTestReport: " + item + "=" + is_ok);
	 return true;
  }
  
  /*
  private Runnable mTasks = new Runnable() 
  { 
    public void run() 
    { 
      if(checkSDCard())
      {
        //intCounter++;
        
        Log.i(TAG, "Run---Counter:"+Integer.toString(intCounter));
        //writeHWLog("HWLog counter" +Integer.toString(intCounter));
        objHandler.postDelayed(mTasks, intUpdateSecond);
        
        //String string1 = getResources().getText(R.string.t_lcd_title).toString();
      }
      else
      {
        mMakeTextToast
        (
          "There are no SDCard",
          true
        );
      }
    }
  };*/
  
  public static boolean writeHWLog(String strLog)
  {
	  if(is_factory)
	  {
		  //Log.i(TAG,"Factory mode");
		  return true;
	  }
	  
	  Log.i(TAG, "writeHWLog write "+ strLog + " into " + strHWTestLog);
	  try
	    {
		  byte[] buf = strLog.getBytes("utf-8");
		  fos_log.write(buf);
		  fos_log.write(new String("\n").getBytes());
	  }catch(IOException e)
      {
        return false;
      }
	  return true;
  }
  /*
  private boolean checkSDCard()
  {
    if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
 
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
  }
  */
  
  public static boolean GetDeviceId()
  {
	  
	  mDeviceId="012345";
	  
	  return true;
  }
  
  public static boolean SetFactoryMode()
  {
	 is_factory=true;
	 
	 return is_factory;
  }
  
  public static boolean PrintHWLogReport() throws IOException
  {
	  int i=0;
	  String r_type="R_OK";
	  
	  if(is_factory)
	  {
		  //Log.i(TAG,"Factory mode");
		  return true;
	  }
	  
	  // Create a mDeviceId folder 
	  	for(i=0; i<ITEM_LIST.size();i++)
	  	{
	  		if(ITEM_LIST.get(i).equals(ITEM_R_FAIL))
	  		{
	  			r_type="R_FAIL";
	  			break;
	  		}
	  		else if(ITEM_LIST.get(i).equals(ITEM_R))
	  		{
	  			r_type="R_Not_Test";
	  			break;	
	  		}
	  	}
	  	String r_file=LogfileEx +r_type +"/" + mDeviceId+strHWTestReport;
	  	Log.i(TAG,"PrintHWLogReport -->"+r_file);
      	File myTempFile = new File(r_file);
      	myTempFile.mkdir();
	  	fos_rep = new FileOutputStream(myTempFile);
	  	for(i=0; i<ITEM_LIST.size();i++)
	  	{
	  	
/*		  byte[] buf = null;
		  	buf = ITEM_LIST.get(i).getBytes();*/
			fos_rep.write(ITEM_LIST.get(i).getBytes("utf-8"));
			fos_rep.write(new String(" Item =").getBytes("utf-8"));
			fos_rep.write(ITEM_R_LIST.get(i).getBytes("utf-8"));
			fos_rep.write(new String("\n").getBytes("utf-8"));
	  	
  		}
  		return true;
}

  @Override
  public void onStart(Intent intent, int startId)
  {
    //objHandler.postDelayed(mTasks, intUpdateSecond);
    super.onStart(intent, startId);
    
	  if(is_factory)
	  {
		  return ;
	  }
    
    try {
	      File myTempFile = new File(LogfileEx + "LOG/"+mDeviceId+strHWTestLog);
		  fos_log = new FileOutputStream(myTempFile);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
    
  }

  @Override
  public void onCreate()
  {
    super.onCreate();
    
    ITEM_LIST = new ArrayList<String>();
    ITEM_R_LIST = new ArrayList<String>();
    int i;
    for(i=0; i<ITEM_TABLES.length; i++)
    {
    	ITEM_LIST.add(ITEM_TABLES[i]);
    	ITEM_R_LIST.add(ITEM_R);
    }
  }
  
  @Override
  public IBinder onBind(Intent intent)
  {
    return null;
  }

  @Override
  public void onDestroy()
  {
    //objHandler.removeCallbacks(mTasks);
    super.onDestroy();
  }
}