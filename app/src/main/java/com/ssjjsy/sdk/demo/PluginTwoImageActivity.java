package com.ssjjsy.sdk.demo;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.sm.LogUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class PluginTwoImageActivity extends AppCompatActivity {
    private FrameLayout container;

    private final String pluginTwoName = "plugin_two";
    private String pluginTwoPkgName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugintwo_image);
        List<PluginInfo> pluginInfoList = RePlugin.getPluginInfoList();
        for(PluginInfo info : pluginInfoList) {
            LogUtil.i("Plugin alias: " + info.getAlias());
            LogUtil.i("Plugin name: " + info.getName());
            LogUtil.i("Plugin packageName: " + info.getPackageName());
            if(TextUtils.equals(info.getAlias(), pluginTwoName)) {
                pluginTwoPkgName = info.getPackageName();
            }
        }

        container = (FrameLayout) findViewById(R.id.container);

        findViewById(R.id.btn_add_inflatexml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInflateXml();
            }
        });
        findViewById(R.id.btn_add_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });
        findViewById(R.id.btn_remove_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView();
            }
        });
    }

    private void addInflateXml() {
        Resources res = RePlugin.fetchResources(pluginTwoName);
        int layoutId = 0;
        if(res != null) {
            layoutId = res.getIdentifier(pluginTwoPkgName + ":layout/image_layout", null, null);
        }
        if(layoutId != 0) {
            View view = LayoutInflater.from(RePlugin.fetchContext(pluginTwoName)).inflate(layoutId, null);
            FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            container.removeAllViews();
            container.addView(view, fp);
            return;
        } else {
            LogUtil.i("layoutId为0");
        }
        Toast.makeText(this, "addInflateXml失败", Toast.LENGTH_SHORT).show();
    }

    private void addView() {
        Context context = RePlugin.fetchContext(pluginTwoName);
        ClassLoader cl = RePlugin.fetchClassLoader(pluginTwoName);

        try {
            String clsName = "com.ssjjsy.sdk.plugin.two.view.PluginTwoImage";
            Class cls = cl.loadClass(clsName);
            Constructor constructor = cls.getConstructor(new Class[]{Context.class});
            //Object object = constructor.newInstance(new Object[]{PluginTwoImageActivity.this});
            Object object = constructor.newInstance(new Object[]{context});
            Method getView = cls.getDeclaredMethod("getView");
            View view = (View) getView.invoke(object);
            FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            container.removeAllViews();
            container.addView(view, fp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void removeView() {
        container.removeAllViews();
    }
}
