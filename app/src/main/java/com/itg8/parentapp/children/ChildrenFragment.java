package com.itg8.parentapp.children;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.itg8.parentapp.Home.HomeActivity;
import com.itg8.parentapp.R;
import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.db.model.TblNotification;
import com.itg8.parentapp.test.ItemListDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChildrenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChildrenFragment extends Fragment implements ChildFragmentInteractor, ChildlistAdapter.OnClickShowDetailFragment {
    public static final String TAG = "ChildrenFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvChildItems)
    RecyclerView rvChildItems;
    @BindView(R.id.childrenview)
    CardView childrenview;
    @BindView(R.id.txtUnseen)
    TextView txtUnseen;
    @BindView(R.id.noticeView)
    CardView noticeView;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    HomeActivityInteractor interactor;
    private ChildlistAdapter adapter;


    public ChildrenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChildrenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChildrenFragment newInstance(String param1, String param2) {
        ChildrenFragment fragment = new ChildrenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "viewState: onCreate:");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_children, container, false);
        Log.d(TAG, "viewState: onCreateView:");
        unbinder = ButterKnife.bind(this, view);
        initAdapter();
        interactor.onCreateView(TAG);
        return view;
    }

    private void initAdapter() {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        rvChildItems.setLayoutManager(manager);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_fall_down);
        Animation animationEnd = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_fall_finish);

        adapter=new ChildlistAdapter(getFragmentManager(),manager,animation,animationEnd);
        adapter.setListener(this);
        rvChildItems.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "ViewState: onAttach: ");
        ((HomeActivity) context).setHomeFragmentListener(this);
        interactor= (HomeActivityInteractor) context;
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onChildListAvailable(List<TblChildren> list) {
        if(list!=null && list.size()>0)
            adapter.addAllChilds(list);
    }

    @Override
    public void failToGetData() {

    }

    @Override
    public void onNotificationListAvailable(List<TblNotification> list) {

    }

    @Override
    public void onNoNotification() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "ViewState: onDestroyView: ");
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
        if (fragment != null){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .setReorderingAllowed(true)
                    .commitAllowingStateLoss();

//                    .commit();
        }
        interactor.onDestroyView(TAG);
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "ViewState: onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "ViewState: onPause: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "ViewState: onDetach: ");
    }


    @Override
    public void onShowDetailClicked(int position) {
        interactor.onClickShowDetailPage(position);
    }
}
