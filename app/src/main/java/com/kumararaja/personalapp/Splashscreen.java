package com.kumararaja.personalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class Splashscreen extends Activity {
    private ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        pb=(ProgressBar)findViewById(R.id.pb);

        new Thread(new Runnable(){
            @Override
            public void run(){
                doWork();
                startApp();
                finish();
            }
        }).start();
    }
    private void doWork(){
        for (int progress=0;progress<100;progress+=20){
            try {
                Thread.sleep(100);
                pb.setProgress(progress);
            } catch (Exception e){
                e.printStackTrace();
                Log.e("Check",e.getMessage());
            }
        }
    }

    private void startApp(){
        Intent i=new Intent(Splashscreen.this,MainActivity.class);
        startActivity(i);
    }
}
