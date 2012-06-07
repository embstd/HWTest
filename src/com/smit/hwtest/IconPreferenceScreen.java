/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smit.hwtest;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class IconPreferenceScreen extends Preference {
	
	private static String TAG = "IconPreferenceScreen";

    private Drawable mIcon;
    private Drawable mIcon_Ok;
    private Drawable mIcon_Fail;
    
    Context mcontext;

    public IconPreferenceScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconPreferenceScreen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutResource(R.layout.preference_icon);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.IconPreferenceScreen, defStyle, 0);
        mIcon = a.getDrawable(R.styleable.IconPreferenceScreen_icon);
        //Log.i(TAG,"IconPreferenceScreen==========");
        
        Resources resources = getContext().getResources();
        mIcon_Ok= resources.getDrawable(R.drawable.icon_test_ok);
        mIcon_Fail= resources.getDrawable(R.drawable.icon_test_fail);
        
        //Log.i(TAG,"onBindView -- "+getTitle()+" ++ setSummary="+getSummary());
        String item=getTitle().toString();
        String ret=HWLog.Check_HWLogTestReport(item);
        setSummary("This test item is " + ret);
        
/*        if(ret.equals(HWLog.ITEM_R_OK))
        {
        	
        }
        else if(ret.equals(HWLog.ITEM_R_FAIL))
        {
        	
        }*/
        //setTitle("sdfsdf");
    }

	@Override
    public void onBindView(View view) {
        super.onBindView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);

/*        if (imageView != null && mIcon != null) {
            imageView.setImageDrawable(mIcon);
        }*/
        
        String item=getTitle().toString();
        String ret=HWLog.Check_HWLogTestReport(item);
        
        if(ret.equals(HWLog.ITEM_R_OK))
        {
            if (imageView != null && mIcon_Ok != null) {
            	imageView.setImageDrawable(mIcon_Ok);
            }
        }
        else if(ret.equals(HWLog.ITEM_R_FAIL))
        {
            if (imageView != null && mIcon_Fail != null) {
            	imageView.setImageDrawable(mIcon_Fail);
            }
        }
        else
        {
            if (imageView != null && mIcon != null) {
                imageView.setImageDrawable(mIcon);
            }
        }
        
    }

	
}
