package com.adobe.busbooking;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adobe.marketing.mobile.MobileCore;

public class AppCrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_crash);
        //setContentView(R.layout.content_app_crash);
        setUpToolBar();
        TextView countdown = (TextView)findViewById(R.id.txtCountDown);

        //Analytics Tracking
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);
       // MobileCore.trackState("CrashActivityView", null);

        new CountDownTimer(5000, 1000) {
            TextView countdown = (TextView)findViewById(R.id.txtCountDown);

            public void onTick(long millisUntilFinished) {
                countdown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //countdown.setText("Crash!");
                System.out.println(2/0);
            }
        }.start();
    }



    private void setUpToolBar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

        toolbar.setTitle("Crash Activity");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);
        MobileCore.trackState("BusBookingActivity_Resume", null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobileCore.lifecyclePause();
    }



}