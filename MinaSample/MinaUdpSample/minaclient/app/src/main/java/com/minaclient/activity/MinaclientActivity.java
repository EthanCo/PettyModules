package com.minaclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.minaclient.util.MemMonClient;

public class MinaclientActivity extends Activity {
	
	private Button myButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.setProperty("java.net.preferIPv6Addresses", "false"); 
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myButton = (Button)findViewById(R.id.myButton);
        myButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 发送客户端数据
				new MemMonClient();
			}
		});
    }
}