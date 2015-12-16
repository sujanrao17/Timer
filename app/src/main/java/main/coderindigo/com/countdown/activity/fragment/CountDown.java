package main.coderindigo.com.countdown.activity.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import main.coderindigo.com.countdown.R;


public class CountDown extends Fragment implements View.OnClickListener {

    View v;
    private static final String TAG = CountDown.class.getSimpleName();
    private TextView mTime;
    private FloatingActionButton startButton;
    private FloatingActionButton stopButton;

    private OnTimerStartListener mCallback;
    public CountDown() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          v = inflater.inflate(R.layout.fragment_countdown_timer, container, false);
//        ViewGroup ref = (ViewGroup)v.findViewById(R.id.countdown_fragment);
//        ref.setTag(this);
//        ref.getChildAt(0).setTag("fragment:" + 0);
        startButton = (FloatingActionButton) v.findViewById(R.id.start_button);
        mTime = (TextView) v.findViewById(R.id.time_countdown);
        stopButton = (FloatingActionButton) v.findViewById(R.id.stop_button);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        return v;
    }



    public  void updateTime(String time){

        Log.d(TAG, time);
        mTime.setText(time);
    }

    @Override
    public void onClick(View v) {
        mCallback.OnTimerStartListener(v);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnTimerStartListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public interface OnTimerStartListener {
        public void OnTimerStartListener(View position);
    }

}
