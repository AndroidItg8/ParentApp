package com.itg8.parentapp.otp.mvp;

import android.os.CountDownTimer;

import com.itg8.parentapp.common.NetworkUtility;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by swapnilmeshram on 15/03/18.
 */

public class OTPModuleImp implements OTPMvp.OtpModuleListener {


    private CountDownTimer countdownTimer;

    @Override
    public void onStartComparingOTP(String otp) {
         new NetworkUtility.NetworkBuilder().build().checkOtp(otp, new NetworkUtility.ResponseListener() {
             @Override
             public void onSuccess(Object message) {

             }

             @Override
             public void onFailure(Object err) {

             }

             @Override
             public void onSomethingWrong(Object e) {

             }
         });

    }


    @Override
    public void onStartTimerThread(final OTPMvp.OTPTimerListener listener) {
        countdownTimer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {

                listener.onOtpTimerUpdated(calculateTimeRemaining(millisUntilFinished));
            }

            public void onFinish() {
               listener.onOtpTimerOver();
            }
        }.start();
    }


    @Override
    public void onDestroyed() {
        if(countdownTimer!=null)
            countdownTimer.cancel();
    }

    private String calculateTimeRemaining(long millisUntilFinished) {
        String m = "";
        if(millisUntilFinished<=1000){
            return "00:00";
        }
        long seconds = millisUntilFinished / 1000;
        if(seconds<60){
            return "00:"+seconds;
        }else {
            long minutes = seconds/60;
            long remainingSeconds=seconds%60;
            if(minutes>0){
                return minutes+":"+(remainingSeconds);
            }else {
                return "00:"+remainingSeconds;
            }
        }
    }
}
