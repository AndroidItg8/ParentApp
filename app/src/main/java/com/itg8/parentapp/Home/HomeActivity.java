package com.itg8.parentapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.itg8.parentapp.Home.mvp.HomeMvp;
import com.itg8.parentapp.R;
import com.itg8.parentapp.children.ChildFragmentInteractor;
import com.itg8.parentapp.children.ChildrenFragment;
import com.itg8.parentapp.children.old_history.HistoryFragment;
import com.itg8.parentapp.children.today_history.OneDayHistoryFragment;
import com.itg8.parentapp.common.BaseActivity;
import com.itg8.parentapp.common.CommonMethod;
import com.itg8.parentapp.common.Prefs;
import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblNotification;
import com.itg8.parentapp.login.LoginActivity;
import com.itg8.parentapp.test.ItemListDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements ChildFragmentInteractor.HomeActivityInteractor, HomeMvp.HomeView {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startFragment();
                    return true;
                case R.id.navigation_history:
                    if(currentFragment.equalsIgnoreCase(HistoryFragment.TAG))
                        return true;

                    fm=getSupportFragmentManager();
                    ft=fm.beginTransaction();
                    ft.replace(R.id.container, HistoryFragment.newInstance("",""),HistoryFragment.TAG);
                    ft.addToBackStack(HistoryFragment.TAG);
                    ft.commit();
                    return true;
                case R.id.navigation_profile:
                    return true;
                case R.id.navigation_request:
                    return true;
            }
            return false;
        }
    };

    private ChildFragmentInteractor homeFragmentListener;
    private boolean homeFragmentViewCreated;
    private boolean oneDayFragmentViewCreated;
    private String currentFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        loadMap();
        //TODO uncomment
        if(!getIntent().hasExtra(CommonMethod.IS_LOGIN)){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        startFragment();
    }

    private void loadMap() {
        // Fixing Later Map loading Delay
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MapView mv = new MapView(getApplicationContext());
                    mv.onCreate(null);
                    mv.onPause();
                    mv.onDestroy();
                }catch (Exception ignored){

                }
            }
        }).start();
    }

    private void startFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChildrenFragment()).commit();
    }


    public void setHomeFragmentListener(ChildFragmentInteractor listener) {
        this.homeFragmentListener = listener;
    }

    @Override
    public void onCreateView(String TAG) {
        currentFragment=TAG;
        if(TAG.equalsIgnoreCase(ChildrenFragment.TAG)) {
            homeFragmentViewCreated = true;
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        else if(
                TAG.equalsIgnoreCase(OneDayHistoryFragment.TAG) ||
                TAG.equalsIgnoreCase(HistoryFragment.TAG)
                ) {
            oneDayFragmentViewCreated = true;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onDestroyView(String TAG) {
        if(TAG.equalsIgnoreCase(ChildrenFragment.TAG))
            homeFragmentViewCreated = false;
        else if(TAG.equalsIgnoreCase(OneDayHistoryFragment.TAG))
            oneDayFragmentViewCreated=false;
    }

    @Override
    public void onClickShowDetailPage(int position) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, OneDayHistoryFragment.newInstance("",""),OneDayHistoryFragment.TAG);
        ft.addToBackStack(OneDayHistoryFragment.TAG);
        ft.commit();
    }

    public void showNotifications(View view) {
        ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoInternet() {

    }

    @Override
    public void onRetroError(Object obj) {

    }

    @Override
    public void onListOfChildrenAvail(List<TblChildren> children) {
        if (homeFragmentViewCreated) {
            homeFragmentListener.onChildListAvailable(children);
        }
    }

    @Override
    public void onListOfNotificationAvail(List<TblNotification> notifications) {
        if (homeFragmentViewCreated) {
            homeFragmentListener.onNotificationListAvailable(notifications);
        }
    }


}
