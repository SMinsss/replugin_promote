package com.ssjjsy.sdk.aar.one;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * Created by Administrator on 2017/8/2.
 */

public class TestClass1 {

    public TestClass1(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_one, null);
        Log.i("SM", "TestClass1 inflate finish");
    }

}
