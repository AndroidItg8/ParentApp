package com.itg8.parentapp.login.mvp;

import com.itg8.parentapp.common.CommonMethod;
import com.itg8.parentapp.common.NetworkUtility;
import com.itg8.parentapp.common.Prefs;

import org.json.JSONException;
import org.json.JSONObject;



class LoginModuleImp implements LoginMVP.LoginModule {
    @Override
    public void onStartCall(String url, String username, String password, final LoginMVP.LoginListener listener) {
        new NetworkUtility.NetworkBuilder().build().login(url,username, password, new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                    String response= (String) message;
                if(response!=null){
                    try {
                        JSONObject object=new JSONObject(response);
                        if(object.has("access_token") && object.has("UserType")) {
                            if (object.has("access_token")) {
                                Prefs.putString(CommonMethod.TOKEN, object.getString("access_token"));
                                listener.onSuccess();
                            } else if (object.has("error_description")) {
                                listener.onFail(object.getString("error_description"));
                            }
                        }else {
                            listener.onFail("Invalid Credential");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Object err) {
                listener.onError(err);

            }

            @Override
            public void onSomethingWrong(Object e) {
                listener.onError(e);
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
