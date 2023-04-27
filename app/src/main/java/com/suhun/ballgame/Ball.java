package com.suhun.ballgame;

import java.util.Random;
import java.util.TimerTask;

public class Ball extends TimerTask {
    private int viewW, viewH, ballW, ballH, dX, dY, showWhichBall;
    private float ballX, ballY;
    public Ball(int viewW, int viewH, int ballW, int ballH, float ballX, float ballY){
        this.viewW = viewW; this.viewH = viewH;
        this.ballW = ballW; this.ballH = ballH;
        this.ballX = ballX; this.ballY = ballY;
        setWhichBall();
        setDxDy();
    }

    public int getWhichBall(){
        return this.showWhichBall;
    }

    public void setWhichBall(){
        this.showWhichBall = new Random().nextInt(4);
    }

    public float getBallX(){
        return this.ballX;
    }

    public float getBallY(){
        return this.ballY;
    }

    private void setDxDy(){
        //產生正負號,如果隨機數字0正號,如果隨機數字1負號
        int plus_minus_sign = (((new Random().nextInt(2))==0)?1:-1);
        int distance = new Random().nextInt(16) + 1;
        this.dX = this.dY = plus_minus_sign * distance;
    }
    @Override
    public void run() {
        if(ballX < 0 || ballX + ballW > viewW){
            dX *= -1;
        }else if(ballY < 0 || ballY + ballH > viewH){
            dY *= -1;
        }
        ballX += dX;
        ballY += dY;
    }
}
