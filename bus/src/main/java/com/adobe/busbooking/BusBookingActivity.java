/*************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 *  Copyright 2018 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property
 * laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/
package com.adobe.busbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import com.adobe.marketing.mobile.MobileCore;


/**
 * This activity class is responsible to show booking engine page and offer card.
 */

public class BusBookingActivity extends AppCompatActivity {

    private TextView mTextGoingTo, mTextLeavingFrom, mTextSource, mTextDestination;
    private ImageButton mBtnFlip;
    private ImageButton mBtnCrash;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_booking);
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);
       // MobileCore.trackState("BusBookingActivity", null);

        setUpToolBar();
        mTextGoingTo =  findViewById(R.id.text_going_to);
        mTextLeavingFrom =  findViewById(R.id.text_leaving_from);
        mTextDestination =  findViewById(R.id.text_destination);
        mTextSource =  findViewById(R.id.text_source);
        mBtnFlip =  findViewById(R.id.btn_flip);
        //mBtnCrash = findViewById(R.id.btn_crash);

        mBtnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipSourceDesti();
                MobileCore.trackAction("flip Destination",null);
            }
        });


        findViewById(R.id.btn_find_buses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });

        findViewById(R.id.rel_offer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BusBookingActivity.this, OfferDetailsActivity.class));
            }
        });

        findViewById(R.id.btn_webview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BusBookingActivity.this, MyWebView.class));
            }
        });

        findViewById(R.id.btn_productString).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BusBookingActivity.this, ProductString.class));
            }
        });
        findViewById(R.id.btn_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobileCore.trackAction("btnCrash",null);
                startActivity(new Intent(BusBookingActivity.this, AppCrashActivity.class));
            }
        });

        findViewById(R.id.btn_privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobileCore.trackAction("btnPrivacy",null);
                startActivity(new Intent(BusBookingActivity.this, PrivacyActivity.class));
            }
        });

        findViewById(R.id.fragOffer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BusBookingActivity.this, SampleFragmentActivity.class));
            }
        });

        setSource("San Francisco");
        setDest("Las Vegas");

    }


    private void setUpToolBar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

        toolbar.setTitle("Bus Booking");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }




    public void flipSourceDesti() {

        Animation animClockWise = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anin_rotating_50_clockwise);

        final Animation aniAntiClockWise = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_rotate_50_anti_clockwise);

        aniAntiClockWise.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBtnFlip.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animClockWise.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mBtnFlip.setVisibility(View.INVISIBLE);
                mBtnFlip.startAnimation(aniAntiClockWise);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mBtnFlip.startAnimation(animClockWise);
        startAnimation();

    }

    /**
     * Switch animation
     */
    private void startAnimation() {

        Animation forLeavingFromIn = AnimationUtils.loadAnimation(this, R.anim.left_to_right_in);
        Animation forGoingToIn = AnimationUtils.loadAnimation(this, R.anim.right_to_left_in);

        final Animation forLeavingFromOut = AnimationUtils.loadAnimation(this, R.anim.left_to_right_out);
        final Animation forGoingToOut = AnimationUtils.loadAnimation(this, R.anim.right_to_left_out);

        forLeavingFromIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateCities();
                mTextSource.startAnimation(forLeavingFromOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


        forGoingToIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTextDestination.startAnimation(forGoingToOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


        mTextSource.startAnimation(forLeavingFromIn);
        mTextDestination.startAnimation(forGoingToIn);
    }


    /**
     * Interchange
     */
    private void updateCities() {
        String strTemp = mTextSource.getText().toString().trim();
        mTextSource.setText(mTextDestination.getText().toString().trim());
        mTextDestination.setText(strTemp);
    }


    private void setDest(String city) {
        mTextGoingTo.setVisibility(View.VISIBLE);
        mTextDestination.setText(city);
        mTextDestination.setTextColor(ContextCompat.getColor(this, R.color.black_opac));

    }


    private void setSource(String city) {

        mTextLeavingFrom.setVisibility(View.VISIBLE);
        mTextSource.setText(city);
        mTextSource.setTextColor(ContextCompat.getColor(this, R.color.black_opac));
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

    private void showConfirmationDialog() {
        DialogFragment sampleDialogFragment = SampleDialogFragment.getInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment prevFragment = getSupportFragmentManager().findFragmentByTag("dialogView");
        if (prevFragment != null) {
            fragmentTransaction.remove(prevFragment);
        }
        fragmentTransaction.addToBackStack(null);
        sampleDialogFragment.show(fragmentTransaction, "dialogView");
    }

}
