package com.itg8.parentapp.common;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itg8.parentapp.R;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    private static final int RC_SMS = 101;
    private static String[] SMS_PERMISSION={Manifest.permission.RECEIVE_SMS};
    private boolean hasSMSPermission;
    private AskForSmsPermission callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSMSPermission();
    }

    private void checkSMSPermission() {
        hasSMSPermission=hasSMSPermission();
    }

    private boolean hasSMSPermission(){
        return EasyPermissions.hasPermissions(this, SMS_PERMISSION);
    }

    public void forcefullyAskSMSPermission(AskForSmsPermission callback) {
        if (!hasSMSPermission) {
            setCallback(callback);
            askForSmsPermission();
        }else {
            callback.onGrantSMS();
        }
    }

    @AfterPermissionGranted(RC_SMS)
    private void askForSmsPermission() {
        EasyPermissions.requestPermissions(this,getString(R.string.rationale_ask_sms),RC_SMS,SMS_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    public void setCallback(AskForSmsPermission callback) {
        this.callback = callback;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(perms.contains(SMS_PERMISSION[0])){
            callback.onGrantSMS();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if(perms.contains(SMS_PERMISSION[0]))
            callback.onDeniedSMS();
    }

    public interface AskForSmsPermission{
        void onGrantSMS();
        void onDeniedSMS();
    }
}
