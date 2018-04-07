package com.itg8.parentapp.attendence;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itg8.parentapp.R;
import butterknife.ButterKnife;

/**
 * Created by Android itg 8 on 4/6/2018.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {
    private Context mContext;

    public AttendanceAdapter(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public AttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_attendance, parent, false);

        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendanceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {
        public AttendanceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
