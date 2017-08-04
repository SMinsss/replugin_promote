package com.ssjjsy.sdk.aar.two;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * Created by Administrator on 2017/8/3.
 */

public class TestClass21 {

    public TestClass21(Context context) {
        Log.i("SM", "TestClass21 constructor");
        LayoutInflater.from(context).inflate(R.layout.layout_two, null);
    }

}
