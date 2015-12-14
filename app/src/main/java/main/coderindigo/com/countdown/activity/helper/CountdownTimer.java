package main.coderindigo.com.countdown.activity.helper;

import android.os.Handler;
import android.util.Log;

/**
 * Created by sujana on 12/14/2015.
 */
public abstract  class CountdownTimer implements Runnable {

    private static final String TAG = CountdownTimer.class.getSimpleName();
    private long timeRemaining;
    private Handler handler;

    public CountdownTimer(Handler handler) {
        this.handler = handler;
    }

    public CountdownTimer(Handler handler, long timeRemaining) {
        this.handler = handler;
        this.timeRemaining = timeRemaining;

    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void start(){
        handler.postDelayed(this, 1000);
    }
    @Override
    public void run() {

        updateUI(timeRemaining);
        timeRemaining = timeRemaining - 1000;
        if (timeRemaining >= 0) {
            Log.d(TAG , "timer nunn");
            handler.postDelayed(this, 1000);
        }
    }

    public abstract void updateUI(long time);
}
