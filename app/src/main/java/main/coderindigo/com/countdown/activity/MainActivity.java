package main.coderindigo.com.countdown.activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import main.coderindigo.com.countdown.activity.fragment.*;


import java.io.IOException;

import main.coderindigo.com.countdown.R;
import main.coderindigo.com.countdown.activity.fragment.CountDown.OnTimerStartListener;
import main.coderindigo.com.countdown.activity.helper.CountdownTimer;
import main.coderindigo.com.countdown.activity.helper.PageAdapter;

public class MainActivity extends AppCompatActivity implements CountDown.OnTimerStartListener, Dialog.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private SeekBar seekBar_min;
    private SeekBar seekBar_sec;
    private CountDown mDisplayFragment;
    private Handler handler;
    private TextView userInputMin;
    private TextView userInputSec;
    private CountdownTimer timer;


    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View fragmentView = inflater.inflate(R.layout.content_main, null);

        //t = (TextView) fragmentView.findViewById(R.id.time);
        //textView = (TextView) findViewById(R.id.textView1);

        InitializeTabs();
        handler = new Handler();
        timer = new CountdownTimer(handler) {
            @Override
            public void onPlayNotification() {
                playSound();
            }

            @Override
            public void onTimerFinished() {
            }

            @Override
            public void onTimerStopped() {
                // mDisplayFragment.updateTime("00:00");
            }

            @Override
            public void updateUI(long time) {
//                mDisplayFragment = (CountDown)getSupportFragmentManager().findFragmentByTag(
//                        "android:switcher:"+ R.id.pager+":0");
                if (mDisplayFragment != null) {
                    Log.d(TAG, "Im inside update");
                    // mDisplayFragment.updateTime(CountdownTimer.covertToString(time));
                    update(CountdownTimer.covertToString(time));

                } else {
                    Toast.makeText(MainActivity.this, "oopppss", Toast.LENGTH_SHORT).show();
                    // Log.d(TAG, mDisplayFragment.toString());
                }

            }

        };

    }

    public void update(String time) {

        mDisplayFragment.updateTime(time);
    }

    private void playSound() {
        try {
            AssetFileDescriptor afd = getAssets().openFd("sounds/alert.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor());
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                String minutes = userInputMin.getText().toString().trim();
                Log.d(TAG, "-----------" + minutes);

                String seconds = userInputSec.getText().toString().trim();
                String input = minutes + ":" + seconds;
                Log.d(TAG, "--TIME---" + input);

                if (CountdownTimer.isValid(input)) {

                    timer.setTimeRemaining(CountdownTimer.covertTomili(input));
                    timer.start();

                }
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                break;

        }
    }

    public void InitializeTabs() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager()) {
        };
        adapter.addFragment(new CountDown(), "Countdown");
        adapter.addFragment(new TimerFragment(), "Timer");
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        mDisplayFragment = (CountDown) adapter.getFragment(0);

    }

    @Override
    public void OnTimerStartListener(View v) {


        if (v.getId() == R.id.start_button) {
            Toast.makeText(MainActivity.this, "ON", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.user_input, null);

            seekBar_min = (SeekBar) view.findViewById(R.id.seekBar_min);
            seekBar_sec = (SeekBar) view.findViewById(R.id.seekBar_sec);

            userInputMin = (TextView) view.findViewById(R.id.user_input_min);
            userInputSec = (TextView) view.findViewById(R.id.user_input_sec);
//            userInput.setText(seekBar.getProgress() );
            seekBar_min.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progress = 0;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                    progress = progresValue;
                    userInputMin.setText((progress < 10) ? "0" + progress : progress + "");
                    //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override

                public void onStopTrackingTouch(SeekBar seekBar) {
                }

            });

            seekBar_sec.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progress = 0;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                    progress = progresValue;
                    userInputSec.setText((progress < 10) ? "0" + progress : progress + "");
                    //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", this);
            builder.setNegativeButton("CANCEL", this);
            builder.show();
        } else if (v.getId() == R.id.stop_button) {
            Toast.makeText(MainActivity.this, "OFF", Toast.LENGTH_SHORT).show();
            timer.stop();
        }
    }
}


