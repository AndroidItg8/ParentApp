package com.itg8.parentapp.children.model;

import com.framgia.library.calendardayview.data.IEvent;

import java.util.Calendar;

public class Event implements IEvent {


    private long mId;
    private Calendar mStartTime;
    private Calendar mEndTime;
    private String mName;
    private String mLocation;
    private int mColor;

    public Event(long mId, Calendar mStartTime,
                 Calendar mEndTime,
                 String mName,
                 String mLocation,
                 int mColor) {
        this.mId=mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mName = mName;
        this.mLocation=mLocation;
        this.mColor = mColor;
    }

    @Override
    public Calendar getStartTime() {
        return mStartTime;
    }

    @Override
    public Calendar getEndTime() {
        return mEndTime;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getHeader() {
        return mName;
    }

    @Override
    public String getDescription() {
        return mLocation;
    }

    @Override
    public int getColor() {
        return mColor;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }
}
