package com.app.o.convert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences prefs;
    private String prefName = "Prefs";
    private static final int TEMPERATURE = 0;
    private static final int HR = 0;
    private static final int BPL = 0;
    private static final int BPH = 0;
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    int HRscore;
    int BPLscore;
    int BPHscore;
    int TEMPscore;
    DAO dbHelper;
    NumberPicker NPL;
    NumberPicker NPH;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_temp);
        final TextView tempRead = (TextView) findViewById(R.id.tempRead);
        //Shared Preferences
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);

//        setContentView(R.layout.content_main);
//        SeekBar tempSeek = (SeekBar) findViewById(R.id.seek_temp);
//        ToggleButton tempToggle = (ToggleButton) findViewById(R.id.toggle_temp);
        //      MENU and Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.commit();

//        editor.putFloat(String.valueOf(NPL),NPL.getValue());
  //      editor.putFloat(String.valueOf(NPH),NPH.getValue());
        editor.putString(String.valueOf(HR), tempRead.getText().toString());
        editor.putFloat(String.valueOf(TEMPERATURE), seekBar.getProgress());

        editor.commit();

     //   editText.setText(prefs.getString(BPH, ""));
//        tempRead.setText(seekBar.getProgress());

     //   seekBar.setOnSeekBarChangeListener(seekBar.getProgress());
        seekBar.setMax(42);

        NPL = (NumberPicker) findViewById(R.id.numberLowPicker);
        NPH = (NumberPicker) findViewById(R.id.numberHighPicker);

        NPH.setMinValue(20);
        NPH.setMaxValue(240);
        NPL.setMinValue(20);
        NPL.setMaxValue(240);

        //Submit Form
        final String mNumber = "07523291879";
        final String mMessage = "TestTest Woooo!";
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float tempRead = ((float)progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button send = (Button) findViewById(R.id.btn_Submit);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreCalc();

                Log.d("WORKING", Integer.toString(HRscore));

                if(HRscore > 1 || BPLscore < 1 || BPHscore > 1 || TEMPscore > 1){
                sendSMS(mNumber.toString(), mMessage.toString());
                Toast.makeText(getBaseContext(), "SMS Sent",
                        Toast.LENGTH_SHORT).show();
                String[] to = {"22763074@go.edgehill.ac.uk"};
                String[] cc = {"22763074@go.edgehill.ac.uk"};
                sendEmail(to, cc, "subject-Hello", "main text-Hope you are ok");
                Toast.makeText(getBaseContext(), "Email Sent",
                        Toast.LENGTH_SHORT).show();


            }else if(HRscore > 2 || BPLscore < 2 || BPHscore > 2 || TEMPscore > 2){
                }
                dbHelper.insertPatientResults(BPL, BPH, TEMPscore, HRscore );

                }

        });

        dbHelper = new DAO(this);
       final List<viewResults> cursor = dbHelper.getAllResults();
        String [] columns = new String[] {
                DAO.PATIENT_COLUMN_ID,
                DAO.PATIENT_TABLE_RESULTS
        };


    }
    private void scoreCalc()
    {
        if(HR < 60 ) {
            HRscore = 0;
        }else if(HR > 160)
        {
            HRscore = 2;
        }else
        {
            HRscore = 1;
        }

        if(NPL.getValue() < 80 ) {
            BPLscore = 0;
        }else if (NPL.getValue() > 110)
        {
            BPLscore = 2;
        }else
        {
            BPLscore = 1;
        }
        if(NPH.getValue() > 120 ) {
            BPHscore = 0;
        }else if (NPH.getValue() > 180)
        {
            BPHscore = 2;
        }else
        {
            BPHscore = 1;
        }

        if(TEMPERATURE >= 37 ) {
            TEMPscore = 0;

        }else if (TEMPERATURE >= 38)
        {
            TEMPscore = 2;
        }else
        {
            TEMPscore = 1;
        }}
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    private  void  sendEmail(String[] emailAdresses, String[] carbonCopies, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        String[] from = {"22763074@go.edgehill.ac.uk"};
        String[] to =  {"22763074@go.edgehill.ac.uk"};
        String[] cc = carbonCopies;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }
    private void sendSMS(String phoneNumber, String message ){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.drawer_layout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            startActivity(new Intent(MainActivity.this, accountActivity.class));
        } else if (id == R.id.nav_contact) {
            startActivity(new Intent(MainActivity.this, contactActivity.class));
        } else if (id == R.id.nav_restults) {
            startActivity(new Intent(MainActivity.this, viewResults.class));

        } else if (id == R.id.nav_custom) {
            startActivity(new Intent(MainActivity.this, customActivity.class));

        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, aboutActivity.class));

        } else if (id == R.id.nav_help) {
            startActivity(new Intent(MainActivity.this, helpActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
