package com.itg8.parentapp.otp.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.itg8.parentapp.common.BaseView;

/**
 * Created by swapnilmeshram on 15/03/18.
 */

public interface OTPMvp {
    public interface OTPView extends BaseView {
        void onOTPTimeOver();
        void onOTPMatch();
        void onOTPMisMatch();
        String getOTP();
        void onOTPReadFromSMS(String otpVal);
        Context askForContext();

        void registerSMSBroadcast(BroadcastReceiver receiver);
        void unregisterSMSBroadcast(BroadcastReceiver receiver);
        void onOTPValidate();

        void onOtpTimerUdate(String val);
    }

    public interface OTPPresenter{
        void onStartCheckingSMS();
        void onOTPVerifyClicked();
        void registerSMSBroadcast();
        void unregisterSMSBroadcast();
    }

    public interface OTPMatchListener{
        void onOTPMatch();
        void onOTPMismatch();
        void onError();
    }

    public interface OTPTimerListener{
        void onOtpTimerUpdated(String val);
        void onOtpTimerOver();
    }

    public interface OtpModuleListener{
        void onStartComparingOTP(String otp);
        void onStartTimerThread(OTPTimerListener listener);
        void onDestroyed();
    }
}
