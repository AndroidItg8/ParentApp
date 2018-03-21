package com.itg8.parentapp.common;

import android.content.Context;

import com.itg8.parentapp.Home.HomeActivity;
import com.itg8.parentapp.R;

/**
 * Created by swapnilmeshram on 15/03/18.
 */

public class CommonMethod {
    public static final String TOKEN = "TOKEN";
    public static final String BASE_URL = "http://sdkfjskdjfkjsdkjfjskdjfkjsk";
    public static final String NOTIFICATION_COUNT = "NOTIFICATION_COUNT";
    public static final int NOT_READ = 0;
    private static final String ILFS = "ilfs";
    public static final String IS_LOGIN = "hasDetail";

    public static boolean isLogin(Context context) {
        if(context==null)
            throw new NullPointerException("Context is null");
        return Prefs.getBoolean(ILFS, false);
    }
}
