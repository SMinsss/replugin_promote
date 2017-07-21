package com.ssjjsy.sdk.plugin.one.activity.process;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qihoo360.replugin.RePlugin;
import com.ssjjsy.sdk.plugin.one.activity.BaseActivity;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/18.
 */

public class ProcessActivity0 extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String processName = getCurrentProcessName();
        int pid = Process.myPid();
        Log.i("SM", "Current process: " + processName + " ,pid: " + pid);

        btn.setText("Start pluginTwo same process activity");
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RePlugin.startActivity(ProcessActivity0.this, RePlugin.createIntent("plugin_two", "com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity0"));
            }
        });

        Button btn2 = addOneButton();
        btn2.setText("Start pluginTwo another process activity");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RePlugin.startActivity(ProcessActivity0.this, RePlugin.createIntent("plugin_two", "com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity1"));
            }
        });

    }
}
