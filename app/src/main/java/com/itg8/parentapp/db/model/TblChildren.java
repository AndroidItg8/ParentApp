package com.itg8.parentapp.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by swapnilmeshram on 16/03/18.
 */


@Entity
public class TblChildren {
    private static final String FIELD_NAME="student_name";
    private static final String FIELD_LOCATION="s_location";
    private static final String FIELD_PROFILE_PIC="s_profile_pic";
    private static final String FIELD_STATUS="s_status";
    private static final String FIELD_LAST_SYNC="s_last_sync";

    @PrimaryKey
    private int sid;

    @ColumnInfo(name = FIELD_NAME)
    private String name;

    @ColumnInfo(name = FIELD_LOCATION)
    private String location;

    @ColumnInfo(name = FIELD_PROFILE_PIC)
    private String profilePicPath;

    @ColumnInfo(name = FIELD_STATUS)
    private String status;

    @ColumnInfo(name = FIELD_LAST_SYNC)
    private long lastSync;


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getLastSync() {
        return lastSync;
    }

    public void setLastSync(long lastSync) {
        this.lastSync = lastSync;
    }
}
