package com.cave.r.dalton.shakepractice;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by dalton on 3/21/16.
 */
public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private View view;

    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, View view){
        super();
        this.surfaceHolder = surfaceHolder;
        this.view = view;
    }

    @Override
    public void run(){
        Canvas canvas;

        while(running){
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.view.render(canvas);

                }
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                //Do nothing!
            }
            finally{
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public boolean getRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }
}
