package com.itg8.parentapp.children;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.itg8.parentapp.R;
import com.itg8.parentapp.db.model.TblChildren;
import com.itg8.parentapp.widgets.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by swapnilmeshram on 16/03/18.
 */

public class ChildlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_FULL_SCREEN = 1;
    private static final int ITEM_SMALL_SCREEN = 2;
    private final Animation animation;
    private final Animation animationEnd;
    List<TblChildren> list;
    int selectedPosition = -1;

    private FragmentManager manager;
    private LinearLayoutManager layoutManager;
    OnClickShowDetailFragment listener;
    private int lastPosition=-1;

    public ChildlistAdapter(FragmentManager manager, LinearLayoutManager layoutManager, Animation context, Animation animationEnd) {
        this.manager = manager;
        this.layoutManager = layoutManager;
        this.animation=context;
        this.animationEnd=animationEnd;
        list = new ArrayList<>();
    }

    public void setListener(OnClickShowDetailFragment listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (selectedPosition == position)
            return ITEM_FULL_SCREEN;
        else
            return ITEM_SMALL_SCREEN;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_SMALL_SCREEN)
            return new ViewHolderSmall(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_child_item_home, parent, false));
        else
            return new ViewHolderFull(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_child_full_item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolderFull){
            animateLayout(((ViewHolderFull)holder).relMapview,position);
            ((ViewHolderFull) holder).childName.setText("CHILDREN "+(1+position));
        }else if(holder instanceof ViewHolderSmall){
            ((ViewHolderSmall) holder).childName.setText("CHILDREN "+(1+position));
        }
    }

    private void animateLayout(View holder,int selectedPosition) {
//        android:layoutAnimation="@anim/layout_animation_for_map"
        if (selectedPosition != lastPosition) {
            holder.startAnimation(animation);
            lastPosition=selectedPosition;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void addAllChilds(List<TblChildren> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    public void onViewDestoryed() {

    }

    public class ViewHolderSmall extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fabChildImage)
        CircleImageView fabChildImage;
        @BindView(R.id.childName)
        TextView childName;
        @BindView(R.id.img_location)
        ImageView imgLocation;
        @BindView(R.id.imgStatus)
        ImageView imgStatus;
        @BindView(R.id.txtSyncStatus)
        TextView txtSyncStatus;
        @BindView(R.id.rlStatus)
        LinearLayout rlStatus;

        public ViewHolderSmall(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selectedPosition = getAdapterPosition();
            layoutManager.scrollToPositionWithOffset(getAdapterPosition(), 20);
            notifyDataSetChanged();
        }


    }

    public class ViewHolderFull extends RecyclerView.ViewHolder implements OnMapReadyCallback, View.OnClickListener {
        @BindView(R.id.cardview_container)
        CardView cardView;
        @BindView(R.id.fabChildImage)
        CircleImageView fabChildImage;
        @BindView(R.id.childName)
        TextView childName;
        @BindView(R.id.img_location)
        ImageView imgLocation;
        @BindView(R.id.rv_main)
        RelativeLayout rvMain;
        @BindView(R.id.rel_mapview)
        RelativeLayout relMapview;
        @BindView(R.id.imgStatus)
        ImageView imgStatus;
        @BindView(R.id.frmStatus)
        FrameLayout frmStatus;
        @BindView(R.id.rv_next_location_list)
        RecyclerView rvNextLocationList;

        private GoogleMap mMap;
        LocationAdapter adapter;

        public ViewHolderFull(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            SupportMapFragment mapFragment = (SupportMapFragment) manager
                    .findFragmentById(R.id.map);
            mapFragment.onCreate(null);
            mapFragment.getMapAsync(this);
            setPlacesToRv(gatherAllNextPlaces());
            cardView.setOnClickListener(this);
            frmStatus.setOnClickListener(this);
        }

        private void setPlacesToRv(List<String> nextPlaces) {
            rvNextLocationList.setLayoutManager(new LinearLayoutManager(cardView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            adapter = new LocationAdapter();
            rvNextLocationList.setAdapter(adapter);

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            MapsInitializer.initialize(cardView.getContext());
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.setMaxZoomPreference(25.0f);
            mMap.setMinZoomPreference(5.0f);
            UiSettings settings = mMap.getUiSettings();
            settings.setZoomControlsEnabled(true);

        }

        @Override
        public void onClick(View view) {
            if(view==cardView) {
                selectedPosition = -1;
                animationEnd.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                relMapview.startAnimation(animationEnd);

            }else if(view==frmStatus){
                listener.onShowDetailClicked(getAdapterPosition());
            }
        }
    }

    private List<String> gatherAllNextPlaces() {
        return new ArrayList<>();
    }

    public interface OnClickShowDetailFragment{
        void onShowDetailClicked(int position);
    }
}
