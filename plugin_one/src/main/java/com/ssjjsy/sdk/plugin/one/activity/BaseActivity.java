package com.ssjjsy.sdk.plugin.one.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
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

    protected Button addOneButton() {
        Button button = new Button(this);
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(Color.BLACK);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 20, 0, 0);
        linearLayout.addView(button, lp);
        return button;
    }

}
