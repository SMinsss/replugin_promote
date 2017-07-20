package com.ssjjsy.sdk.plugin.one.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssjjsy.sdk.plugin.one.R;

/**
 * Created by Administrator on 2017/7/17.
 */

public class BaseActivity extends Activity {

    protected LinearLayout linearLayout;

    protected TextView txt;

    protected Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SM", "Activity name: " + this.getClass().getSimpleName() + " ,taskId: " + getTaskId());
        setContentView(R.layout.activity_test);
        TextView tv = (TextView) findViewById(R.id.txt);
        tv.setText(getClass().getSimpleName() + " " + getTaskId());
        txt = tv;

        btn = (Button) findViewById(R.id.btn);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }

}
