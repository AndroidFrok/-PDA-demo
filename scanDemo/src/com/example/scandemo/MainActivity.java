package com.example.scandemo;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	 private EditText mbarcode;//
	 private EditText mbarcode1;//
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbarcode = (EditText)findViewById(R.id.ed_barcode); 
		mbarcode1 = (EditText)findViewById(R.id.ed_barcode2); 
		IntentFilter S80BarCodeCheckFilter = new IntentFilter("com.rscja.android.DATA_RESULT");
		registerReceiver(m_S80BarCodeCheckReciever,S80BarCodeCheckFilter);
		mbarcode.setOnKeyListener(new EditText.OnKeyListener()  
	    {  
	      @Override  
	      public boolean onKey(View arg0, int arg1, KeyEvent arg2)  
	      {  
	    	    
	    	      int bb;
	    	      String aa;
	    	      bb=arg1;
	    	      bb=arg2.getKeyCode();
	    	      String dd=Integer.toString(bb);
	    	      Log.d("getKeyCode",dd);
	    	      if(arg2.getKeyCode()==KeyEvent.KEYCODE_ENTER)
	    	      {
	    	    	  bb=KeyEvent.KEYCODE_F10;
	    	    	
	    	    	  aa=mbarcode.getText().toString();
	    	    	  mbarcode1.setText(aa);
	    	    	  
	    	      }
                  return false;  
	    	      
	      }  
	    });  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private BroadcastReceiver m_S80BarCodeCheckReciever = new  BroadcastReceiver () {

		@Override
		public void onReceive(Context context, Intent intent) {

		String intent_data=intent.getStringExtra("data"); //intent_data就是条码内容
		mbarcode.setText(intent_data);
		  Log.d("mbarcode",intent_data);
		}
     };
		    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
