package com.cave.r.dalton.shakepractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dalton on 3/21/16.
 */
public class View extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread mainThread;
    private String message;

    public View(Context context){
        super(context);
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(), this);
        message = "0 Shakes";
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //Do Nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                mainThread.join();
                retry = false;

            }
            catch(InterruptedException e) {
                //retry
            }
        }
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        if (paint != null){
            canvas.drawPaint(paint);
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText(message, 200, 200, paint);
    }

    public void update(String message){
        this.message = message;
    }
}
