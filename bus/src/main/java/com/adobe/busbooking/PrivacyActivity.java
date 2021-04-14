package com.adobe.busbooking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.MobilePrivacyStatus;


/*

Documentation regarding Opt-in/opt-out and unknown:
https://experienceleague.adobe.com/docs/mobile-services/android/gdpr-privacy-android/privacy.html?lang=en#gdpr-privacy-android

Latest version: https://aep-sdks.gitbook.io/docs/resources/privacy-and-gdpr - Watch Video at the end of the page
 */
public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RadioGroup group = (RadioGroup) findViewById(R.id.myRadioGroup);

        MobileCore.getPrivacyStatus(new AdobeCallback<MobilePrivacyStatus>() {
            @Override
            public void call(MobilePrivacyStatus mobilePrivacyStatus) {
                System.out.println(mobilePrivacyStatus);
                switch (mobilePrivacyStatus){
                    case OPT_IN:
                        group.check(R.id.btnOptIn);
                        break;
                    case OPT_OUT:
                        group.check(R.id.btnOptOut);
                        break;
                    case UNKNOWN:
                        group.check(R.id.btnUnknown);
                        break;
                }
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btnOptIn:
                        MobileCore.setPrivacyStatus(MobilePrivacyStatus.OPT_IN);
                        break;
                    case R.id.btnOptOut:
                        MobileCore.setPrivacyStatus(MobilePrivacyStatus.OPT_OUT);
                        break;
                    case R.id.btnUnknown:
                        MobileCore.setPrivacyStatus(MobilePrivacyStatus.UNKNOWN);
                        break;
                }
            }
        });

    }
}