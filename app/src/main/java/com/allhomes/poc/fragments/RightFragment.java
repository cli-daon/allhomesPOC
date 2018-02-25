package com.allhomes.poc.fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allhomes.poc.R;
import com.allhomes.poc.javaBeans.Property;
import com.allhomes.poc.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cli on 25/02/2018.
 */
public class RightFragment extends Fragment {
    private List<Property> properties;
    private static final String TAG =RightFragment.class.getName();

    //Create an instance of right fragment and inject the list of properties
    public static RightFragment newInstance(List<Property> properties) {
        LogUtil.d(TAG, "Entering newInstance...");
        Bundle args = new Bundle();
        args.putParcelableArrayList("properties",  (ArrayList<? extends Parcelable>) properties);
        RightFragment fragment = new RightFragment();
        fragment.setArguments(args);
        LogUtil.d(TAG, "Quiting newInstance...");
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.d(TAG, "Entering onCreateView...");
        View view=inflater.inflate(R.layout.fragment_right, null);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.listRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        Bundle bundle=getArguments();
        properties=bundle.getParcelableArrayList("properties");
        RecyclerViewAdaptor recycleViewAdaptor=new RecyclerViewAdaptor();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewAdaptor);
        LogUtil.d(TAG, "Quiting onCreateView...");
        return view;
    }

    //Custom RecyclerViewAdaptor for right fragment
     class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_property_info_text, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            final Property property=properties.get(position);
            //Rest the adId textView
            final CustomViewHolder viewHolder=(CustomViewHolder)holder;
            viewHolder.adIdTv.setText(getString(R.string.instruction));
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewHolder.adIdTv.setText(getString(R.string.ad_id)+" "+property.getAdId());
                }
            });

        }

        @Override
        public int getItemCount() {
            return properties.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            public final TextView adIdTv;
            public final View view;

            public CustomViewHolder(View view) {
                super(view);
                this.view=view;
                adIdTv = view.findViewById(R.id.adId);
            }
        }
    }
}
