package com.itg8.parentapp.Home.mvp;

import com.itg8.parentapp.common.BaseView;
import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblNotification;

import java.util.List;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

public interface HomeMvp {
    public interface HomeView extends BaseView{
        void onListOfChildrenAvail(List<TblChildren> children);
        void onListOfNotificationAvail(List<TblNotification> notifications);
    }

    public interface HomePresenter{
        void onStartGettingLocalChildren();
        void onStartGettingLocalNotificationData();
        void onStartGettingOnlineChildren();
        void onStartGettingChildrenStatus();
    }

    public interface HomeModule{
        void getLocalChildrenList();
        void getNotificaitonList();
    }

    public interface HomeListener{
        void onLocalChildrenDataAvail(List<TblChildren> list);
        void onOnlineChildDataAvail(List<TblChildren> list);
        void onChildStatusAvail(List<TblChildren> list);
        void onNotificationListAvail(List<TblNotification> notifications);

        void onFailToGetLocationChildData(Throwable e);

        void onFailToGetNotificationData(Throwable e);
    }






}
