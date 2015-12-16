package main.coderindigo.com.countdown.activity.helper;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

/**
 * Created by sujana on 12/14/2015.
 */
public abstract class CountdownTimer  implements Runnable {

    private static final String TAG = CountdownTimer.class.getSimpleName();
    private long timeRemaining;
    private Handler handler;
    private boolean isKilled = false;

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

    public void start() {
        isKilled = false;
        handler.postDelayed(this, 1000);
    }

    public void stop() {
        isKilled = true;
        onTimerStopped();
    }


    @Override
    public void run() {
        if (!isKilled) {
            updateUI(timeRemaining);
            timeRemaining = timeRemaining - 1000;
            if (timeRemaining == 30000){
                Log.d(TAG, timeRemaining +"");
                onPlayNotification();
            }

            if (timeRemaining >= 0) {

                Log.d(TAG, "timer nunn");
                handler.postDelayed(this, 1000);
            } else {
                onTimerFinished();
            }
        }

    }

    public abstract void onPlayNotification();

    public abstract void onTimerFinished();

    public abstract void onTimerStopped();

    public static boolean isValid(String input) {
        if ((input == null) || input.isEmpty()) {
            return false;
        }
        if (input.length() == 5 && input.indexOf(":") == 2) {
            try {
                long totalDuration = covertTomili(input);
                boolean valid = (totalDuration > 30);
                return valid;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static long getMin(String input) throws NumberFormatException {

        int min = Integer.parseInt(input.substring(0, 2));
        return min;

    }

    public static long getSec(String input) throws NumberFormatException {

        int seconds = Integer.parseInt(input.substring(3, input.length()));
        return seconds;

    }

    public static long covertTomili(String input) {
        try {

            long millisec =  ((getMin(input) * 60 + getSec(input)) * 1000);
            return millisec;
        } catch (NumberFormatException e) {
            //invalid number
            return 0;
        }
    }

    public static String covertToString(long input) {
        int totalsec = (int) input / 1000;
        int min = totalsec / 60;
        int sec = totalsec % 60;
        String minutesString = (min < 10) ? "0" + min : min + "";
        String secString = (sec < 10) ? "0" + sec : sec + "";
        return minutesString + ":" + secString;

    }


    public abstract void updateUI(long time);
}
