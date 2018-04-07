package com.itg8.parentapp.test;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itg8.parentapp.R;


import com.itg8.parentapp.databinding.FragmentItemListDialogBinding;
import com.itg8.parentapp.db.model.TblNotification;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";

    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance(int itemCount) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentItemListDialogBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_item_list_dialog, container, false);
        View view = binding.getRoot();
        TblNotification notification = new TblNotification();
        notification.setTitle("This is the title");
        notification.setDescription("desctiption desctription desctiption desctriptiondesctiption desctription desctiption desctriptiondesctiption desctriptiondesctiption desctriptionvdesctiption desctriptionvdesctiptiondesctiption desctription vvdesctiption desctription");
        //here data must be an instance of the class MarsDataProvider
        binding.setNotification(notification);
        binding.btnOk.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View view) {
        dismiss();
    }
}
