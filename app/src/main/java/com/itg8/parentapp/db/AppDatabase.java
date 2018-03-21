package com.itg8.parentapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblChildrenDao;
import com.itg8.parentapp.db.model.TblNotification;
import com.itg8.parentapp.db.model.TblNotificationDao;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

@Database(entities = {TblChildren.class, TblNotification.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract TblChildrenDao tblChildrenDao();
    public abstract TblNotificationDao tblNotification();
}
