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
import android.widget.ImageView;
import android.widget.TextView;

import com.allhomes.poc.R;
import com.allhomes.poc.javaBeans.Property;
import com.allhomes.poc.utils.LogUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cli on 25/02/2018.
 */
public class LeftFragment extends Fragment {
    private List<Property> properties;
    private static final String TAG =LeftFragment.class.getName();

    //Create an instance of left fragment and inject the list of properties
    public static LeftFragment newInstance(List<Property> properties) {
        LogUtil.d(TAG, "Entering newInstance...");
        Bundle args = new Bundle();
        args.putParcelableArrayList("properties",  (ArrayList<? extends Parcelable>) properties);
        LeftFragment fragment = new LeftFragment();
        fragment.setArguments(args);
        LogUtil.d(TAG, "Quiting newInstance...");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.d(TAG, "Entering onCreateView...");
        View view=inflater.inflate(R.layout.fragment_left, null);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.listRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        RecyclerViewAdaptor recycleViewAdaptoer=new RecyclerViewAdaptor();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewAdaptoer);
        Bundle bundle=getArguments();
        properties=bundle.getParcelableArrayList("properties");
        LogUtil.d(TAG, "Quiting onCreateView...");
        return view;
    }

    //Custom RecyclerViewAdaptor for left fragment
    class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder=null;
            View view=null;
            switch (viewType){
                case 1:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_property_info_premium, parent, false);
                    break;
                default:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_property_info_standard, parent, false);
                    break;
            }
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            Property property=properties.get(position);
            CustomViewHolder viewHolder=(CustomViewHolder)holder;
            viewHolder.priceTV.setText(property.getDisplayPrice());
            viewHolder.bedroomsTV.setText(property.getBedrooms()+" "+getString(R.string.bed));
            viewHolder.bathroomsTV.setText(property.getBathrooms()+" "+getString(R.string.bath));
            viewHolder.carspacesTV.setText(property.getCarspaces()+" "+getString(R.string.car));
            viewHolder.addressTV.setText(property.getDisplayableAddress());
            if(property.getRetinaDisplayThumbUrl()!=null){
                Glide.with(LeftFragment.this)
                        .load(property.getRetinaDisplayThumbUrl())
                        .fitCenter()
                        .placeholder(R.color.colorAccent)
                        .into(viewHolder.firstImage);
            }
            if(viewHolder.secondImage!=null&&property.getSecondRetinaDisplayThumbUrl()!=null){
                Glide.with(LeftFragment.this)
                        .load(property.getSecondRetinaDisplayThumbUrl())
                        .fitCenter()
                        .placeholder(R.color.colorAccent)
                        .into(viewHolder.secondImage);
            }
            if(property.getAgencyLogoUrl()!=null){
                Glide.with(LeftFragment.this)
                        .load(property.getAgencyLogoUrl())
                        .fitCenter()
                        .placeholder(R.color.colorAccent)
                        .into(viewHolder.agentIcon);
            }
        }

        @Override
        public int getItemCount() {
            return properties.size();
        }

        @Override
        public int getItemViewType(int position) {
            return properties.get(position).getIsElite();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            public final TextView priceTV;
            public final TextView bedroomsTV;
            public final TextView bathroomsTV;
            public final TextView carspacesTV;
            public final TextView addressTV;
            public final ImageView firstImage;
            public final ImageView secondImage;
            public final ImageView agentIcon;


            public CustomViewHolder(View view) {
                super(view);
                priceTV=view.findViewById(R.id.price);
                bedroomsTV=view.findViewById(R.id.bedrooms);
                bathroomsTV=view.findViewById(R.id.bathrooms);
                carspacesTV=view.findViewById(R.id.carspaces);
                addressTV=view.findViewById(R.id.address);
                firstImage=view.findViewById(R.id.firstImage);
                secondImage=view.findViewById(R.id.secondImage);
                agentIcon=view.findViewById(R.id.agentIcon);
            }
        }
    }

}
