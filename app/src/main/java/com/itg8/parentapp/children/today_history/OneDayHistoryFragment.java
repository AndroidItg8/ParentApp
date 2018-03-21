package com.itg8.parentapp.children.today_history;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.library.calendardayview.CalendarDayView;
import com.itg8.parentapp.R;
import com.itg8.parentapp.children.ChildFragmentInteractor;
import com.itg8.parentapp.children.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link OneDayHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneDayHistoryFragment extends Fragment {

    public static final String TAG = "OneDayHistoryFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.dayView)
    CalendarDayView dayView;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ChildFragmentInteractor.HomeActivityInteractor mListener;
    private List<Event> events;

    public OneDayHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneDayHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OneDayHistoryFragment newInstance(String param1, String param2) {
        OneDayHistoryFragment fragment = new OneDayHistoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_one_day_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        mListener.onCreateView(TAG);
        events=new ArrayList<>();
        int eventColor = getResources().getColor(R.color.colorPrimary);
        Calendar timeStart = Calendar.getInstance();
        timeStart.set(Calendar.HOUR_OF_DAY,16);
        timeStart.set(Calendar.MINUTE,15);
        Calendar timeEnd = (Calendar) timeStart.clone();
        timeEnd.add(Calendar.HOUR_OF_DAY, 1);
        timeEnd.add(Calendar.MINUTE,30);
        Event event = new Event(3, timeStart, timeEnd, "event 6", "house", eventColor);
//        event.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.avatar));
//        event.setTitle("event 2 this is test");
//        event.setDescription("Yuong alsdf");
//        event.setQuote("Google map");
        events.add(event);
        dayView.setLimitTime(6,18);
        dayView.setEvents(events);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            mListener = (ChildFragmentInteractor.HomeActivityInteractor) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListener.onDestroyView(TAG);
        unbinder.unbind();
    }



}
