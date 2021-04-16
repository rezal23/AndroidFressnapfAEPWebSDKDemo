package com.adobe.busbooking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adobe.marketing.mobile.MobileCore;

import java.util.HashMap;

public class ProductString extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productstring);

        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);

        findViewById(R.id.btn_prodViewSendRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showConfirmationDialog();
                prodViewSendRequest();
            }
        });

        findViewById(R.id.btn_addtobasketsendrequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showConfirmationDialog();
                addToBasketsendrequest();
            }
        });

        findViewById(R.id.btn_purchasesendrequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showConfirmationDialog();
                purchaseSendRequest();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void prodViewSendRequest() {
        //Code sample for Product Detail Page - analytics event prodView
        //create a context data dictionary
        HashMap cdata = new HashMap<String, Object>();
        cdata.put(  "&&products", "katze;1085019;1;2.39;eVar16=in_stock|eVar17=katze|eVar18=katzenfutter|eVar19=nassfutter|eVar20=purina one|eVar43=sensitive huhn & karotten 4x85g|eVar57=food|eVar58=reduced");
        //Send the tracking call
        MobileCore.trackAction("prodView", cdata);
    }

    private void addToBasketsendrequest() {
        //Code sample for Add product to Basket - analytics event scAdd
        //create a context data dictionary
        HashMap cdata = new HashMap<String, Object>();
        cdata.put(  "&&products", "katze;1085019;1;2.39");
        //Send the tracking call
        MobileCore.trackAction("scAdd", cdata);
    }

    private void purchaseSendRequest() {
        //Code sample purchase product/s - analytics event purchase
        //create a context data dictionary
        HashMap cdata = new HashMap<String, Object>();
        cdata.put(  "&&products", "katze;1085019;1;2.39");
        cdata.put("purchaseid", "1234567890");
        //Send the tracking call
        MobileCore.trackAction("purchase", cdata);
    }
}