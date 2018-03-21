package com.itg8.parentapp.db.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

@Dao
public interface TblChildrenDao {
    @Query("Select * from tblchildren")
    List<TblChildren> getAll();

    @Query("Select * from tblchildren WHERE sid = (:sid)")
    TblChildren getChildrenById(int sid);

    @Insert
    void insertAll(TblChildren... children);

    @Update
    void updateAll(TblChildren... children);

    @Delete
    void delete(TblChildren children);


}
