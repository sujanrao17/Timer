package main.coderindigo.com.countdown.activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import main.coderindigo.com.countdown.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Dialog.OnClickListener{

    private TextView mTime;
    private Button startButton;
    private Button stopButton;
    private Handler handler;
    private long timeRemaining = 5000;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        mTime = (TextView) findViewById(R.id.time);
        startButton = (Button) findViewById(R.id.start_button);
        stopButton = (Button) findViewById(R.id.stop_button);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                timeRemaining = timeRemaining- 1000;
                if (timeRemaining>0){
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runnable, 1000);






    }
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.start_button){
            Toast.makeText(MainActivity.this, "ON", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.user_input, null);
            EditText userInput = (EditText) view.findViewById(R.id.user_input);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Time");
            builder.setView(R.layout.user_input);
            builder.setPositiveButton("OK", this);
            builder.setNegativeButton("CANCEL", this);
            builder.show();


        }else if(v.getId() == R.id.stop_button){
            Toast.makeText(MainActivity.this, "OFF", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:

                break;

            case DialogInterface.BUTTON_NEGATIVE:
                break;

        }

    }
}
