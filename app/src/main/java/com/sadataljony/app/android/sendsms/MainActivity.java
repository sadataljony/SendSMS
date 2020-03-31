package com.sadataljony.app.android.sendsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditTextMobileNumber, mEditTextSmsBody;
    private Button mBtnSendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// initialize ui
        mEditTextMobileNumber = findViewById(R.id.editTextMobileNumber);// initialize Mobile Number EditText
        mEditTextSmsBody = findViewById(R.id.editTextSmsBody);// initialize SMS Body EditText
        mBtnSendSms = findViewById(R.id.buttonSendSms);// initialize Send SMS Button
        mBtnSendSms.setOnClickListener(this);// on click event in Send SMS Button
    }

    @Override
    public void onClick(View v) {
        // Send SMS Method invoke after click on Send SMS Button
        if (v == mBtnSendSms) {
            String strMobileNumber = mEditTextMobileNumber.getText().toString().trim();
            String strSmeBody = mEditTextSmsBody.getText().toString().trim();
            sendSms(strMobileNumber, strSmeBody);// Send SMS Method
        }

    }

    // Send SMS Method Body
    private void sendSms(String strPhoneNumber, String strSms) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {// checking runtime permission for Sending SMS
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(strPhoneNumber, null, strSms, null, null);// this statement send sms(background)
                Toast.makeText(getApplicationContext(), "Message Sent Successful", Toast.LENGTH_LONG).show();// Showing Toast after sending SMS
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();// Showing Toast if SMS sending fail
                e.printStackTrace();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);// Grant runtime permission for sending SMS
            }
        }

    }
}
