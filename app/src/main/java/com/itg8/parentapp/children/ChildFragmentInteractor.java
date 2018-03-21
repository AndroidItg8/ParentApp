package com.itg8.parentapp.children;

import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblNotification;

import java.util.List;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

public interface ChildFragmentInteractor {
    void showProgress();
    void hideProgress();
    void onChildListAvailable(List<TblChildren> list);
    void failToGetData();
    void onNotificationListAvailable(List<TblNotification> list);
    void onNoNotification();

    public interface HomeActivityInteractor{
        void onCreateView(String TAG);
        void onDestroyView(String TAG);

        void onClickShowDetailPage(int position);

    }

}
