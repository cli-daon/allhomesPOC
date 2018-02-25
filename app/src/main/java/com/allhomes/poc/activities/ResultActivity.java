package com.allhomes.poc.activities;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.allhomes.poc.R;
import com.allhomes.poc.ServiceGenerator;
import com.allhomes.poc.fragments.LeftFragment;
import com.allhomes.poc.fragments.RightFragment;
import com.allhomes.poc.javaBeans.ListingResults;
import com.allhomes.poc.javaBeans.Property;
import com.allhomes.poc.javaBeans.SearchResponse;
import com.allhomes.poc.serverInterfaces.AdsRestfulServerInterface;
import com.allhomes.poc.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cli on 25/02/2018.
 */

public class ResultActivity extends AppCompatActivity {
    private static final String TAG =ResultActivity.class.getName();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private List<String> tabNames;
    private ProgressDialog loadingDialog =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.d(TAG, "Entering onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        initializeLoadingDialog();
        getServerData();
        LogUtil.d(TAG, "Quiting onCreate...");
    }

    private void getServerData() {
        LogUtil.d(TAG, "Entering getServerData...");
        ServiceGenerator.initialize(getString(R.string.base_url));
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        AdsRestfulServerInterface adsRestfulServerInterface =ServiceGenerator.createService(AdsRestfulServerInterface.class);
        Call<SearchResponse> searchCall=adsRestfulServerInterface.searchProperties(bundle.getString("mode"), bundle.getString("suburb"), bundle.getString("postcode"), bundle.getString("state"));
        searchCall.enqueue(new Callback<SearchResponse>() {
            //Inject the data into fragments if retrieving data succeeds
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse=response.body();
                ListingResults listingResults =searchResponse.getListingResults();
                initData(listingResults.getListings());
                initListener();
                loadingDialog.dismiss();
            }

            //Return to MainActivity if retrieving data fails
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                loadingDialog.dismiss();
                LogUtil.e(TAG,t.getMessage());
                Toast.makeText(ResultActivity.this, R.string.retrieve_data_failure, Toast.LENGTH_LONG );
                ResultActivity.this.finish();

            }
        });
        loadingDialog.show();
        LogUtil.d(TAG, "Entering getServerData...");
    }

    private void initView() {
        LogUtil.d(TAG, "Entering initView...");
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        LogUtil.d(TAG, "Quiting initView...");
    }

    private void initializeLoadingDialog()
    {
        LogUtil.d(TAG,"Entering initializeLoadingDiaLogUtil...");
        loadingDialog =new ProgressDialog(this, R.style.CustomDialog);
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        LogUtil.d(TAG,"Quiting initializeLoadingDiaLogUtil...");
    }

    private void initData(List<Property> properties){
        LogUtil.d(TAG,"Entering initData...");
        fragments=new ArrayList<>();
        fragments.add(LeftFragment.newInstance(properties));
        fragments.add(RightFragment.newInstance(properties));
        tabNames=new ArrayList<>();
        tabNames.add(getString(R.string.left_tab));
        tabNames.add(getString(R.string.right_tab));
        for(int i=0;i<tabNames.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabNames.get(i)));
        }
        LogUtil.d(TAG,"Quiting initData...");

    }

    private void initListener(){
        LogUtil.d(TAG,"Entering initListener...");
        FragmentManager fragmentManager=getSupportFragmentManager();
        CustomAdaptor customAdaptor=new CustomAdaptor(fragmentManager);
        viewPager.setAdapter(customAdaptor);
        tabLayout.setupWithViewPager(viewPager);
        LogUtil.d(TAG,"Quiting initListener...");
    }

    class CustomAdaptor extends FragmentPagerAdapter {

        public CustomAdaptor(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments!=null?fragments.size():0;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return tabNames.get(position);
        }
    }
}
