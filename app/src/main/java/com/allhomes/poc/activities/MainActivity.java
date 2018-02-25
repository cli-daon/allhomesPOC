package com.allhomes.poc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import com.allhomes.poc.R;
import com.allhomes.poc.utils.LogUtil;

/**
 * Created by cli on 25/02/2018.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG =MainActivity.class.getName();
    private Button searchButton;
    private Spinner modeSpinner;
    private Spinner stateSpinner;
    private Spinner suburbSpinner;
    private Spinner postcodeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.d(TAG, "Entering onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        LogUtil.d(TAG, "Quiting onCreate...");

    }

    private void initView(){
        LogUtil.d(TAG, "Entering initView...");
        searchButton=findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        modeSpinner=findViewById(R.id.modeSpinner);
        stateSpinner=findViewById(R.id.stateSpinner);
        suburbSpinner=findViewById(R.id.suburbSpinner);
        postcodeSpinner=findViewById(R.id.postCodeSpinner);
        LogUtil.d(TAG, "Quiting initView...");
    }

    @Override
    public void onClick(View view) {
        LogUtil.d(TAG, "Entering onClick...");
        switch(view.getId()){
            case R.id.searchButton:
                Intent intent=new Intent(this,ResultActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("mode",modeSpinner.getSelectedItem().toString());
                bundle.putString("state", stateSpinner.getSelectedItem().toString());
                bundle.putString("suburb", suburbSpinner.getSelectedItem().toString());
                bundle.putString("postcode", postcodeSpinner.getSelectedItem().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        LogUtil.d(TAG, "Quiting onClick...");
    }
}
