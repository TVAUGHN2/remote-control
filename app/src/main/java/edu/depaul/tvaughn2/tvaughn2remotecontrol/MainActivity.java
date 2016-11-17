package edu.depaul.tvaughn2.tvaughn2remotecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity
    implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";

    //initializes singleton for favorite channels
    //Fox, ESPN, and CBS channel numbers are initial values
    final FavChannel fav_channels = FavChannel.getInsstance();

    //setting request code so main activity knows
    //to receive favorite channel request
    private static final int SET_FAVS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_control);

        //setting listener for power switch
        Switch power_switch = (Switch) findViewById(R.id.power_switch);
        power_switch.setOnCheckedChangeListener(this);

        //setting listener for seek bar
        final TextView volume_text = (TextView) findViewById(R.id.speaker_vol);
        SeekBar volume_seek = (SeekBar) findViewById(R.id.volume_seek);
        volume_seek.setOnSeekBarChangeListener(
           new SeekBar.OnSeekBarChangeListener(){
           public void onProgressChanged(SeekBar seekBar,
                                        int i, boolean b){
             Log.d(TAG, "onProgressChanged");
             volume_text.setText(""+i);
           }

           public void onStartTrackingTouch(SeekBar seekBar){}
           public void onStopTrackingTouch(SeekBar seekBar){}

        });

        //setting listener for buttons
        final TextView tv_ch = (TextView) findViewById(R.id.cur_ch);
        View.OnClickListener listener = new View.OnClickListener(){

            String current_ch_input = "";

            public void onClick(View v) {
                String input = ((Button) v).getText().toString();
                String output = tv_ch.getText().toString();
                Integer ch_num = Integer.parseInt(output);


                switch (v.getId()){
                    case R.id.ch_minus:
                        if (output.equals("001")){
                            tv_ch.setText("999");
                        }
                        else{
                            tv_ch.setText(String.format("%03d", (ch_num - 1)));
                        }
                        break;
                    case R.id.ch_plus:
                        if (output.equals("999")){
                            tv_ch.setText("001");
                        }
                        else{
                            tv_ch.setText(String.format("%03d", (ch_num + 1)));
                        }
                        break;
                    case R.id.fav1:
                        tv_ch.setText(fav_channels.getFav_ch1());
                        break;
                    case R.id.fav2:
                        tv_ch.setText(fav_channels.getFav_ch2());
                        break;
                    case R.id.fav3:
                        tv_ch.setText(fav_channels.getFav_ch3());
                        break;
                    case R.id.switch_dvr:
                        Intent dvr_intent =
                                new Intent(MainActivity.this,DVR.class);
                        startActivity(dvr_intent);
                        break;
                    case R.id.switch_config:
                        Intent config_intent =
                                new Intent(MainActivity.this,Config.class);
                        startActivityForResult(config_intent, SET_FAVS);
                        break;
                    default: //append channel output for each number
                        current_ch_input += input;
                        if(current_ch_input.length() > 2){
                            if (current_ch_input.equals("000")){
                                current_ch_input = "";
                            }
                            else{
                                tv_ch.setText(current_ch_input);
                                current_ch_input = "";
                            }
                        }
                        break;
                }
            }
        };


        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btn_ch_minus = (Button) findViewById(R.id.ch_minus);
        Button btn_ch_plus = (Button) findViewById(R.id.ch_plus);
        Button btn_fav1 = (Button) findViewById(R.id.fav1);
        Button btn_fav2 = (Button) findViewById(R.id.fav2);
        Button btn_fav3 = (Button) findViewById(R.id.fav3);
        Button btn_to_dvr = (Button) findViewById(R.id.switch_dvr);
        Button btn_to_config = (Button) findViewById(R.id.switch_config);

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btn_ch_minus.setOnClickListener(listener);
        btn_ch_plus.setOnClickListener(listener);
        btn_fav1.setOnClickListener(listener);
        btn_fav2.setOnClickListener(listener);
        btn_fav3.setOnClickListener(listener);
        btn_to_dvr.setOnClickListener(listener);
        btn_to_config.setOnClickListener(listener);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //if button is power switch
        TextView tv_pow= (TextView) findViewById(R.id.tv_pow);
        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btn_ch_minus = (Button) findViewById(R.id.ch_minus);
        Button btn_ch_plus = (Button) findViewById(R.id.ch_plus);
        Button btn_fav1 = (Button) findViewById(R.id.fav1);
        Button btn_fav2 = (Button) findViewById(R.id.fav2);
        Button btn_fav3 = (Button) findViewById(R.id.fav3);
        SeekBar volume_seek = (SeekBar) findViewById(R.id.volume_seek);


        if (isChecked == true){
            //enable buttons
            //switch power button on in my tv
            btn0.setEnabled(true);
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            btn4.setEnabled(true);
            btn5.setEnabled(true);
            btn6.setEnabled(true);
            btn7.setEnabled(true);
            btn8.setEnabled(true);
            btn9.setEnabled(true);
            btn_ch_minus.setEnabled(true);
            btn_ch_plus.setEnabled(true);
            btn_fav1.setEnabled(true);
            btn_fav2.setEnabled(true);
            btn_fav3.setEnabled(true);
            volume_seek.setEnabled(true);

            tv_pow.setText("On");
        }
        else{
            //disable buttons
            //switch power button off in my tv
            btn0.setEnabled(false);
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
            btn5.setEnabled(false);
            btn6.setEnabled(false);
            btn7.setEnabled(false);
            btn8.setEnabled(false);
            btn9.setEnabled(false);
            btn_ch_minus.setEnabled(false);
            btn_ch_plus.setEnabled(false);
            btn_fav1.setEnabled(false);
            btn_fav2.setEnabled(false);
            btn_fav3.setEnabled(false);
            volume_seek.setEnabled(false);

            tv_pow.setText("Off");
        }

        Log.d(TAG, "OnCheckedChanged() " + buttonView.getTag() + " " + (isChecked ? "on" : "off"));

    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG,"On Start in main");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == SET_FAVS) {
            if (resultCode == RESULT_OK) {
                String fav = data.getCharSequenceExtra("Fav_Position").toString();
                String label = data.getCharSequenceExtra("Label").toString();
                String ch_num = data.getCharSequenceExtra("Ch_Num").toString();
                if (fav.equals("Left")) {
                    Button btn_fav = (Button) findViewById(R.id.fav1);
                    btn_fav.setText(label);
                    fav_channels.setFav_ch1(ch_num);
                } else if (fav.equals("Middle")) {
                    Button btn_fav = (Button) findViewById(R.id.fav2);
                    btn_fav.setText(label);
                    fav_channels.setFav_ch2(ch_num);
                } else {
                    Button btn_fav = (Button) findViewById(R.id.fav3);
                    btn_fav.setText(label);
                    fav_channels.setFav_ch3(ch_num);
                }
                Toast.makeText(this, "Changes saved.",
                        Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(this, "No changes made.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
