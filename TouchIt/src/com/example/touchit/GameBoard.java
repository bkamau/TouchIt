package com.example.touchit;

import java.util.Calendar;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameBoard extends SurfaceView implements Runnable,OnTouchListener{
	
	Bitmap ball,play, planet,earth;
	String s, sc, scd, conts;
	Random rnd;
	Paint paint;
	Canvas canvas;
	Thread myThread    = null;
	boolean isItOk           = false;
	SurfaceHolder holder;
	Handler mhandler;
	String timer;
	int counts;	
	int count;
	int touches = 0;
	int x,xx = 0;
	int y,yy = 0;
	
	public GameBoard(Context context) {
		super(context);
		holder = getHolder();
		mhandler = new Handler(Looper.getMainLooper());
		planet = BitmapFactory.decodeResource(getResources(), R.drawable.planet);
		earth = BitmapFactory.decodeResource(getResources(), R.drawable.myearth);
		timer = getResources().getString(R.string.timer);
	}

	@Override
	public void run() {
		
		while (isItOk == true) {
			if(!holder.getSurface().isValid()){
				continue;
			}
			
			touchCounter();
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	public void pause(){
		isItOk = false;
		while(true){
			try{
				myThread.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			break;
		}
		myThread = null;
	}	
	
	public void resume(){
		isItOk = true;
		myThread = new Thread(this);
		myThread.start();
	}
	
	public void GenRandom(){
		rnd	= new Random(Calendar.getInstance().getTimeInMillis());
		x = rnd.nextInt(getWidth() - ball.getWidth());
		y = rnd.nextInt(getHeight()- ball.getHeight());
	}

	public void touchCounter(){
		canvas = holder.lockCanvas();
	
		GenRandom();
        canvas.drawRGB(125, 100, 251);
		canvas.drawBitmap(ball, (x), (y),null);
		
		s = String.valueOf(x);
		sc = String.valueOf(xx);
		
		scd =String.valueOf(touches);
		paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        
        canvas.drawText("Counts", 520, 50, paint);
        canvas.drawText(scd, 520, 100, paint);
		
		holder.unlockCanvasAndPost(canvas);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xx = (int)event.getX();
			yy = (int)event.getY();
			
			if((xx >= x)  && xx <= (xx+ball.getWidth())){
				if((yy >= y) && y <= (yy+ball.getHeight()))
				touches ++;
			}
			xx = 0;
			yy = 0;
			break;

		default:
			break;
		}
		
		return true;
	}
	
	

	

}