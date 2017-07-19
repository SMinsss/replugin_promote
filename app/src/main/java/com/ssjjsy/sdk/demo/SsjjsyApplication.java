package com.ssjjsy.sdk.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.RePluginEventCallbacks;
import com.qihoo360.replugin.model.PluginInfo;
import com.sm.LogUtil;

/**
 * Created by Administrator on 2017/7/12.
 */

public class SsjjsyApplication extends RePluginApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        RePlugin.enableDebugger(base, BuildConfig.DEBUG);
    }

    @Override
    protected RePluginConfig createConfig() {
        RePluginConfig config = new RePluginConfig();

        config.setUseHostClassIfNotFound(true);

        config.setEventCallbacks(mEventCallbacks);

        return super.createConfig();
    }

    private RePluginEventCallbacks mEventCallbacks = new RePluginEventCallbacks(this) {

        @Override
        public void onInstallPluginSucceed(PluginInfo info) {
            super.onInstallPluginSucceed(info);
            Toast.makeText(SsjjsyApplication.this, "安装插件成功", Toast.LENGTH_SHORT).show();
            LogUtil.i("onInstallPluginSucceed");
            LogUtil.i("type: " + info.getType());
            LogUtil.i("alias: " + info.getAlias());
            LogUtil.i("name: " + info.getName());
            LogUtil.i("packageName: " + info.getPackageName());
            LogUtil.i("getApkFile: " + info.getApkFile().getAbsolutePath());
            LogUtil.i("getDexFile:" + info.getDexFile().getAbsolutePath());
            LogUtil.i("getDexParentDir:" + info.getDexParentDir().getAbsolutePath());
        }

        @Override
        public void onInstallPluginFailed(String path, InstallResult code) {
            super.onInstallPluginFailed(path, code);
            Toast.makeText(SsjjsyApplication.this, "安装插件失败", Toast.LENGTH_SHORT).show();
            LogUtil.i("path: " + path + " , code : " + code);
        }

        @Override
        public void onStartActivityCompleted(String plugin, String activity, boolean result) {
            super.onStartActivityCompleted(plugin, activity, result);
            Toast.makeText(SsjjsyApplication.this, "打开Activity完成", Toast.LENGTH_SHORT).show();
            LogUtil.i("plugin: " + plugin);
            LogUtil.i("activity: " + activity);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            super.onActivityDestroyed(activity);
            Toast.makeText(SsjjsyApplication.this, "Activity销毁", Toast.LENGTH_SHORT).show();
            LogUtil.i("activity: " + activity);
        }

        @Override
        public void onBinderReleased() {
            super.onBinderReleased();
            Toast.makeText(SsjjsyApplication.this, "Binder Release", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPrepareAllocPitActivity(Intent intent) {
            super.onPrepareAllocPitActivity(intent);
            Toast.makeText(SsjjsyApplication.this, "onPrepareAllocPitActivity", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPrepareStartPitActivity(Context context, Intent intent, Intent pittedIntent) {
            super.onPrepareStartPitActivity(context, intent, pittedIntent);
            Toast.makeText(SsjjsyApplication.this, "onPrepareStartPitActivity", Toast.LENGTH_SHORT).show();
        }
    };
}
