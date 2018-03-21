package com.itg8.parentapp.otp.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

import com.itg8.parentapp.common.BaseWeakPresenter;

import java.util.Objects;

/**
 * Created by swapnilmeshram on 15/03/18.
 */

public class OTPPresenterImp extends BaseWeakPresenter<OTPMvp.OTPView> implements OTPMvp.OTPPresenter, OTPMvp.OTPTimerListener {

    private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private final OTPMvp.OtpModuleListener module;
    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()==null)
                return;

            if((intent.getAction().equalsIgnoreCase(SMS_ACTION))){
                Bundle bundle=intent.getExtras();
                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    assert pdusObj != null;
                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();
                        verifyMessageContainsOTP(message);

                    } // end for loop
                } // bundle is null
            }
        }
    };

    private void verifyMessageContainsOTP(String message) {
        if(message.contains("OTP")){
            String[] otps=message.split("OTP");
            String otpVal= otps.length>2?otps[1].substring(0,4):"";
            getView().onOTPReadFromSMS(otpVal);
        }
    }


    /**
     * When creating activity be sure for otp you have use
     * #OTPView in widgets package.
     * @param otpView
     */
    public OTPPresenterImp(OTPMvp.OTPView otpView) {
        super(otpView);
        module=new OTPModuleImp();
    }

    @Override
    public void onStartCheckingSMS() {
        module.onStartTimerThread(this);
    }

    @Override
    public void onOTPVerifyClicked() {
        if(hasView()){
            if(TextUtils.isEmpty(getView().getOTP()))
                getView().onOTPValidate();
        }
    }

    @Override
    public void registerSMSBroadcast() {
        if(hasView()){
            getView().registerSMSBroadcast(receiver);
        }
    }

    @Override
    public void unregisterSMSBroadcast() {
        if(hasView()){
            getView().unregisterSMSBroadcast(receiver);
        }
    }

    @Override
    public void onOtpTimerUpdated(String val) {
        if(hasView()){
            getView().onOtpTimerUdate(val);
        }
    }

    @Override
    public void onOtpTimerOver() {
        if(hasView()){
            getView().onOTPTimeOver();
        }
    }
}
