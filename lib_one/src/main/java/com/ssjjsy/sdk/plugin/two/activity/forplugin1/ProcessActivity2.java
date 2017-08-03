package com.ssjjsy.sdk.plugin.two.activity.forplugin1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ssjjsy.sdk.plugin.binder.Data0Impl;
import com.ssjjsy.sdk.plugin.two.R;

/**
 * Created by Administrator on 2017/7/25.
 */

public class ProcessActivity2 extends AppCompatActivity {

    private TextView textName;
    private TextView textDate;
    private TextView textLocation;
    private TextView textBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process2);
        initView();
    }

    private void initView() {
        textName = (TextView) findViewById(R.id.text_name);
        textDate = (TextView) findViewById(R.id.text_date);
        textLocation = (TextView) findViewById(R.id.text_location);
        textBehavior = (TextView) findViewById(R.id.text_behavior);

        if(Data0Impl.info != null) {
            textName.setText(textName.getText().toString() + Data0Impl.name);
            textDate.setText(textDate.getText().toString() + Data0Impl.info.getDate());
            textLocation.setText(textLocation.getText().toString() + Data0Impl.info.getLocation());
            textBehavior.setText(textBehavior.getText().toString() + Data0Impl.info.getBehavior());
        }
    }
}
