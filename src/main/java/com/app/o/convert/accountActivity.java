package com.app.o.convert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class accountActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;

    //Patient patient = new Patient();
    private DAO dbHelper;
    EditText nameEditText;
    EditText genderEditText;
    EditText ageEditText;
    EditText emailEditText;
    private static final String name = "nameVaue";

    Button saveButton;
    LinearLayout buttonLayout;
    Button editButton;

    int USERID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("Prefs", MODE_PRIVATE);
        //SharedPreferences.Editor editor = prefs.edit();
        USERID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);
        dbHelper = new DAO(getBaseContext());
        try {
            String destPath = "/data/data" + getPackageName()
                    + "/mymediacare";
            File f = new File(destPath);
            if (!f.exists()) {
                CopyDB(getBaseContext().getAssets().open("results"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Name = prefs.getString("PatientName", "Name");

        setContentView(R.layout.content_account);
        nameEditText = (EditText) findViewById(R.id.editTextName);
        genderEditText = (EditText) findViewById(R.id.editTextGender);
        ageEditText = (EditText) findViewById(R.id.editTextAge);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        nameEditText.setText(Name.toString());
        //Buttons
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        dbHelper = new DAO(this);
        if (USERID == 1) {
            saveButton.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);
//                Cursor rs = dbHelper.(USERID);
//                rs.moveToFirst();
//                String patientName = rs.getString(rs.getColumnIndex(DAO.PATIENT_COLUMN_NAME));
//                String patientGender = rs.getString(rs.getColumnIndex(DAO.PATIENT_COLUMN_GENDER));
//                int personAge = rs.getInt(rs.getColumnIndex(DAO.PATIENT_COLUMN_AGE));
//                nameEditText.setText(rs.getString(rs.getColumnIndex(DAO.PATIENT_COLUMN_NAME)));
//
//                if (!rs.isClosed()) {
//                    rs.close();
//                }
            nameEditText.setFocusable(false);
            nameEditText.setClickable(false);
            //dbHelper.getPatient(
           // nameEditText.setText(prefs.getString("PatientName", "d"));
            //editor.putString(nameEditText, nameEditText.)
            //editor.putString(name, String.valueOf(nameEditText.getText()));

            genderEditText.setText(prefs.getString("PatientName", "default"));
            genderEditText.setFocusable(false);
            genderEditText.setClickable(false);
//
            ///              ageEditText.setText(personAge + "");
            ageEditText.setFocusable(false);
            ageEditText.setClickable(false);
        }

    }

    public void CopyDB(InputStream inputstream, OutputStream outputstream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputstream.read(buffer)) < 0) {
            outputstream.write(buffer, 0, length);

        }
        inputstream.close();
        outputstream.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                persistPatient();
                prefs.edit().putBoolean("firstrun", false).commit();
                return;

        }
    }

    public void persistPatient() {
        prefs.edit().putString("PatientName", (nameEditText).toString()).commit();
        prefs.edit().putString("PatientAge", String.valueOf(ageEditText).toString()).commit();
//        prefs.edit().putString("PatientGender", String.valueOf(genderEditText).toString()).commit();
//        prefs.edit().putString("PatientEmail", String.valueOf(emailEditText).toString()).commit();
    }

    @Override
    public void onBackPressed() {

        Intent openMainActivity = new Intent(accountActivity.this, MainActivity.class);
        startActivity(openMainActivity);

    }
    }
