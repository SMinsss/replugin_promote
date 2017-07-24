package com.ssjjsy.sdk.plugin.one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qihoo360.replugin.RePlugin;
import com.ssjjsy.sdk.plugin.one.activity.fetchview.PluginTwoImageActivity;
import com.ssjjsy.sdk.plugin.one.activity.process.ProcessActivity0;
import com.ssjjsy.sdk.plugin.one.activity.taskaffinity.TAActivity1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("SM", "Activity name: " + this.getClass().getSimpleName() + " ,taskId: " + getTaskId());

        findViewById(R.id.btn_activity_TAActivity1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TAActivity1.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_activity_ProcessActivity1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProcessActivity0.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_activity_Plugin2MainActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RePlugin.startActivity(MainActivity.this, RePlugin.createIntent("plugin_two", "com.ssjjsy.sdk.plugin.two.MainActivity"));
            }
        });

        findViewById(R.id.btn_activity_pluginTwoImageActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PluginTwoImageActivity.class);
                startActivity(intent);
            }
        });
    }
}
