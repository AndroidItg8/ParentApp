package com.itg8.parentapp.Home.mvp;

import com.itg8.parentapp.common.BaseWeakPresenter;
import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblNotification;

import java.util.List;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

public class HomePresenterImp extends BaseWeakPresenter<HomeMvp.HomeView> implements HomeMvp.HomeListener {

    HomeMvp.HomeModule module;
    public HomePresenterImp(HomeMvp.HomeView homeView) {
        super(homeView);
        module=new HomeModuleImp(this);
    }

    @Override
    public void onLocalChildrenDataAvail(List<TblChildren> list) {
        if(hasView()){
            getView().onListOfChildrenAvail(list);
        }
    }

    @Override
    public void onOnlineChildDataAvail(List<TblChildren> list) {
        if(hasView()){
            getView().onListOfChildrenAvail(list);
        }
    }

    @Override
    public void onChildStatusAvail(List<TblChildren> list) {
        if(hasView()){
            getView().onListOfChildrenAvail(list);
        }
    }

    @Override
    public void onNotificationListAvail(List<TblNotification> notifications) {
        if(hasView())
        {
            getView().onListOfNotificationAvail(notifications);
        }
    }

    @Override
    public void onFailToGetLocationChildData(Throwable e) {
       throw new IllegalStateException(e.getMessage());
    }

    @Override
    public void onFailToGetNotificationData(Throwable e) {
        throw new IllegalStateException(e.getMessage());
    }
}
