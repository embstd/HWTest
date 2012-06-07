/*
 * Copyright (C) 2008 The Android Open Source Project
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
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;


public class Testing extends PreferenceActivity {

    //private static final String KEY_PARENT = "parent";
    private Intent mIntent = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.testings);
        
        //PreferenceGroup parent = (PreferenceGroup) findPreference(KEY_PARENT);
        //Utils.updatePreferenceToSpecificActivityOrRemove(this, parent, KEY_SYNC_SETTINGS, 0);
        //Utils.updatePreferenceToSpecificActivityOrRemove(this, parent, KEY_SEARCH_SETTINGS, 0);

    }
    
    @Override
    protected void onResume() {
        super.onResume();
        //findPreference(KEY_CALL_SETTINGS).setEnabled(!AirplaneModeEnabler.isAirplaneModeOn(this));
    }
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	
        mIntent = new Intent(Testing.this, HWTEST.class);
        startActivity(mIntent);
    }

}
