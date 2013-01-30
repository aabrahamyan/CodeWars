package com.skybot.activities;

import android.app.Activity;
import android.os.Bundle;


/**
 * Activity lifecycle for Skybot Agents
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class AgentActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_layout);
    }  
}


