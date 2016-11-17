package edu.depaul.tvaughn2.tvaughn2remotecontrol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import static android.widget.Toast.*;

/**
 * Created by Travis on 10/21/2016.
 */

public class DVR extends Activity
        implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dvr_control);

        //setting listener for power switch
        Switch power_switch = (Switch) findViewById(R.id.dvr_power_switch);
        power_switch.setOnCheckedChangeListener(this);


        //setting listener for buttons
        final TextView state = (TextView) findViewById(R.id.state);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                String current_state = state.getText().toString();
                switch (v.getId()) {
                    case R.id.play:
                        if (current_state.equals("Playing")){
                            Toast.makeText(getApplicationContext(),
                                    "It is already Playing.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else if (!current_state.equals("Recording")) {
                            state.setText("Playing");
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Playing is not possible while Recording.",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.stop:
                        if(current_state.equals("Stopped")) {
                            Toast.makeText(getApplicationContext(),
                                    "It is already Stopped.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            state.setText("Stopped");
                        }
                        break;
                    case R.id.pause:
                        if (current_state.equals("Playing")) {
                            state.setText("Paused");
                        }
                        else if (current_state.equals("Paused")){
                            Toast.makeText(getApplicationContext(),
                                    "It is already Paused.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "You may only Pause if the DVR is Playing.",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.ff:
                        if (current_state.equals("Playing")) {
                            state.setText("Fast forwarding");
                        }
                        else if (current_state.equals("Fast forwarding")){
                            Toast.makeText(getApplicationContext(),
                                    "It is already Fast forwarding.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "You may only Fast forward if the DVR is Playing.",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.frwd:
                        if (current_state.equals("Playing")) {
                            state.setText("Fast rewinding");
                        }
                        else if (current_state.equals("Fast rewinding")){
                            Toast.makeText(getApplicationContext(),
                                    "It is already Fast rewinding.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "You may only Fast rewind if the DVR is Playing.",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.record:
                        if (current_state.equals("Stopped")) {
                            state.setText("Recording");
                        }
                        else if (current_state.equals("Recording")){
                            Toast.makeText(getApplicationContext(),
                                    "It is already Recording.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "You may only Record if the DVR is Stopped.",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.switch_tv:
                        setResult(RESULT_CANCELED);
                        finish();
                        break;
                }
            }
        };


        Button btn_play = (Button) findViewById(R.id.play);
        Button btn_stop = (Button) findViewById(R.id.stop);
        Button btn_pause = (Button) findViewById(R.id.pause);
        Button btn_ff = (Button) findViewById(R.id.ff);
        Button btn_frwd = (Button) findViewById(R.id.frwd);
        Button btn_record = (Button) findViewById(R.id.record);
        Button btn_to_tv = (Button) findViewById(R.id.switch_tv);


        btn_play.setOnClickListener(listener);
        btn_stop.setOnClickListener(listener);
        btn_pause.setOnClickListener(listener);
        btn_ff.setOnClickListener(listener);
        btn_frwd.setOnClickListener(listener);
        btn_record.setOnClickListener(listener);
        btn_to_tv.setOnClickListener(listener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //if button is power switch
        TextView dvr_pow = (TextView) findViewById(R.id.dvr_pow);
        TextView state = (TextView) findViewById(R.id.state);
        Button btn_play = (Button) findViewById(R.id.play);
        Button btn_stop = (Button) findViewById(R.id.stop);
        Button btn_pause = (Button) findViewById(R.id.pause);
        Button btn_ff = (Button) findViewById(R.id.ff);
        Button btn_frwd = (Button) findViewById(R.id.frwd);
        Button btn_record = (Button) findViewById(R.id.record);
        Button btn_to_tv = (Button) findViewById(R.id.switch_tv);


        if (isChecked == true) {
            //enable buttons
            //switch power button on in my tv
            btn_play.setEnabled(true);
            btn_stop.setEnabled(true);
            btn_pause.setEnabled(true);
            btn_ff.setEnabled(true);
            btn_frwd.setEnabled(true);
            btn_record.setEnabled(true);
            btn_to_tv.setEnabled(true);

            dvr_pow.setText("On");
            state.setText("Stopped");
        } else {
            //disable buttons
            //switch power button off in my tv
            btn_play.setEnabled(false);
            btn_stop.setEnabled(false);
            btn_pause.setEnabled(false);
            btn_ff.setEnabled(false);
            btn_frwd.setEnabled(false);
            btn_record.setEnabled(false);
            btn_to_tv.setEnabled(false);

            dvr_pow.setText("Off");
        }

        Log.d(TAG, "OnCheckedChanged() " + buttonView.getTag() + " " + (isChecked ? "on" : "off"));

    }



}
