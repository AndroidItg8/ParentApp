package com.itg8.parentapp.db.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

@Dao
public interface TblNotificationDao {

    @Query("SELECT * FROM tblnotification")
    List<TblNotification> getAll();

    @Query("SELECT * FROM tblnotification where read_status=(:status)")
    List<TblNotification> getAllReadStatusNotifications(int status);

    @Query("SELECT * FROM tblnotification where acknowledge=(:ack) and  read_status=(:status)")
    List<TblNotification> getAllReadButNotAckNotifications(boolean ack, int status);




}
