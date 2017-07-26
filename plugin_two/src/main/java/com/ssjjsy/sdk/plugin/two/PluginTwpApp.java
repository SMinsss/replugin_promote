package com.ssjjsy.sdk.plugin.two;

import android.app.Application;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.base.IPC;
import com.sm.LogUtil;
import com.ssjjsy.sdk.plugin.binder.Data0Impl;

/**
 * Created by Administrator on 2017/7/25.
 */

public class PluginTwpApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i("PluginTwoApp onCreate: " + IPC.getCurrentProcessName());
        //RePlugin.registerGlobalBinder("Data0", new Data0Impl()); 这个不行
        RePlugin.registerPluginBinder("Data0", new Data0Impl());
    }
}
