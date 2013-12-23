package com.example.touchit;


import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{

	GameBoard v;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		v = new GameBoard(this);
		v.setOnTouchListener(v);
		setContentView(v);
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		v.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		v.resume();
		
	}

	
	
	
	
	
	
	
	
	
}
