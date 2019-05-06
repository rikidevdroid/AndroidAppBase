package com.example.amir.base.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.amir.base.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private int entries = 6;
    private String phoneNum[];
    private String buttonLabels[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = new String[entries];
        buttonLabels = new String[entries];

        // Populate the data arrays
        populateArrays();

        // Set up buttons and attach click listeners

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setText(buttonLabels[0]);
        button1.setOnClickListener(this);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(buttonLabels[1]);
        button2.setOnClickListener(this);

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setText(buttonLabels[2]);
        button3.setOnClickListener(this);

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setText(buttonLabels[3]);
        button4.setOnClickListener(this);

        Button button5 = (Button)findViewById(R.id.button5);
        button5.setText(buttonLabels[4]);
        button5.setOnClickListener(this);

        Button button6 = (Button)findViewById(R.id.button6);
        button6.setText(buttonLabels[5]);
        button6.setOnClickListener(this);
    }

    // Launch the phone dialer

    public void launchDialer(String number){
        String numberToDial = "tel:"+number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(numberToDial)));
    }



    public void populateArrays(){


        phoneNum[0] = "999-000-0001";
        phoneNum[1] = "999-000-0002";
        phoneNum[2] = "999-000-0003";
        phoneNum[3] = "999-000-0004";
        phoneNum[4] = "999-000-0005";
        phoneNum[5] = "999-000-0006";

        buttonLabels[0] = "Jane D. Arc";
        buttonLabels[1] = "John Doe";
        buttonLabels[2] = "Jane Doe";
        buttonLabels[3] = "Abraham Linking";
        buttonLabels[4] = "Mona Liza";
        buttonLabels[5] = "Issac Nuton";
    }

    @Override
    public void onClick(View v) {
        if(isPermissionGranted()){
        switch (v.getId()) {

            case R.id.button1:
                launchDialer(phoneNum[0]);
                break;

            case R.id.button2:
                launchDialer(phoneNum[1]);
                break;

            case R.id.button3:
                launchDialer(phoneNum[2]);
                break;

            case R.id.button4:
                launchDialer(phoneNum[3]);
                break;

            case R.id.button5:
                launchDialer(phoneNum[4]);
                break;

            case R.id.button6:
                launchDialer(phoneNum[5]);
                break;
        }}
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}


