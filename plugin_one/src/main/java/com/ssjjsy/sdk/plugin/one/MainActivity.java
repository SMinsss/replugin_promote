package com.ssjjsy.sdk.plugin.one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.common.utils.TimeUtils;
import com.sm.LogUtil;
import com.ssjjsy.sdk.aar.one.TestClass0;
import com.ssjjsy.sdk.aar.one.TestClass1;
import com.ssjjsy.sdk.demo.classloader.TestA;
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

        findViewById(R.id.btn_activity_ProcessActivity0).setOnClickListener(new View.OnClickListener() {
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

        findViewById(R.id.btn_test_classloader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("ClassLoader name: " + getClassLoader().getClass().getSimpleName());
                ClassLoader loader = getClassLoader();
                try {
                    loader.loadClass("com.ssjjsy.sdk.demo.classloader.TestA");
                    LogUtil.i("loadClass1 success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    loader.loadClass("com.qihoo360.replugin.common.utils.TimeUtils");
                    LogUtil.i("loadClass2 success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogUtil.i("Time: " + TimeUtils.getNowString());
                LogUtil.i("btn_test1");
                TestA testA = new TestA();
                LogUtil.i("btn test2");
            }
        });

        findViewById(R.id.btn_test_aarone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestClass0 class0 = new TestClass0(MainActivity.this);
                TestClass1 class1 = new TestClass1(MainActivity.this);
            }
        });
    }
}
