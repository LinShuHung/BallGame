package com.suhun.ballgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class BallGameView extends View {
    public String tag = BallGameView.class.getSimpleName();
    private Resources res;
    private int viewW, viewH, ballW, ballH;
    private int[] bmpBallResId = {R.drawable.ball0, R.drawable.ball1, R.drawable.ball2, R.drawable.ball3};
    private Bitmap[] bmpBalls = new Bitmap[bmpBallResId.length];
    private boolean isInitBallView;
    private Timer timer = new Timer();
    private RefreshTask refreshTask = new RefreshTask();

    //inner class
    private class RefreshTask extends TimerTask{
        @Override
        public void run() {
            postInvalidate();
        }
    }

    public BallGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.ballgame_bg);
        res = context.getResources();
    }

    private void initBallView(){
        this.viewW = getWidth(); this.viewH = getHeight();
        setBallWH(this.viewW);
        isInitBallView = true;
        for(int i=0;i<bmpBalls.length;i++){
            bmpBalls[i] = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(res, bmpBallResId[i])
                    , ballW, ballH, false);
        }
        timer.schedule(refreshTask, 0, 30);
    }

    private void setBallWH(int viewW){
        ballW = (int)(viewW / 12.0);
        ballH = ballW;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInitBallView){
            initBallView();
            isInitBallView = true;
        }
        canvas.drawBitmap(bmpBalls[3], 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
