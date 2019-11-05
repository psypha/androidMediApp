package com.app.o.convert;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class customActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private String prefName = "Custom";
    private static final String FONT_SIZE_KEY = "fontsize";
    private static final String TEXT_VALUE_KEY = "textvalue";
    private static final String BGCOLOUR = "colourValue";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            Button btnSave = (Button) findViewById(R.id.btnSave);

            final SeekBar seekBar = (SeekBar) findViewById(R.id.SeekBar01);
            final EditText editText = (EditText) findViewById(R.id.EditText01);
            final SeekBar seekColour = (SeekBar) findViewById(R.id.seekColour);

            btnSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    editor.commit();

                    editor.putFloat(FONT_SIZE_KEY, editText.getTextSize());
                    editor.putString(TEXT_VALUE_KEY, editText.getText().toString());
                    editor.putFloat(BGCOLOUR, seekColour.getProgress());
                    editor.commit();


                    Toast.makeText(getBaseContext(),
                            "Font size saved successfully!",
                            Toast.LENGTH_SHORT).show();
                    //
                    float fontSize = prefs.getFloat(FONT_SIZE_KEY, 12);
                    float colourValue = prefs.getFloat(BGCOLOUR, 0);

                    seekBar.setProgress((int) colourValue);
                    seekBar.setProgress((int) fontSize);

                    editText.setText(prefs.getString(TEXT_VALUE_KEY, ""));
                    editText.setTextSize(seekBar.getProgress());
                    seekBar.setBackgroundColor(seekColour.getProgress());

                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int
                                progress, boolean fromUser) {
                            prefs.edit().putString("textSize", String.valueOf(progress));
                            editText.setTextSize(progress);
                        }
                    });
                }


            });
            seekColour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekColour) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekColour) {
                }

                @Override
                public void onProgressChanged(SeekBar seekColour, int
                        progress, boolean fromUser) {
                    getWindow().getDecorView().setBackgroundColor((progress));
                }
            });
        }



}
