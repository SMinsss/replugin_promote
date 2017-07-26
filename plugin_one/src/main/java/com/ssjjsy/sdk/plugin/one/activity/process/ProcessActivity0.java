package com.ssjjsy.sdk.plugin.one.activity.process;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.i.IPluginManager;
import com.sm.LogUtil;
import com.ssjjsy.sdk.plugin.binder.Data0Info;
import com.ssjjsy.sdk.plugin.binder.IData0;
import com.ssjjsy.sdk.plugin.one.activity.BaseActivity;

/**
 * Created by Administrator on 2017/7/18.
 */

public class ProcessActivity0 extends BaseActivity{

    private String pluginTwoName = "plugin_two";
    private String moduleName = "Data0";

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
                RePlugin.startActivity(ProcessActivity0.this, RePlugin.createIntent(pluginTwoName, "com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity0"));
            }
        });

        Button btn2 = addOneButton();
        btn2.setText("Start pluginTwo another process activity");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RePlugin.startActivity(ProcessActivity0.this, RePlugin.createIntent(pluginTwoName, "com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity1"));
            }
        });

        Button btn3 = addOneButton();
        btn3.setText("Push data0 to pluginTwo, process p0");
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 插件间进行通信，Replugin尽量保持同进程
                IBinder binder =  RePlugin.fetchBinder(pluginTwoName, moduleName);
                IData0 data0 = IData0.Stub.asInterface(binder);
                if(data0 == null) {

                } else {
                    try {
                        data0.setName("SMinsss");
                        Data0Info info = new Data0Info();
                        info.setDate("20170722");
                        info.setLocation("Bainaohui");
                        info.setBehavior("Buy earPhone");
                        data0.setData0Info(info);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    RePlugin.startActivity(ProcessActivity0.this, RePlugin.createIntent(pluginTwoName, "com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity2"));
                }
            }
        });

        Button btn4 = addOneButton();
        btn4.setText("Push data0 to pluginTwo, auto process");
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AUTO默认在UI进程获取binder
                try {
                    IBinder binder = RePlugin.fetchBinder(pluginTwoName, moduleName, String.valueOf(IPluginManager.PROCESS_AUTO));
                    if(binder == null) {
                        // 如果
                        LogUtil.i("binder null");
                        Toast.makeText(ProcessActivity0.this, "PluginTwo 还没在UI进程加载，再点击一次", Toast.LENGTH_SHORT).show();
                    } else {
                        LogUtil.i("binder not null");
                        IData0 data0 = IData0.Stub.asInterface(binder);
                        try {
                            data0.setName("Batman");
                            Data0Info info = new Data0Info();
                            info.setDate("longago");
                            info.setLocation("Building top");
                            info.setBehavior("Jump");
                            data0.setData0Info(info);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    LogUtil.i("getBinder: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

    }
}
