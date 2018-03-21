package com.itg8.parentapp.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

@Entity
public class TblNotification {
    public static final String FIELD_TITLE="title";
    public static final String FIELD_DESCRIPTION="description";
    public static final String FIELD_READ_STATUS="read_status";
    public static final String FIELD_DATE_TIME="date_time";
    public static final String FIELD_ACK="acknowledge";


    @PrimaryKey
    private int nid;

    @ColumnInfo(name = FIELD_TITLE)
    private String title;

    @ColumnInfo(name = FIELD_DESCRIPTION)
    private String description;

    @ColumnInfo(name = FIELD_READ_STATUS)
    private  int status;

    @ColumnInfo(name = FIELD_DATE_TIME)
    private long dateTime;

    @ColumnInfo(name = FIELD_ACK)
    private boolean ack;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }
}
