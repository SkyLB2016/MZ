package com.sky.oa.activity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.sky.oa.R;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DeviceIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDeviceId();
    }

    @RequiresPermission(value = "android.permission.READ_PRIVILEGED_PHONE_STATE")
    public void getDeviceId() {
        StringBuilder builder = new StringBuilder();
        String deviceId;
        try {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception ignore) {
            deviceId = "";
        }
        builder.append("设备ID1==");
        builder.append(deviceId);
        builder.append("\n设备ID2==");

        Log.i("MainActivity", "deviceId==" + deviceId);
        //Try to get the ANDROID_ID
        String OpenUDID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        builder.append(OpenUDID);
//        OpenUDID="0000000000000000";
        if (OpenUDID == null || "9774d56d682e549c".equals(OpenUDID) || "0000000000000000".equals(OpenUDID) || OpenUDID.length() < 15) {
            //if ANDROID_ID is null, or it's equals to the GalaxyTab generic ANDROID_ID or bad, generates a new one
            final SecureRandom random = new SecureRandom();
            OpenUDID = new BigInteger(64, random).toString(16);
            builder.append("\n设备ID3==");
            builder.append(OpenUDID);
        }
        Log.i("MainActivity", "deviceId==" + OpenUDID);
        TextView tv = findViewById(R.id.tv);
        tv.setText(builder);
    }
}