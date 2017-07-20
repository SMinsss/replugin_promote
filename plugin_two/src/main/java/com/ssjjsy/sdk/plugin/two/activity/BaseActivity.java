package com.ssjjsy.sdk.plugin.two.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.ssjjsy.sdk.plugin.two.R;

/**
 * Created by shim on 2017/7/21.
 */

public class BaseActivity extends AppCompatActivity {

    protected TextView txt;
    protected Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
