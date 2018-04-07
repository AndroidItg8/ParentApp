package com.itg8.parentapp.attendence;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.itg8.parentapp.R;
import com.itg8.parentapp.widgets.CircleImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Unbinder unbinder;
    @BindView(R.id.fabChildImage)
    CircleImageView fabChildImage;
    @BindView(R.id.lbl_child_name)
    TextView lblChildName;
    @BindView(R.id.lbl_class)
    TextView lblClass;
    @BindView(R.id.lbl_class_value)
    TextView lblClassValue;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.lbl_roll_no)
    TextView lblRollNo;
    @BindView(R.id.lbl_roll_no_value)
    TextView lblRollNoValue;
    @BindView(R.id.lbl_day)
    TextView lblDay;
    @BindView(R.id.lbl_present_total)
    TextView lblPresentTotal;
    @BindView(R.id.lbl_present)
    TextView lblPresent;
    @BindView(R.id.lbl_present_value)
    TextView lblPresentValue;
    @BindView(R.id.lbl_absent)
    TextView lblAbsent;
    @BindView(R.id.lbl_absent_value)
    TextView lblAbsentValue;
    @BindView(R.id.lbl_leave)
    TextView lblLeave;
    @BindView(R.id.lbl_leave_value)
    TextView lblLeaveValue;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceFragment newInstance(String param1, String param2) {
        AttendanceFragment fragment = new AttendanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendence, container, false);
        unbinder = ButterKnife.bind(this, view);

        init();
        return view;

    }

    private void init() {
        setCalenderView();
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new AttendanceAdapter(getActivity()));
    }

    private void setCalenderView() {

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,2);
        events.add(new EventDay(calendar, R.drawable.ic_circle_green));
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        events.add(new EventDay(calendar, R.drawable.ic_circle_red));

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        events.add(new EventDay(calendar, R.drawable.ic_circle_blue));
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        events.add(new EventDay(calendar, R.drawable.ic_circle_green));
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 6 );
        events.add(new EventDay(calendar, R.drawable.ic_circle_green));



        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -2);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setEvents(events);
//        calendarView.setDisabledDays(getDisabledDays());
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
