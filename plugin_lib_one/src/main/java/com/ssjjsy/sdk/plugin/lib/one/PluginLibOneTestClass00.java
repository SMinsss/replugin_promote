package com.ssjjsy.sdk.plugin.lib.one;

import android.content.Context;
import android.util.Log;

import com.ssjjsy.sdk.lib.one.LibOneTestClass00;

/**
 * Created by Administrator on 2017/8/3.
 */

public class PluginLibOneTestClass00 {

    public PluginLibOneTestClass00(Context context) {
        Log.i("SM", "PluginLibOneTestClass00");
        new LibOneTestClass00(context);
    }

}
