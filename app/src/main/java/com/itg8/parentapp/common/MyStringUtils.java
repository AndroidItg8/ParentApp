package com.itg8.parentapp.common;

import android.content.Context;

/**
 * Created by swapnilmeshram on 21/03/18.
 */

public class MyStringUtils {

    private final Context mContext;

    public MyStringUtils(Context context) {
        this.mContext=context;
    }


    public final static class Builder{
        private Context context;

        public Builder setContext(Context context){
            this.context=context;
            return this;
        }

        public MyStringUtils build(){
            if(context==null)
                throw new RuntimeException("Context not set, please set context before building the Prefs instance.");

            return new MyStringUtils(context);
        }
    }


}
