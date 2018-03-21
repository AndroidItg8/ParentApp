package com.itg8.parentapp.Home.mvp;

import com.itg8.parentapp.common.CommonMethod;
import com.itg8.parentapp.common.MyApplication;
import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblNotification;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomeModuleImp implements HomeMvp.HomeModule {
    private HomeMvp.HomeListener listener;

    public HomeModuleImp(HomeMvp.HomeListener listener) {

        this.listener = listener;
    }

    @Override
    public void getLocalChildrenList() {
        Single.create(new SingleOnSubscribe<List<TblChildren>>() {
            @Override
            public void subscribe(SingleEmitter<List<TblChildren>> e) throws Exception {
                try {
                    e.onSuccess(MyApplication.getInstance().getDatabase().tblChildrenDao().getAll());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    e.onError(e1);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<List<TblChildren>>() {
                    @Override
                    public void accept(List<TblChildren> list) throws Exception {
                        listener.onLocalChildrenDataAvail(list);

                    }
                }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailToGetLocationChildData(throwable);
            }
        });
    }

    @Override
    public void getNotificaitonList() {
        Single.create(new SingleOnSubscribe<List<TblNotification>>() {
            @Override
            public void subscribe(SingleEmitter<List<TblNotification>> e) throws Exception {
                e.onSuccess(MyApplication.getInstance().getDatabase().tblNotification().getAllReadStatusNotifications(CommonMethod.NOT_READ));
            }
        }).doOnSuccess(new Consumer<List<TblNotification>>() {
            @Override
            public void accept(List<TblNotification> notifications) throws Exception {
                listener.onNotificationListAvail(notifications);
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailToGetNotificationData(throwable);
            }
        });
    }
}
