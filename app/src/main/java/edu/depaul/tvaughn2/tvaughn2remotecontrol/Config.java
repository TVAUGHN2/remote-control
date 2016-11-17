package edu.depaul.tvaughn2.tvaughn2remotecontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Config extends Activity
        implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_fav_ch);

        //setting listener for radio buttons
        final RadioGroup fav_radiogroup = (RadioGroup)findViewById(R.id.fav_ch_radiogroup);
        RadioButton left_radio = (RadioButton) findViewById(R.id.radio_left);
        RadioButton middle_radio = (RadioButton) findViewById(R.id.radio_middle);
        RadioButton right_radio = (RadioButton) findViewById(R.id.radio_right);

        left_radio.setOnCheckedChangeListener(this);
        middle_radio.setOnCheckedChangeListener(this);
        right_radio.setOnCheckedChangeListener(this);

        //setting listener for label text view
        final TextView new_label = (TextView) findViewById(R.id.fav_ch_name);
        new_label.setOnEditorActionListener(
                new TextView.OnEditorActionListener(){
                    public boolean onEditorAction(TextView v, int actionID,
                                                  KeyEvent event){
                        boolean handled = false;
                        if(actionID == EditorInfo.IME_ACTION_NEXT){
                            //find the index of the text item field (if it exists)
                            int label_length = v.getText().toString().length();
                            if (label_length < 2 || label_length > 4){
                                Toast.makeText(getApplicationContext(),
                                        "Your label must be between 2 and 4 characters.",
                                        Toast.LENGTH_LONG).show();
                            }

                            InputMethodManager imm = (InputMethodManager)
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


                            handled = true;
                        }
                        return handled;
                    }
                }
        );

        //setting listener for buttons
        final TextView fav_ch_label = (TextView) findViewById(R.id.label);
        View.OnClickListener listener = new View.OnClickListener(){


            public void onClick(View v) {
                String input = ((Button) v).getText().toString();
                String output = fav_ch_label.getText().toString();
                Integer ch_num = Integer.parseInt(output);

                switch (v.getId()){
                    case R.id.ch_minus:
                        if (output.equals("1") || output.equals("0")){
                            fav_ch_label.setText("999");
                        }
                        else{
                            fav_ch_label.setText(String.format("%d", (ch_num - 1)));
                        }
                        break;
                    case R.id.ch_plus:
                        if (output.equals("999")){
                            fav_ch_label.setText("1");
                        }
                        else{
                            fav_ch_label.setText(String.format("%d", (ch_num + 1)));
                        }
                        break;
                    case R.id.clear:
                        fav_ch_label.setText("0");
                        break;
                    case R.id.cancel:
                        setResult(RESULT_CANCELED);
                        finish();
                        break;
                    case R.id.save:
                        if(fav_radiogroup.getCheckedRadioButtonId() == -1){
                            Toast.makeText(getApplicationContext(),
                                    "You must check a favorite channel button.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else if (new_label.getText().length() < 2 || new_label.getText().length() > 4){
                            Toast.makeText(getApplicationContext(),
                                    "Your label must be between 2 and 4 characters.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else if(fav_ch_label.getText().equals("0")){
                            Toast.makeText(getApplicationContext(),
                                    "You must enter a valid channel.",
                                    Toast.LENGTH_LONG).show();
                        }
                        //send data to main activity
                        else{
                            Intent save_data = new Intent();
                            RadioButton fav = (RadioButton)
                                    findViewById(fav_radiogroup.getCheckedRadioButtonId());
                            save_data.putExtra("Fav_Position",  fav.getText());
                            save_data.putExtra("Label", new_label.getText());
                            String new_ch = fav_ch_label.getText().toString();
                            //add leading zeros if necessary
                            if (new_ch.length() == 2){
                                new_ch = "0" + new_ch;
                            }
                            else if(new_ch.length() == 1){
                                new_ch = "00" + new_ch;
                            }
                            save_data.putExtra("Ch_Num", new_ch);
                            setResult(RESULT_OK, save_data);
                            finish();
                        }
                        break;
                    default: //append channel output for each number
                        if (output.equals("0") || output.length() == 3){
                            output = "";
                        }
                        output += input;
                        fav_ch_label.setText(output);
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
        Button btn_cancel = (Button) findViewById(R.id.cancel);
        Button btn_save = (Button) findViewById(R.id.save);
        Button btn_clear = (Button) findViewById(R.id.clear);


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
        btn_cancel.setOnClickListener(listener);
        btn_save.setOnClickListener(listener);
        btn_clear.setOnClickListener(listener);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //if button is power switch
        int button_id = ((Button) buttonView).getId();
        RadioButton inputRadio= (RadioButton) findViewById(button_id);
        RadioGroup radio_group = (RadioGroup) findViewById(R.id.fav_ch_radiogroup);
        RadioButton radio_left = (RadioButton) findViewById(R.id.radio_left);
        RadioButton radio_middle = (RadioButton) findViewById(R.id.radio_middle);
        RadioButton radio_right = (RadioButton) findViewById(R.id.radio_right);

        if (isChecked) {
            if (inputRadio.equals(radio_left)) {
                radio_left.setChecked(true);
                radio_middle.setChecked(false);
                radio_right.setChecked(false);
            } else if (inputRadio.equals(radio_middle)) {
                radio_middle.setChecked(true);
                radio_left.setChecked(false);
                radio_right.setChecked(false);

            } else if (inputRadio.equals(radio_right)) {
                radio_right.setChecked(true);
                radio_middle.setChecked(false);
                radio_left.setChecked(false);

            } else {
                Toast.makeText(getApplicationContext(),
                        "Error in Radio Button Logic.",
                        Toast.LENGTH_LONG).show();

            }
        }

        Log.d(TAG, "OnCheckedChanged() " + buttonView.getTag() + " " + (isChecked ? "on" : "off"));

    }



}
