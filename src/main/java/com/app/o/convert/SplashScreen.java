package com.app.o.convert;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.app.o.convert.R;


public class SplashScreen extends Activity {
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(300);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                //CheckFirst RUN
                    prefs = getSharedPreferences("com.app.o.convert", MODE_PRIVATE);
                    if (prefs.getBoolean("firstrun", true)) {
                        Intent openMainActivity = new Intent(SplashScreen.this, accountActivity.class);
                        startActivity(openMainActivity);
                    }else {
                        Intent openMainActivity = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(openMainActivity);
                    }

                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
