package com.example.hspcadmin.htmlproject.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时工具类
 *
 *
 * Created by wzheng on 2018/12/3.
 */

public class TimeCountDown {
    private Timer timer;
    private TimeCountFace face;
    /**
     * handle what
     * */
    public static int HANDLE_WHAT_STAR = 1;
    public static int HANDLE_WHAT_END = 999;

    public TimeCountDown init(TimeCountFace face){
        this.face = face;
        timer = new Timer();
        return this;
    }

    /**
     *@param period :  延时  1000 - 1s
     *
     * */
    public void star(int period){
        try{
            timer.schedule(timerTask,0,period);
        }catch (Exception e){}
    }

    public void cancel(){
        timer.cancel();
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            face.setMessage();
        }
    };

    public interface TimeCountFace{
        void setMessage();
    }
}
