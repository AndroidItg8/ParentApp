package com.itg8.parentapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.itg8.parentapp.Home.HomeActivity;
import com.itg8.parentapp.R;
import com.itg8.parentapp.common.CommonMethod;
import com.itg8.parentapp.common.UtilSnackbar;
import com.itg8.parentapp.login.mvp.LoginMVP;
import com.itg8.parentapp.login.mvp.LoginPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements LoginMVP.LoginView, View.OnClickListener {


    @BindView(R.id.edt_mobile_number)
    TextInputEditText edtMobileNumber;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;


    LoginMVP.LoginPresenter presenter;
    @BindView(R.id.btnLayout)
    ButtonBarLayout btnLayout;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.layoutUsername)
    TextInputLayout layoutUsername;
    @BindView(R.id.layoutPassword)
    TextInputLayout layoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImp(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        hide();
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }

    private void toggle() {
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @Override
    public String getUsername() {
        return edtMobileNumber.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(CommonMethod.IS_LOGIN,true);
        startActivity(intent);
    }

    @Override
    public void onFail(String message) {
        UtilSnackbar.showSnakbarRedColor(edtMobileNumber, message);
    }

    @Override
    public void onError(Object t) {
        UtilSnackbar.showSnakbarTypeThree(edtMobileNumber, new UtilSnackbar.OnSnackbarActionClickListener() {
            @Override
            public void onRetryClicked() {

            }
        });
    }

    @Override
    public void onUsernameInvalid(String err) {
        layoutUsername.setError(err);
    }

    @Override
    public void onPasswordInvalid(String err) {
        layoutPassword.setError(err);
    }

    @Override
    public void showProgress() {
        btnLayout.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnLayout.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        onSuccess();
        //TODO remove
//        presenter.onLoginClicked(view);
    }
}
